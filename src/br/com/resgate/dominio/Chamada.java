package br.com.resgate.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.resgate.persistencia.ObjetoPersistente;

/**
 * Entity implementation class for Entity: Chamada
 *
 */
@Entity

public class Chamada extends ObjetoPersistente implements Serializable {

	
	private String numero;
	private String descricao;
	private String cidadao;
	
	@Temporal(TemporalType.TIMESTAMP) 
	private Date horaInicio;
	
	@ManyToOne
	private Funcionario funcionario;
	
	@ManyToOne
	private Incidente incidente;
	
	private static final long serialVersionUID = 1L;

	public Chamada() {
		super();
	}   
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}     
	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Incidente getIncidente() {
		return incidente;
	}
	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCidadao() {
		return cidadao;
	}
	public void setCidadao(String cidadao) {
		this.cidadao = cidadao;
	}
	@Override
	public String toString() {
		return this.cidadao + ", " + this.numero;
	}
	
	
}
