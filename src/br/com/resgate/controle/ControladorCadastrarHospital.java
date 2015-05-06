/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.controle;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;

import br.com.resgate.aplicacao.AplCadastrarHospital;
import br.com.resgate.dominio.Hospital;

/**
 *
 * @author thiago
 */
@Model
public class ControladorCadastrarHospital extends ControladorBase implements Controlador{

	@EJB
	private AplCadastrarHospital cadastrarHospital;

	private Hospital hospital = new Hospital();
	
	private static List<SelectItem> listaHospitais;

	public String salvar() {
		cadastrarHospital.criar(hospital);
		return "/cadastrarHospital/listar.xhtml";
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
	
	public List<Hospital> getListagem(){
		return cadastrarHospital.getLista();
	}

	public List<SelectItem> getListaHospitais() {
		if (listaHospitais == null) {
			listaHospitais = new ArrayList<SelectItem>();
			List<Hospital> lstHospital = cadastrarHospital.getLista();
			for(Hospital hospital : lstHospital){
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
}