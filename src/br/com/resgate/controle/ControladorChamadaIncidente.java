/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.resgate.aplicacao.AplCadastrarHospital;
import br.com.resgate.aplicacao.AplGerenciarDespacho;
import br.com.resgate.aplicacao.AplGerenciarIncidente;
import br.com.resgate.aplicacao.AplRegistrarChamada;
import br.com.resgate.dominio.Chamada;
import br.com.resgate.dominio.Despacho;
import br.com.resgate.dominio.FuncaoFuncionario;
import br.com.resgate.dominio.Hospital;
import br.com.resgate.dominio.Incidente;
import br.com.resgate.dominio.NivelPrioridade;
import br.com.resgate.dominio.TipoEquipamento;
import br.com.resgate.persistencia.ObjetoPersistente;

/**
 *
 * @author thiago
 */
@Model
public class ControladorChamadaIncidente extends ControladorBase implements
		Controlador {

	@EJB
	private AplRegistrarChamada registrarChamada;

	@EJB
	private AplGerenciarIncidente gerenciarIncidente;

	@EJB
	private AplCadastrarHospital aplHospital;

	@EJB
	private AplGerenciarDespacho aplGerenciarDespacho;

	private Chamada chamada = new Chamada();

	private boolean chamadaEmergencial = false;
	private Incidente incidente = new Incidente();

	private static List<SelectItem> listaHospitais;
	private static List<SelectItem> listaPrioridades;

	private FuncaoFuncionario[] funcoesFuncionario;
	private TipoEquipamento[] tiposEquipamentos;

	private Hospital hospital;
	
	private String nomeHospital;

	public String salvar() {
		chamadaEmergencial = false;
		chamada.setHoraInicio(new Date());
		registrarChamada.criar(chamada);

		incidente.getChamadas().add(chamada);

		for (FuncaoFuncionario s : funcoesFuncionario) {
			incidente.getFuncaoFuncionarios().add(s);
		}

		for (TipoEquipamento s : tiposEquipamentos) {
			incidente.getTipoEquipamentos().add(s);
		}
		Hospital h = aplHospital.recuperarPorNome(nomeHospital);
		incidente.setHospital(h);
		gerenciarIncidente.criar(incidente);

		chamada.setIncidente(incidente);
		registrarChamada.criar(chamada);
		return "/incidente/listar.xhtml";
	}

	public Chamada getChamada() {
		return chamada;
	}

	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}

	public boolean isChamadaEmergencial() {
		return chamadaEmergencial;
	}

	public void setChamadaEmergencial(boolean chamadaEmergencial) {
		this.chamadaEmergencial = chamadaEmergencial;
	}

	public List<SelectItem> getListaPrioridades() {
		if (listaPrioridades == null) {
			listaPrioridades = new ArrayList<SelectItem>();
			NivelPrioridade[] niveisPrioridade = NivelPrioridade.values();
			for (NivelPrioridade nivelPrioridade : niveisPrioridade) {
				listaPrioridades.add(new SelectItem(nivelPrioridade));
			}
		}
		return listaPrioridades;
	}

	public Incidente getIncidente() {
		return incidente;
	}

	public void setIncidente(Incidente incidente) {
		this.incidente = incidente;
	}

	public static void setListaPrioridades(List<SelectItem> listaPrioridades) {
		ControladorChamadaIncidente.listaPrioridades = listaPrioridades;
	}

	public List<Incidente> getListagem() {
		return gerenciarIncidente.getLista();
	}

	public List<Chamada> getChamadas() {
		return registrarChamada.getLista();
	}

	public List<Incidente> getIncidentes() {
		return gerenciarIncidente.getLista();
	}

	public List<SelectItem> getListaHospitais() {
		listaHospitais = new ArrayList<SelectItem>();
		List<Hospital> lstHospital = aplHospital.getLista();
		for (Hospital hospital : lstHospital) {
			listaHospitais.add(new SelectItem(hospital));
		}
		return listaHospitais;
	}

	public FuncaoFuncionario[] getFuncoesFuncionario() {
		return funcoesFuncionario;
	}

	public void setFuncoesFuncionario(FuncaoFuncionario[] funcoesFuncionario) {
		this.funcoesFuncionario = funcoesFuncionario;
	}

	public TipoEquipamento[] getTiposEquipamentos() {
		return tiposEquipamentos;
	}

	public void setTiposEquipamentos(TipoEquipamento[] tiposEquipamentos) {
		this.tiposEquipamentos = tiposEquipamentos;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String excluir() {
		return "";
	}

	@Override
	public String editar() {
		ObjetoPersistente obj = (ObjetoPersistente) getDatatable().getRowData();
		if (obj instanceof Incidente) {
			incidente = (Incidente) obj;
			return "/incidente/form.xhtml";
		} else {
			chamada = (Chamada) obj;
			return "/registrarChamada/form.xhtml";
		}
	}

	public String despachar() {
		incidente = (Incidente) getDatatable().getRowData();
		Despacho despacho = new Despacho();
		despacho.setIncidente(incidente);
		despacho.setDataHora(new Date());

		String resultado = aplGerenciarDespacho.despachar(despacho);
		if (!resultado.isEmpty()) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(resultado));
		}
		return "/incidente/listar.xhtml";
	}
	
	public String finalizarDespacho(){
		incidente = (Incidente) getDatatable().getRowData();
		aplGerenciarDespacho.finalizarDespacho(incidente.getDespacho());
		incidente.setDespacho(null);
		incidente.setIncidenteAberto(false);
		gerenciarIncidente.criar(incidente);
		return "/incidente/listar.xhtml";
	}

	public String getNomeHospital() {
		return nomeHospital;
	}

	public void setNomeHospital(String nomeHospital) {
		this.nomeHospital = nomeHospital;
	}
}
