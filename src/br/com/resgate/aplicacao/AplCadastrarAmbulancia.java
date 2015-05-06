
 /* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.aplicacao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Ambulancia;
import br.com.resgate.persistencia.AmbulanciaDAO;

/**
 *
 * @author thiago
 */
@Stateless
public class AplCadastrarAmbulancia {

	@EJB
	private AmbulanciaDAO ambulanciaDAO;

	public void criar(Ambulancia ambulancia) {
		ambulanciaDAO.salvar(ambulancia);
	}
	
	public void excluir(Ambulancia ambulancia){
		ambulanciaDAO.excluir(ambulancia);
	}

	public List<Ambulancia> getLista() {
		// TODO Auto-generated method stub
		return ambulanciaDAO.recuperatodos();
	}
	
	public void atualizar(Ambulancia ambulancia) {
		Ambulancia ambulanciaaux = ambulanciaDAO.recuperarPorId(ambulancia
				.getId());
		ambulanciaaux.setPlaca(ambulancia.getPlaca());
	}

}
