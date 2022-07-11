package com.fretes.calculofretes.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fretes.calculofretes.Model.Cep;
import com.fretes.calculofretes.Model.Frete;
import com.fretes.calculofretes.Repository.FreteRepository;

@RestController
@RequestMapping("/frete")
@CrossOrigin("*")
public class FreteController {
	@Autowired
	private FreteRepository repository;
//	Consultar todos os fretes postados
	@GetMapping
	public ResponseEntity<List<Frete>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
//	Consultar Fretes pelo nome do destinatario
	@GetMapping("/nome/{nomeDestinatario}")
	public ResponseEntity<List<Frete>> GetByNomeDestinatario(@PathVariable String nomeDestinatario){
		return ResponseEntity.ok(repository.findAllByNomeDestinatarioContainingIgnoreCase(nomeDestinatario));
	}
//	Postar um Frete com a regra de Negocio
	@PostMapping(consumes = {"application/json"})
	public ResponseEntity<Frete> postFrete (@RequestBody Frete frete){
		
		String api = "https://viacep.com.br/ws/";
		
		String primeiraConsulta = frete.getCepOrigem();
		String segundaConsulta = frete.getCepDestino();
		
		String  apiPrimeiraConsulta = api+primeiraConsulta+"/json/";
		String  apiSegundaConsulta = api+segundaConsulta+"/json/";
		
		
		RestTemplate restTemplate = new RestTemplate();

		Cep cepOrigem = restTemplate.getForObject(apiPrimeiraConsulta, Cep.class);
		Cep cepDestino= restTemplate.getForObject(apiSegundaConsulta, Cep.class);
		
//		Formata o valor do cep conforme a api utilizada
		frete.setCepOrigem(cepOrigem.getCep());
		frete.setCepDestino(cepDestino.getCep());
		
//		Insere os valores do frete e data de entrega conforme condição
		if(cepOrigem.getDdd().equals(cepDestino.getDdd())) {
			frete.setVlTotalFrete(frete.getPeso() - frete.getPeso() * 0.5);
			frete.setDataPrevistaEntrega(1);
		}else if(cepOrigem.getUf().equals(cepDestino.getUf())) {
			frete.setVlTotalFrete(frete.getPeso() - frete.getPeso() * 0.75);
			frete.setDataPrevistaEntrega(3);
		}else {
			frete.setVlTotalFrete(frete.getPeso());
			frete.setDataPrevistaEntrega(10);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(frete));
	}
	@DeleteMapping("/{id}")
	public void deletaFrete(@PathVariable long id) {
		repository.deleteById(id);
	}
}