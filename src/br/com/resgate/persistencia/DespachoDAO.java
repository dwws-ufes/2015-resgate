/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import javax.ejb.Stateless;

import br.com.resgate.dominio.Despacho;

/**
 *
 * @author thiago
 */
@Stateless
public class DespachoDAO extends BaseDAO<Despacho> {

	@Override
	protected Class<Despacho> getDomainClass() {
		// TODO Auto-generated method stub
		return Despacho.class;
	}

}
