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

import br.com.resgate.aplicacao.AplCadastrarEquipamento;
import br.com.resgate.dominio.Equipamento;
import br.com.resgate.dominio.TipoEquipamento;

/**
 *
 * @author thiago
 */
@Model
public class ControladorCadastrarEquipamento extends ControladorBase implements Controlador{

	@EJB
	private AplCadastrarEquipamento cadastrarEquipamento;

	private Equipamento equipamento = new Equipamento();
	
	private static List<SelectItem> listaTipos;
	
	public String salvar() {
		cadastrarEquipamento.criar(equipamento);
		return "/cadastrarEquipamento/listar.xhtml";
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(Equipamento equipamento) {
		this.equipamento = equipamento;
	}

	public AplCadastrarEquipamento getCadastrarEquipamento() {
		return cadastrarEquipamento;
	}

	public void setCadastrarEquipamento(AplCadastrarEquipamento cadastrarEquipamento) {
		this.cadastrarEquipamento = cadastrarEquipamento;
	}

	public List<SelectItem> getListaTipos() {
		if (listaTipos == null) {
			listaTipos = new ArrayList<SelectItem>();
			TipoEquipamento[] tiposEquipamento = TipoEquipamento.values();
			for(TipoEquipamento tipoEquipamento : tiposEquipamento){
				listaTipos.add(new SelectItem(tipoEquipamento));
			}
		}
		return listaTipos;
	}

	public static void setListaTipos(List<SelectItem> listaTipos) {
		ControladorCadastrarEquipamento.listaTipos = listaTipos;
	}
	
	public List<Equipamento> getListagem(){
		return cadastrarEquipamento.getLista();
	}

	@Override
	public String excluir() {
		equipamento = (Equipamento) getDatatable().getRowData();
		cadastrarEquipamento.excluir(equipamento);
		return "/cadastrarEquipamento/listar.xhtml";
		
	}

	@Override
	public String editar() {
		equipamento = (Equipamento) getDatatable().getRowData();
		return "/cadastrarEquipamento/form.xhtml";
	}
}
