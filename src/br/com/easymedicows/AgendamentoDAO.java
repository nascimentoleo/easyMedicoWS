package br.com.easymedicows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class AgendamentoDAO {

	private Conexao conexao = new Conexao();

	public boolean inserirAgendamento(Agendamento agendamento) {

		try {
			String sql = "INSERT INTO agendamentos VALUES(null,?,?,?,?,?)";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, agendamento.getNomePaciente());
			stmt.setString(2, agendamento.getData());
			stmt.setInt(3, agendamento.getOrdem());
			stmt.setString(4, agendamento.getHora());
			stmt.setString(5, agendamento.getUser());

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
			String sql = "ALTER TABLE agendamentos SET nomePaciente = ?, data = ?,"
					+ " ordem = ?, hora = ?, medicos_user = ? WHERE idAgendamento = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, agendamento.getNomePaciente());
			stmt.setString(2, agendamento.getData());
			stmt.setInt(3, agendamento.getOrdem());
			stmt.setString(4, agendamento.getHora());
			stmt.setString(5, agendamento.getUser());
			stmt.setInt(6, agendamento.getIdAgendamento());

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

	public LinkedList<Agendamento> getAgendamentosByMedicoAndDate(String user, String data) {
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
				agendamento.setOrdem(rs.getInt(4));
				agendamento.setHora(rs.getString(5));
				agendamento.setUser(rs.getString(6));
				
				listaAgendas.add(agendamento);

			} 
			return listaAgendas;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
