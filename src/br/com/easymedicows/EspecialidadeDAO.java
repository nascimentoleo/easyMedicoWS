package br.com.easymedicows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class EspecialidadeDAO {
	public LinkedList<Especialidade> getEspecialidades() {
		try {
			String sql = "SELECT * FROM especialidades ORDER BY nome";
			PreparedStatement stmt = Conexao.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			LinkedList<Especialidade> listaEspecialidades = new LinkedList<>();

			while (rs.next()) {
				listaEspecialidades.add(new Especialidade(rs.getInt(1), rs.getString(2)));
			}
			rs.close();
			stmt.close();
			return listaEspecialidades;
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}

}
