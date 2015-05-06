/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import javax.ejb.Stateless;

import br.com.resgate.dominio.Incidente;

/**
 *
 * @author thiago
 */
@Stateless
public class IncidenteDAO extends BaseDAO<Incidente> {

	@Override
	protected Class<Incidente> getDomainClass() {
		// TODO Auto-generated method stub
		return Incidente.class;
	}

}
