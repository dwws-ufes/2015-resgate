/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.resgate.dominio.Hospital;

/**
 *
 * @author thiago
 */
@Stateless
public class HospitalDAO extends BaseDAO<Hospital> {

	@Override
	protected Class<Hospital> getDomainClass() {
		// TODO Auto-generated method stub
		return Hospital.class;
	}

	public Hospital recuperarPorNome(String nome) {
		String jpql = "select h from Hospital h where h.nome = '" + nome + "'";
		TypedQuery<Hospital> query = getEm().createQuery(jpql, Hospital.class);
		List<Hospital> hospitais = query.getResultList();
		if(hospitais != null && hospitais.size() > 0){
			return hospitais.get(0);
		}else{
			return null;
		}
		
	}
}
