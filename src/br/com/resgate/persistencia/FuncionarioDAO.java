/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.resgate.dominio.Funcionario;

/**
 *
 * @author thiago
 */
@Stateless
public class FuncionarioDAO extends BaseDAO<Funcionario> {

	public Funcionario recuperarPorLogin(String login) {
		String jpql = "select f from Funcionario f where f.nome = '" + login
				+ "'";
		TypedQuery<Funcionario> query = getEm().createQuery(jpql,
				Funcionario.class);
		Funcionario f = query.getSingleResult();
		return f;
	}

	@Override
	protected Class<Funcionario> getDomainClass() {
		// TODO Auto-generated method stub
		return Funcionario.class;
	}
}
