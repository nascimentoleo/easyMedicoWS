package br.com.easymedicows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver"); 
		return DriverManager.getConnection("jdbc:mysql://localhost/easymedico","root","root");
	}
}
