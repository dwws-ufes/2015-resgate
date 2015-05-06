package br.com.resgate.dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.resgate.persistencia.ObjetoPersistente;

/**
 * Entity implementation class for Entity: Incidente
 *
 */
@Entity
public class Incidente extends ObjetoPersistente implements Serializable {

	private static final long serialVersionUID = 1L;
	private String endereco;
	private boolean incidenteAberto;
	private long latitude;
	private long longitude;
	private NivelPrioridade nivelPrioridade;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<FuncaoFuncionario> funcaoFuncionarios;
	//
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<TipoEquipamento> tipoEquipamentos;

	@OneToMany(mappedBy = "incidente")
	private Set<Chamada> chamadas;

	@ManyToOne
	private Hospital hospital;

	@OneToOne(mappedBy = "incidente")
	private Despacho despacho;

	public Incidente() {
		chamadas = new HashSet<Chamada>();
		tipoEquipamentos = new HashSet<TipoEquipamento>();
		funcaoFuncionarios = new HashSet<FuncaoFuncionario>();
		incidenteAberto = true;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public boolean getIncidenteAberto() {
		return this.incidenteAberto;
	}

	public void setIncidenteAberto(boolean incidenteAberto) {
		this.incidenteAberto = incidenteAberto;
	}

	public Set<Chamada> getChamadas() {
		return chamadas;
	}

	public void setChamadas(Set<Chamada> chamadas) {
		this.chamadas = chamadas;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Despacho getDespacho() {
		return despacho;
	}

	public void setDespacho(Despacho despacho) {
		this.despacho = despacho;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public NivelPrioridade getNivelPrioridade() {
		return nivelPrioridade;
	}

	public void setNivelPrioridade(NivelPrioridade nivelPrioridade) {
		this.nivelPrioridade = nivelPrioridade;
	}

	public Set<FuncaoFuncionario> getFuncaoFuncionarios() {
		return funcaoFuncionarios;
	}

	public void setFuncaoFuncionarios(Set<FuncaoFuncionario> funcaoFuncionarios) {
		this.funcaoFuncionarios = funcaoFuncionarios;
	}

	public Set<TipoEquipamento> getTipoEquipamentos() {
		return tipoEquipamentos;
	}

	public void setTipoEquipamentos(Set<TipoEquipamento> tipoEquipamentos) {
		this.tipoEquipamentos = tipoEquipamentos;
	}

	public String getEstadoIncidente() {
		if (incidenteAberto)
			return "Aberto";
		else
			return "Fechado";
	}
	
	
}
