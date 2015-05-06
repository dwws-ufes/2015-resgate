/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.resgate.aplicacao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Funcionario;
import br.com.resgate.persistencia.FuncionarioDAO;

/**
 *
 * @author thiago
 */
@Stateless
public class AplCadastrarFuncionario {

	@EJB
	private FuncionarioDAO funcionarioDAO;

	public void criar(Funcionario funcionario) {
		funcionarioDAO.salvar(funcionario);
	}
	
	public void excluir(Funcionario funcionario){
		funcionarioDAO.excluir(funcionario);
	}

	public List<Funcionario> getLista() {
		// TODO Auto-generated method stub
		return funcionarioDAO.recuperatodos();
	}
	
	public void atualizar(Funcionario funcionario) {
		Funcionario funcionarioaux = funcionarioDAO.recuperarPorId(funcionario
				.getId());
		funcionarioaux.setNome(funcionario.getNome());
		funcionarioaux.setSenha(funcionario.getSenha());
		funcionarioaux.setFuncoesFuncionario(funcionario.getFuncoesFuncionario());
	}
}
