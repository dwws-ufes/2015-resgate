package br.com.resgate.controle;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;

import br.com.resgate.aplicacao.AplGerenciarDespacho;
import br.com.resgate.dominio.Despacho;

@Model
public class ControladorDespacho extends ControladorBase implements Controlador {
	
	@EJB
	private AplGerenciarDespacho aplGerenciarDespacho;
	
	public List<Despacho> getListagem() {
		return aplGerenciarDespacho.getLista();
	}
	
	@Override
	public String excluir() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String editar() {
		// TODO Auto-generated method stub
		return null;
	}

}
