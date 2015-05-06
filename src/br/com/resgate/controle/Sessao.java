package br.com.resgate.controle;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.resgate.dominio.FuncaoFuncionario;
import br.com.resgate.dominio.Funcionario;
import br.com.resgate.persistencia.FuncionarioDAO;

@SessionScoped
@Named
public class Sessao implements Serializable {

	@EJB
	private FuncionarioDAO funcionarioDAO;

	private String usuario;

	private String senha;

	private static final long serialVersionUID = 1L;
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdmin() {
		return funcionarioLogado.getFuncoesFuncionario().contains(
				FuncaoFuncionario.ADMINSTRADOR);
	}

	public boolean isAtendente() {
		return funcionarioLogado.getFuncoesFuncionario().contains(
				FuncaoFuncionario.ATENDENTE);
	}

	public void limpar() {
		funcionarioLogado = null;
	}

	public void login() {
		try {
			Funcionario f = funcionarioDAO.recuperarPorLogin(usuario);
			if (f.getSenha().equals(senha)) {
				funcionarioLogado = f;
			}
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Infelizmente ocorreu um erro ao efetuar o login. Por favor verifque seu usuário e senha e tente novamente."));
		}
	}

	public String logout() {
		funcionarioLogado = null;
		return "/index.xhtml";
	}
}