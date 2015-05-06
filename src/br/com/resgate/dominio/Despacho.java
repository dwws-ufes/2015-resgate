package br.com.resgate.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.resgate.persistencia.ObjetoPersistente;

/**
 * Entity implementation class for Entity: Despacho
 *
 */
@Entity
public class Despacho extends ObjetoPersistente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHora;

	@OneToOne
	private Incidente incidente;

	@OneToMany(mappedBy = "despacho", fetch = FetchType.EAGER)
	private Set<Ambulancia> ambulancias;
	
	private String estado;

	public Despacho() {
		super();
		ambulancias = new HashSet<Ambulancia>();
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public Set<Ambulancia> getAmbulancias() {
		return ambulancias;
	}

	public void setAmbulancias(Set<Ambulancia> ambulancias) {
		this.ambulancias = ambulancias;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIdsAmbulancias() {
		String ids = "";
		for (Ambulancia ambulancia : ambulancias) {
			ids += ambulancia.getId().toString();
			ids += ", ";
		}
		if(ids.isEmpty()){
			return "";
		}
		return ids.substring(0, ids.length() - 2);
	}

	public String getEquipamentosAmbulancias() {
		String nomes = "";
		for (Ambulancia ambulancia : ambulancias) {
			for (Equipamento e : ambulancia.getEquipamentos()) {
				nomes += e.getTipo();
				nomes += ", ";
			}
		}
		if(nomes.isEmpty()){
			return "";
		}
		return nomes.substring(0, nomes.length() - 2);
	}

	public String getfuncionariosAmbulancias() {
		String nomes = "";
		for (Ambulancia ambulancia : ambulancias) {
			for (Funcionario f : ambulancia.getFuncionarios()) {
				nomes += f.getNome();
				nomes += ", ";
			}
		}
		if(nomes.isEmpty()){
			return "";
		}
		return nomes.substring(0, nomes.length() - 2);
	}
}
