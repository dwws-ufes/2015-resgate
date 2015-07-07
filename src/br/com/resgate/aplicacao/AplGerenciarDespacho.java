package br.com.resgate.aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.resgate.dominio.Ambulancia;
import br.com.resgate.dominio.Despacho;
import br.com.resgate.dominio.Equipamento;
import br.com.resgate.dominio.FuncaoFuncionario;
import br.com.resgate.dominio.Funcionario;
import br.com.resgate.dominio.TipoEquipamento;
import br.com.resgate.persistencia.DespachoDAO;

@Stateless
public class AplGerenciarDespacho {

	@EJB
	private DespachoDAO despachoDAO;

	@EJB
	private AplCadastrarAmbulancia aplCadastrarAmbulancia;

	@EJB
	private AplCadastrarEquipamento aplCadastrarEquipamento;

	@EJB
	private AplCadastrarFuncionario aplCadastarFuncionario;

	public List<Despacho> getLista() {
		return despachoDAO.recuperatodos();
	}

	public String despachar(Despacho despacho) {
		Set<FuncaoFuncionario> funcoesRequeridas = despacho.getIncidente()
				.getFuncaoFuncionarios();
		Set<TipoEquipamento> tiposRequeridos = despacho.getIncidente()
				.getTipoEquipamentos();
		
		List<Equipamento> equipamentosParaAtualizar = new ArrayList<Equipamento>();
		List<Funcionario> funcionariosParaAtualizar = new ArrayList<Funcionario>();

		List<Ambulancia> ambulancias = aplCadastrarAmbulancia.getLista();
		Ambulancia ambulanciaDespacho = null;
		for (Ambulancia ambulancia : ambulancias) {
			if (ambulancia.getDespacho() == null) {
				ambulanciaDespacho = ambulancia;
				break;
			}
		}

		if (ambulanciaDespacho == null) {
			despachoDAO.getEm().getTransaction().rollback();
			return "Não foi possível efetuar o despacho, pois não existem ambulâncias disponíveis";
		}

		List<Equipamento> equipamentos = aplCadastrarEquipamento.getLista();

		for (Equipamento equipamento : equipamentos) {
			if (equipamento.getAmbulancia() == null
					&& tiposRequeridos.size() > 0
					&& tiposRequeridos.contains(equipamento.getTipo())) {
				ambulanciaDespacho.getEquipamentos().add(equipamento);
				equipamento.setAmbulancia(ambulanciaDespacho);
				equipamentosParaAtualizar.add(equipamento);
			}
		}

		if (ambulanciaDespacho.getEquipamentos().size() != tiposRequeridos
				.size()) {
			despachoDAO.getEm().getTransaction().rollback();
			return "Não foi possível efetuar o despacho, pois não existem equipamentos disponíveis";
		}

		List<Funcionario> funcionarios = aplCadastarFuncionario.getLista();

		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getAmbulancia() == null
					&& funcoesRequeridas.size() > 0) {
				for (FuncaoFuncionario f : funcionario.getFuncoesFuncionario()) {
					if (funcoesRequeridas.contains(f)) {
						ambulanciaDespacho.getFuncionarios().add(funcionario);
						funcionario.setAmbulancia(ambulanciaDespacho);
						funcionariosParaAtualizar.add(funcionario);
						break;
					}
				}
			}
		}

		if (ambulanciaDespacho.getFuncionarios().size() != funcoesRequeridas
				.size()) {
			despachoDAO.getEm().getTransaction().rollback();
			return "Não foi possível efetuar o despacho, pois não existem funcionários disponíveis";
		}

		for(Funcionario f : funcionariosParaAtualizar){
			aplCadastarFuncionario.criar(f);
		}
		
		for(Equipamento e : equipamentosParaAtualizar){
			aplCadastrarEquipamento.criar(e);
		}
		
		despacho.setEstado("Em Atendimento");
		despacho.getAmbulancias().add(ambulanciaDespacho);
		despachoDAO.salvar(despacho);
		ambulanciaDespacho.setDespacho(despacho);
		aplCadastrarAmbulancia.criar(ambulanciaDespacho);
		return "";
	}

	public void finalizarDespacho(Despacho despacho) {
		Set<Ambulancia> ambulancias = despacho.getAmbulancias();
		for (Ambulancia ambulancia : ambulancias) {
			for (Funcionario f : ambulancia.getFuncionarios()) {
				f.setAmbulancia(null);
				aplCadastarFuncionario.criar(f);
			}
		}

		for (Ambulancia ambulancia : ambulancias) {
			for (Equipamento e : ambulancia.getEquipamentos()) {
				e.setAmbulancia(null);
				aplCadastrarEquipamento.criar(e);
			}
		}

		for (Ambulancia ambulancia : ambulancias) {
			ambulancia.setDespacho(null);
			aplCadastrarAmbulancia.criar(ambulancia);
		}

		despacho.setAmbulancias(null);
		despacho.setIncidente(null);
		despacho.setEstado("Encerrado");
		despachoDAO.salvar(despacho);
	}
}