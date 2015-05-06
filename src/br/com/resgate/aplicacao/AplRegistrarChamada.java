/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.aplicacao;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Chamada;
import br.com.resgate.persistencia.ChamadaDAO;

/**
 *
 * @author thiago
 */
@Stateless
public class AplRegistrarChamada {

	@EJB
	private ChamadaDAO chamadaDAO;

	public void criar(Chamada chamada) {
		chamadaDAO.salvar(chamada);
		
	}
	
	public List<Chamada> getLista() {
		return chamadaDAO.recuperatodos();
	}

}
