package br.com.resgate.dominio;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.resgate.persistencia.ObjetoPersistente;

/**
 * Entity implementation class for Entity: Ambulancia
 *
 */
@Entity
public class Ambulancia extends ObjetoPersistente implements Serializable {

	private String placa;

	@OneToMany(mappedBy = "ambulancia", fetch = FetchType.EAGER)
	private Set<Equipamento> equipamentos;

	@OneToMany(mappedBy = "ambulancia", fetch = FetchType.EAGER)
	private Set<Funcionario> funcionarios;

	@ManyToOne(fetch = FetchType.EAGER)
	private Despacho despacho;

	private static final long serialVersionUID = 1L;

	public Ambulancia() {
		super();
	}

	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Set<Equipamento> getEquipamentos() {
		return this.equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Despacho getDespacho() {
		return despacho;
	}

	public void setDespacho(Despacho despacho) {
		this.despacho = despacho;
	}
}
