package br.com.easymedicows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class MedicoDAO {
	
	
	private Conexao conexao = new Conexao();
	
	public boolean inserirMedico(Medico medico) {
		
		try {
			String sql = "INSERT INTO medicos VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
			
			stmt.setString(1, medico.getUser());
			stmt.setString(2, medico.getPassword());
			stmt.setString(3, medico.getNome());
			stmt.setString(4, medico.getEspecialidade());
			stmt.setInt(5, medico.getQtdPacientesPorDia());
			stmt.setInt(6, medico.getQtdPacientesPorHora());
			stmt.setString(7, medico.getAgendaManha());
			stmt.setString(8, medico.getAgendaTarde());
			
			stmt.executeUpdate();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		

	}

	
	public boolean alterarMedico(Medico medico) {
		try {
			String sql = "UPDATE medicos SET nome = ?, especialidade = ?,"
					+ " qtdPacientePorDia = ?, qtdPacientePorHora = ?, agendaManha = ?, agendaTarde = ? WHERE user = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setString(1, medico.getNome());
			stmt.setString(2, medico.getEspecialidade());
			stmt.setInt(3, medico.getQtdPacientesPorDia());
			stmt.setInt(4, medico.getQtdPacientesPorHora());
			stmt.setString(5, medico.getAgendaManha());
			stmt.setString(6, medico.getAgendaTarde());
			stmt.setString(7, medico.getUser());

			stmt.executeUpdate();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirMedicoByUser(String user) {
		try {
			String sql = "DELETE FROM medicos WHERE user = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setString(1, user);

			stmt.executeUpdate();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public LinkedList<Medico> getMedicos() {

		try {
			String sql = "SELECT * FROM medicos";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			LinkedList<Medico> listaMedicos = new LinkedList<Medico>();
			while (rs.next()) {
				Medico medico = new Medico();
				medico.setUser(rs.getString(1));
				medico.setPassword(rs.getString(2));
				medico.setNome(rs.getString(3));
				medico.setEspecialidade(rs.getString(4));
				medico.setQtdPacientesPorDia(rs.getInt(5));
				medico.setQtdPacientesPorHora(rs.getInt(6));
				medico.setAgendaManha(rs.getString(7));
				medico.setAgendaTarde(rs.getString(8));
				listaMedicos.add(medico);
			}
			return listaMedicos;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public Medico getMedicoByUser(String user){
		try {
			String sql = "SELECT * FROM medicos WHERE user = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Medico medico = new Medico();
				medico.setUser(rs.getString(1));
				medico.setPassword(rs.getString(2));
				medico.setNome(rs.getString(3));
				medico.setEspecialidade(rs.getString(4));
				medico.setQtdPacientesPorDia(rs.getInt(5));
				medico.setQtdPacientesPorHora(rs.getInt(6));
				medico.setAgendaManha(rs.getString(7));
				medico.setAgendaTarde(rs.getString(8));
				
				return medico;
			} else
				return null;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	} 
	
	
}
