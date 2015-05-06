/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.aplicacao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Equipamento;
import br.com.resgate.persistencia.EquipamentoDAO;

/**
 *
 * @author thiago
 */
@Stateless
public class AplCadastrarEquipamento {

	@EJB
	private EquipamentoDAO equipamentoDAO;

	public void criar(Equipamento equipamento) {
		equipamentoDAO.salvar(equipamento);
	}
	
	public void excluir(Equipamento equipamento){
		equipamentoDAO.excluir(equipamento);
	}

	public List<Equipamento> getLista() {
		// TODO Auto-generated method stub
		return equipamentoDAO.recuperatodos();
	}
}
