package br.com.resgate.dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.resgate.persistencia.ObjetoPersistente;

/**
 * Entity implementation class for Entity: Funcionario
 *
 */
@Entity

public class Funcionario extends ObjetoPersistente implements Serializable {

	
	private String nome;
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<FuncaoFuncionario> funcoesFuncionario;
	
	@ManyToOne
	private Ambulancia ambulancia;
	
	@OneToMany(mappedBy="funcionario")
	private Set<Chamada> chamadas;
	
	private static final long serialVersionUID = 1L;

	public Funcionario() {
		super();
		funcoesFuncionario = new HashSet<FuncaoFuncionario>();
		chamadas = new HashSet<Chamada>();
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}   
	public Set<FuncaoFuncionario> getFuncoesFuncionario() {
		return this.funcoesFuncionario;
	}

	public void setFuncoesFuncionario(Set<FuncaoFuncionario> funcoesFuncionario) {
		this.funcoesFuncionario = funcoesFuncionario;
	}
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}
	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	public Set<Chamada> getChamadas() {
		return chamadas;
	}
	public void setChamadas(Set<Chamada> chamadas) {
		this.chamadas = chamadas;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
   
}
