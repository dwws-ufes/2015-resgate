/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;

import br.com.resgate.aplicacao.AplCadastrarHospital;
import br.com.resgate.dominio.Hospital;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;

/**
 *
 * @author thiago
 */

@Model
public class ControladorCadastrarHospital extends ControladorBase implements Serializable,
		Controlador {

	@EJB
	private AplCadastrarHospital cadastrarHospital;

	private Hospital hospital = new Hospital();

	private static List<SelectItem> listaHospitais;
	
	private boolean hospitalExiste = false;

	public String salvar() {
		cadastrarHospital.criar(hospital);
		return "/cadastrarHospital/listar.xhtml";
	}

	public String cadastrarTodosHospitais() {
		String query = "PREFIX dcterms: <http://purl.org/dc/terms/> "
				+ "PREFIX dbpprop: <http://dbpedia.org/property/> "
				+ "PREFIX dbpedia-owl: <http://dbpedia.org/ontology/> "
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
				+ "SELECT ?name where { "
				+ "?x dcterms:subject <http://pt.dbpedia.org/resource/Categoria:Hospitais_da_cidade_de_São_Paulo> . "
				+ "?x rdfs:label ?name " + "}";

		QueryExecution queryExecution = QueryExecutionFactory.sparqlService(
				"http://pt.dbpedia.org/sparql", query);
		ResultSet results = queryExecution.execSelect();
		while (results.hasNext()) {
			QuerySolution querySolution = results.next();
			Literal literal = querySolution.getLiteral("name");
			Hospital hospital = new Hospital();
			hospital.setNome("" + literal.getValue());
			hospital.setEndereco("");
			cadastrarHospital.criar(hospital);
		}
		return "/cadastrarHospital/listar.xhtml";
	}

	public void existeHospital() {
		Hospital h = null;
		if (!hospital.getNome().isEmpty()) {
			h = cadastrarHospital.recuperarPorNome(hospital.getNome());
			if(h != null){
				hospital = h;
			}
		}
	}

	public String atualizar() {
		cadastrarHospital.atualizar(hospital);
		return "/cadastrarHospital/listar.xhtml";
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public List<Hospital> getListagem() {
		return cadastrarHospital.getLista();
	}

	public List<SelectItem> getListaHospitais() {
		if (listaHospitais == null) {
			listaHospitais = new ArrayList<SelectItem>();
			List<Hospital> lstHospital = cadastrarHospital.getLista();
			for (Hospital hospital : lstHospital) {
				listaHospitais.add(new SelectItem(hospital));
			}
		}
		return listaHospitais;
	}

	public static void setListaHospitais(List<SelectItem> listaHospitais) {
		ControladorCadastrarHospital.listaHospitais = listaHospitais;
	}

	@Override
	public String excluir() {
		hospital = (Hospital) getDatatable().getRowData();
		cadastrarHospital.excluir(hospital);
		return "/cadastrarHospital/listar.xhtml";
	}

	@Override
	public String editar() {
		hospital = (Hospital) getDatatable().getRowData();
		return "/cadastrarHospital/formedit.xhtml";
	}

	public boolean isHospitalExiste() {
		return hospitalExiste;
	}

	public void setHospitalExiste(boolean hospitalExiste) {
		this.hospitalExiste = hospitalExiste;
	}
}