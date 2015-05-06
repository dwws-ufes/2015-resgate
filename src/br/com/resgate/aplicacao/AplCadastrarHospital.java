/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.aplicacao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Hospital;
import br.com.resgate.persistencia.HospitalDAO;

/**
 *
 * @author thiago
 */
@Stateless
public class AplCadastrarHospital {

	@EJB
	private HospitalDAO hospitalDAO;

	public void criar(Hospital hospital) {
		hospitalDAO.salvar(hospital);
	}

	public void excluir(Hospital hospital) {
		hospitalDAO.excluir(hospital);
	}

	public List<Hospital> getLista() {
		return hospitalDAO.recuperatodos();
	}

	public Hospital recuperarPorNome(String nomeHospital) {
		Hospital hospital = hospitalDAO.recuperarPorNome(nomeHospital);
		return hospital;
	}

	public void atualizar(Hospital hospital) {
		Hospital hospitalaux = hospitalDAO.recuperarPorId(hospital.getId());
		hospitalaux.setNome(hospital.getNome());
		hospitalaux.setEndereco(hospital.getEndereco());
	}
}
