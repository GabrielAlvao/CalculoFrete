package com.fretes.calculofretes.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fretes.calculofretes.Model.Frete;

@Repository
public interface FreteRepository extends JpaRepository <Frete, Long>{
	public List<Frete> findAllByNomeDestinatarioContainingIgnoreCase (String nomeDestinatario);
}
