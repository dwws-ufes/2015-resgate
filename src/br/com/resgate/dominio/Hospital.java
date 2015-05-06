package br.com.resgate.dominio;

import br.com.resgate.persistencia.ObjetoPersistente;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Hospital
 *
 */
@Entity
public class Hospital extends ObjetoPersistente implements Serializable {

	private String nome;
	private String endereco;
	private static final long serialVersionUID = 1L;

	public Hospital() {
		super();
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return this.nome;
	}
	
//	<ui:decorate template="/arquivos/modelos/campo.xhtml">
//	<ui:define name="hospital">Hospital</ui:define>
//	<h:selectOneListbox  id="hospital"
//		value="#{controladorChamadaIncidente.hospital}">
//		<f:selectItems
//			value="#{controladorCadastrarHospital.listagem}" />
//	</h:selectOneListbox>
//</ui:decorate>
}