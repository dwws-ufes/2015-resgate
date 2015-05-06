/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import javax.ejb.Stateless;

import br.com.resgate.dominio.Equipamento;

/**
 *
 * @author thiago
 */
@Stateless
public class EquipamentoDAO extends BaseDAO<Equipamento> {

	@Override
	protected Class<Equipamento> getDomainClass() {
		// TODO Auto-generated method stub
		return Equipamento.class;
	}

}
