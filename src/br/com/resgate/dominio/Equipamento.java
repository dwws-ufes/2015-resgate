package br.com.resgate.dominio;

import br.com.resgate.dominio.TipoEquipamento;
import br.com.resgate.persistencia.ObjetoPersistente;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Equipamento
 *
 */
@Entity

public class Equipamento extends ObjetoPersistente implements Serializable {

	private TipoEquipamento tipo;
	
	@ManyToOne
	private Ambulancia ambulancia;
	
	private static final long serialVersionUID = 1L;

	public Equipamento() {
		super();
	}   
	public TipoEquipamento getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoEquipamento tipo) {
		this.tipo = tipo;
	}
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}
	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
