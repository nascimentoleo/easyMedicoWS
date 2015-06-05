package br.com.easymedicows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

public class AgendamentoDAO {

	public boolean inserirAgendamento(Agendamento agendamento) {

		if(Data.dataValida(agendamento.getData())){
			try {
				String sql = "INSERT INTO agendamentos VALUES(null,?,?,?,?,?)";
				PreparedStatement stmt = Conexao.getConnection().prepareStatement(
						sql);
				stmt.setString(1, agendamento.getNomePaciente());
				stmt.setString(2, agendamento.getData());
				stmt.setString(3, agendamento.getHora());
				stmt.setString(4, agendamento.getUser());
				stmt.setString(5, agendamento.getImei());
				stmt.executeUpdate();
				
				stmt.close();
				return true;

			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		

	}

	public boolean alterarAgendamento(Agendamento agendamento) {
		if(Data.dataValida(agendamento.getData())){
			try {
				String sql = "UPDATE agendamentos SET nomePaciente = ?, data = ?,"
						+ " hora = ?, medicos_user = ? , IMEI = ? WHERE idAgendamento = ?";
				PreparedStatement stmt = Conexao.getConnection().prepareStatement(
						sql);
				stmt.setString(1, agendamento.getNomePaciente());
				stmt.setString(2, agendamento.getData());
				stmt.setString(3, agendamento.getHora());
				stmt.setString(4, agendamento.getUser());
				stmt.setString(5, agendamento.getImei());
				stmt.setInt(6, agendamento.getIdAgendamento());

				stmt.executeUpdate();
				stmt.close();
				return true;

			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return false;
		
	}

	public boolean excluirAgendamentoById(int id) {
		try {
			String sql = "DELETE FROM agendamentos WHERE idAgendamento = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.close();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public LinkedList<Agendamento> getAgendamentosPorMedicoData(String user,
			String data) {
		try {
			String sql = "SELECT * FROM agendamentos WHERE medicos_user = ? and data = ? order by hora";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, user);
			stmt.setString(2, data);
			ResultSet rs = stmt.executeQuery();
			LinkedList<Agendamento> listaAgendas = new LinkedList<Agendamento>();
			while (rs.next()) {
				Agendamento agendamento = new Agendamento();
				agendamento.setIdAgendamento(rs.getInt(1));
				agendamento.setNomePaciente(rs.getString(2));
				agendamento.setData(rs.getString(3));
				agendamento.setHora(rs.getString(4));
				agendamento.setUser(rs.getString(5));
				agendamento.setImei(rs.getString(6));

				listaAgendas.add(agendamento);

			}
			rs.close();
			stmt.close();
			return listaAgendas;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	public LinkedList<Agendamento> getAgendamentosPorIMEI(String imei) {
		try {
			String sql = "SELECT * FROM agendamentos WHERE IMEI = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, imei);
			ResultSet rs = stmt.executeQuery();
			LinkedList<Agendamento> listaAgendas = new LinkedList<Agendamento>();
			while (rs.next()) {
				Agendamento agendamento = new Agendamento();
				agendamento.setIdAgendamento(rs.getInt(1));
				agendamento.setNomePaciente(rs.getString(2));
				agendamento.setData(rs.getString(3));
				agendamento.setHora(rs.getString(4));
				agendamento.setUser(rs.getString(5));
				agendamento.setImei(rs.getString(6));

				listaAgendas.add(agendamento);

			}
			rs.close();
			stmt.close();
			
			return listaAgendas;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public LinkedList<Horario> getHorariosDisponiveisPorMedicoData(String user,
			String data) {
		// Primeiro pego os agendamentos para aquele médico
		String sql = "SELECT hora from agendamentos WHERE medicos_user = ? and data = ?";
		PreparedStatement stmt;
		try {
			stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setString(1, user);
			stmt.setString(2, data);
			ResultSet rsAgendamentos = stmt.executeQuery();
			// Agora pego os limites de agendamentos para aquele médico
			sql = "SELECT qtdPacientesPorHora, agendaManha, agendaTarde FROM medicos WHERE user = ?";
			stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setString(1, user);
			ResultSet rsMedicos = stmt.executeQuery();
			rsMedicos.next();
			Medico medico = new Medico();
			medico.setQtdPacientesPorHora(rsMedicos.getInt(1));
			medico.setAgendaManha(rsMedicos.getString(2));
			medico.setAgendaTarde(rsMedicos.getString(3));
			// Se for hora marcada
			Vector<String> horariosAgendados = new Vector<String>();
			// Pego a quantidade agendada
			int qtdAgendados = rsAgendamentos.getFetchSize();
			// Aqui irei percorrer o array de agendamentos, para saber quantos
			// estão agendados pela manhã e quantos pela tarde
			while (rsAgendamentos.next())
				horariosAgendados.add(rsAgendamentos.getString(1));
			rsMedicos.close();
			rsAgendamentos.close();
			stmt.close();
			
			return GerenciaHorarios.calculaHorariosDisponiveisPorDia(
					horariosAgendados, medico);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
