/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.controle;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;

import br.com.resgate.aplicacao.AplCadastrarFuncionario;
import br.com.resgate.dominio.FuncaoFuncionario;
import br.com.resgate.dominio.Funcionario;

/**
 *
 * @author thiago
 */
@Model
public class ControladorCadastrarFuncionario extends ControladorBase implements Controlador{

	@EJB
	private AplCadastrarFuncionario cadastrarFuncionario;

	private Funcionario funcionario = new Funcionario();
	
	private List<SelectItem> listaOpcoesFuncoes;
	private FuncaoFuncionario[] funcoesFuncionario;

	public String salvar() {
		for (FuncaoFuncionario s : funcoesFuncionario){
			funcionario.getFuncoesFuncionario().add(s);
		}
		cadastrarFuncionario.criar(funcionario);
		return "/cadastrarFuncionario/listar.xhtml";
	}
	
	public String atualizar() {
		for (FuncaoFuncionario s : funcoesFuncionario){
			funcionario.getFuncoesFuncionario().add(s);
		}
		cadastrarFuncionario.atualizar(funcionario);
		return "/cadastrarFuncionario/listar.xhtml";
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<SelectItem> getListaOpcoesFuncoes() {
		if (listaOpcoesFuncoes == null) {
			listaOpcoesFuncoes = new ArrayList<SelectItem>();
			FuncaoFuncionario[] funcoesFuncionario = FuncaoFuncionario.values();
			for(FuncaoFuncionario funcaoFuncionario : funcoesFuncionario){
				listaOpcoesFuncoes.add(new SelectItem(funcaoFuncionario));
			}
		}
		return listaOpcoesFuncoes;
	}

	public void setListaOpcoesFuncoes(List<SelectItem> listaOpcoesFuncoes) {
		this.listaOpcoesFuncoes = listaOpcoesFuncoes;
	}

	public FuncaoFuncionario[] getFuncoesFuncionario() {
		return funcoesFuncionario;
	}

	public void setFuncoesFuncionario(FuncaoFuncionario[] funcoesFuncionario) {
		this.funcoesFuncionario = funcoesFuncionario;
	}
	
	public List<Funcionario> getListagem(){
		return cadastrarFuncionario.getLista();
	}

	@Override
	public String excluir() {
		funcionario = (Funcionario) getDatatable().getRowData();
		cadastrarFuncionario.excluir(funcionario);
		return "/cadastrarFuncionario/listar.xhtml";
	}

	@Override
	public String editar() {
		funcionario = (Funcionario) getDatatable().getRowData();
		return "/cadastrarFuncionario/formedit.xhtml";
	}
}