package com.fretes.calculofretes.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_calculo_frete")
public class Frete {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private double peso;
	@NotNull
	private String cepOrigem;
	@NotNull
	private String cepDestino;
	@NotNull
	private String nomeDestinatario;
	@NotNull
	private double vlTotalFrete;
	@NotNull
	private int dataPrevistaEntrega;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataConsulta = new java.sql.Date(System.currentTimeMillis());

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public double getVlTotalFrete() {
		return vlTotalFrete;
	}

	public void setVlTotalFrete(double vlTotalFrete) {
		this.vlTotalFrete = vlTotalFrete;
	}

	public int getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setDataPrevistaEntrega(int dataPrevistaEntrega) {
		this.dataPrevistaEntrega = dataPrevistaEntrega;
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	
	
}
