package br.com.resgate.aplicacao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Incidente;
import br.com.resgate.persistencia.IncidenteDAO;

@Stateless
public class AplGerenciarIncidente {

	@EJB
	private IncidenteDAO incidenteDAO;

	public void criar(Incidente incidente) {
		incidenteDAO.salvar(incidente);
	}

	public List<Incidente> getLista() {
		// TODO Auto-generated method stub
		return incidenteDAO.recuperatodos();
	}
}
