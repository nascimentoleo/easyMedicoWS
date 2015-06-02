package br.com.easymedicows;

import java.sql.Date;
import java.sql.Time;


public class Agendamento {
	private int idAgendamento;
	private String nomePaciente;
	private String data;
	private String hora;
	private String user;
	
	public int getIdAgendamento() {
		return idAgendamento;
	}
	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	

}
