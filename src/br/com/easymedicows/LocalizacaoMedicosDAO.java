package br.com.easymedicows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LocalizacaoMedicosDAO {

	public boolean inserirLocalizacao(LocalizacaoMedicos localizacao) {

		try {
			String sql = "INSERT INTO localizacao_medicos VALUES(?,?,?,?)";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, localizacao.getUser());
			stmt.setString(2, localizacao.getLatitude());
			stmt.setString(3, localizacao.getLongitude());
			stmt.setString(4, localizacao.getAtivo());

			stmt.executeUpdate();
			stmt.close();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean alterarLocalizacao(LocalizacaoMedicos localizacao) {
		try {
			String sql = "UPDATE localizacao_medicos SET latitude = ?, longitude = ?,"
					+ " ativo = ? WHERE medicos_user = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, localizacao.getLatitude());
			stmt.setString(2, localizacao.getLongitude());
			stmt.setString(3, localizacao.getAtivo());
			stmt.setString(4, localizacao.getUser());

			stmt.executeUpdate();
			stmt.close();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean excluirLocalizacaoByUser(String user) {
		try {
			String sql = "DELETE FROM localizacao_medicos WHERE medicos_user = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, user);

			stmt.executeUpdate();
			stmt.close();
			return true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	// Aqui irei retornar a lista de todos os médicos ativos e suas localizações
	public LinkedList<Medico> getLocalizacaoByMedicos() {
		try {
			String sql = "SELECT M.user, M.nome, M.especialidade,  M.qtdPacientesPorHora, M.agendaManha, M.agendaTarde, M.crm, LM.latitude, LM.longitude "
					+ "FROM medicos M, localizacao_medicos LM"
					+ " WHERE LM.medicos_user = M.user AND LM.ativo = ? ";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, "S");
			ResultSet rs = stmt.executeQuery();
			LinkedList<Medico> listaMedicos = new LinkedList<Medico>();
			while (rs.next()) {
				Medico medico = new Medico();
				medico.setUser(rs.getString(1));
				medico.setNome(rs.getString(2));
				medico.setEspecialidade(rs.getString(3));
				medico.setQtdPacientesPorHora(rs.getInt(4));
				medico.setAgendaManha(rs.getString(5));
				medico.setAgendaTarde(rs.getString(6));
				medico.setCrm(rs.getString(7));
				medico.setLocalizacao(new LocalizacaoMedicos());
				medico.getLocalizacao().setUser(medico.getUser());
				medico.getLocalizacao().setLatitude(rs.getString(8));
				medico.getLocalizacao().setLongitude(rs.getString(9));

				listaMedicos.add(medico);
			}
			rs.close();
			stmt.close();
			return listaMedicos;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public LocalizacaoMedicos getLocalizacaoByUser(String user) {
		try {
			String sql = "SELECT * FROM localizacao_medicos WHERE medicos_user = ?";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(
					sql);
			stmt.setString(1, user);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				LocalizacaoMedicos localizacao = new LocalizacaoMedicos();
				localizacao.setUser(rs.getString(1));
				localizacao.setLatitude(rs.getString(2));
				localizacao.setLongitude(rs.getString(3));
				localizacao.setAtivo(rs.getString(4));
				rs.close();
				stmt.close();
				return localizacao;
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
