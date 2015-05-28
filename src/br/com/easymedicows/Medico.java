package br.com.easymedicows;

import java.util.LinkedList;

public class Medico {
  
	private String user;
	private String password;
	private String nome;
	private String especialidade;
	private String tipoAtendimento;
	private int QtdPacientesPorDia;
	private int QtdPacientesPorHora;
	private LocalizacaoMedicos localizacao;
	private LinkedList<Agendamento> agendamentos;
	

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(String tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public int getQtdPacientesPorDia() {
		return QtdPacientesPorDia;
	}

	public void setQtdPacientesPorDia(int qtdPacientesPorDia) {
		QtdPacientesPorDia = qtdPacientesPorDia;
	}

	public int getQtdPacientesPorHora() {
		return QtdPacientesPorHora;
	}

	public void setQtdPacientesPorHora(int qtdPacientesPorHora) {
		QtdPacientesPorHora = qtdPacientesPorHora;
	}

	public LocalizacaoMedicos getLocalizacao() {
		return localizacao;
	} 

	public void setLocalizacao(LocalizacaoMedicos localizacao) {
		this.localizacao = localizacao;
	}

	public LinkedList<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(LinkedList<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	} 
	
	
	
	
	
	

}
