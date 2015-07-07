/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.controle;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.com.resgate.aplicacao.AplCadastrarAmbulancia;
import br.com.resgate.dominio.Ambulancia;

/**
 *
 * @author thiago
 */
@Model
public class ControladorCadastrarAmbulancia extends ControladorBase implements Controlador{

	@EJB
	private AplCadastrarAmbulancia cadastrarAmbulancia;

	private Ambulancia ambulancia = new Ambulancia();

	public String salvar() {
		cadastrarAmbulancia.criar(ambulancia);
		return "/cadastrarAmbulancia/listar.xhtml";
	}
	
	public String atualizar() {
		cadastrarAmbulancia.atualizar(ambulancia);
		return "/cadastrarAmbulancia/listar.xhtml";
	}

	public Ambulancia getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}

	public List<Ambulancia> getListagem() {
		return cadastrarAmbulancia.getLista();
	}

//	public String dadoRDF(){
//		ambulancia = (Ambulancia) getDatatable().getRowData();
//		ConversaodeDados.dadoRDF("http://localhost:2020/data/Ambulancia/"+ String.valueOf(ambulancia.getId()));
//		return "http://localhost:2020/data/Ambulancia/"+ String.valueOf(ambulancia.getId());
//	}
	@Override
	public String excluir() {
		ambulancia = (Ambulancia) getDatatable().getRowData();
		cadastrarAmbulancia.excluir(ambulancia);
		return "/cadastrarAmbulancia/listar.xhtml";
	}

	@Override
	public String editar() {
		ambulancia = (Ambulancia) getDatatable().getRowData();
		return "/cadastrarAmbulancia/formedit.xhtml";
	}
}