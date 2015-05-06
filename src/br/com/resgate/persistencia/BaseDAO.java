/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author thiago
 */
public abstract class BaseDAO<T extends ObjetoPersistente> {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	protected abstract Class<T> getDomainClass();

	public T salvar(T obj) {
		if (obj.isPersistente()) {
			obj = em.merge(obj);
		} else {
			em.persist(obj);
		}
		return obj;
	}

	public List<T> recuperatodos() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(getDomainClass()));
		return em.createQuery(cq).getResultList();
	}

	public void excluir(ObjetoPersistente obj) {
		em.remove(em.merge(obj));
	}
	
	public T recuperarPorId(Long id) {
		return (T) em.find(getDomainClass(), id);
	}
}