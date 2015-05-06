/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import br.com.resgate.dominio.Ambulancia;

/**
 *
 * @author thiago
 */
@Stateless
public class AmbulanciaDAO extends BaseDAO<Ambulancia> {

	@PersistenceContext
	private EntityManager em;

	
	protected EntityManager getEntityManager() {
		return em;
	}
	@Override
	protected Class<Ambulancia> getDomainClass() {
		// TODO Auto-generated method stub
		return Ambulancia.class;
	}
	
	public Ambulancia recuperarPorPlaca(String placa) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ambulancia> cq = cb.createQuery(Ambulancia.class);
		Root<Ambulancia> root = cq.from(Ambulancia.class);

		EntityType<Ambulancia> model = root.getModel();
		cq.where(cb.equal(root.get(model.getSingularAttribute("placa", String.class)), placa));

		Ambulancia ambulancia = null;
		try {
			ambulancia = em.createQuery(cq).getSingleResult();
		} catch (RuntimeException e) { }

		return ambulancia;
	}

}
