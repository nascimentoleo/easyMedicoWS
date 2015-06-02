package br.com.easymedicows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

public class AgendamentoDAO {

	private Conexao conexao = new Conexao();

	public boolean inserirAgendamento(Agendamento agendamento) {

		try {
			String sql = "INSERT INTO agendamentos VALUES(null,?,?,?,?)";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, agendamento.getNomePaciente());
			stmt.setString(2, agendamento.getData());
			stmt.setString(3, agendamento.getHora());
			stmt.setString(4, agendamento.getUser());

			stmt.executeUpdate();

			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean alterarAgendamento(Agendamento agendamento) {
		try {
			String sql = "UPDATE agendamentos SET nomePaciente = ?, data = ?,"
					+ " hora = ?, medicos_user = ? WHERE idAgendamento = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, agendamento.getNomePaciente());
			stmt.setString(2, agendamento.getData());
			stmt.setString(3, agendamento.getHora());
			stmt.setString(4, agendamento.getUser());
			stmt.setInt(5, agendamento.getIdAgendamento());

			stmt.executeUpdate();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirAgendamentoById(int id) {
		try {
			String sql = "DELETE FROM agendamentos WHERE idAgendamento = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, id);

			stmt.executeUpdate();
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
			String sql = "SELECT * FROM agendamentos WHERE medicos_user = ? and data = ?";
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

				listaAgendas.add(agendamento);

			}
			return listaAgendas;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public Vector<String> getHorariosDisponiveisPorMedicoData(String user,
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
			sql = "SELECT qtdPacientesPorDia,qtdPacientesPorHora, agendaManha, agendaTarde FROM medicos WHERE user = ?";
			stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setString(1, user);
			ResultSet rsMedicos = stmt.executeQuery();
			rsMedicos.next();
			Medico medico = new Medico();
			medico.setQtdPacientesPorDia(rsMedicos.getInt(1));
			medico.setQtdPacientesPorHora(rsMedicos.getInt(2));
			medico.setAgendaManha(rsMedicos.getString(3));
			medico.setAgendaTarde(rsMedicos.getString(4));
			// Se for hora marcada
			Vector<String> horariosAgendados = new Vector<String>();
			// Pego a quantidade agendada
			int qtdAgendados = rsAgendamentos.getFetchSize();
			// Aqui irei percorrer o array de agendamentos, para saber quantos
			// estão agendados pela manhã e quantos pela tarde
			while (rsAgendamentos.next())
				horariosAgendados.add(rsAgendamentos.getString(1));
			
			return  Horarios.calculaHorariosDisponiveisPorDia(horariosAgendados,medico);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
