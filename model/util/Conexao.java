package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {  //classe conexao criada para se conectar com o banco de dados
		//aqui 3 atributos: 	
	private String url = "jdbc:postgresql://localhost:5432/postgres"; 
	private String user = "postgres";
	private String password = "12345";
	
			
	public Connection getConexao() { 
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);				
			if(conn != null) {
				System.out.println("Banco conectado com sucesso!");
			}
		} 
		catch(Exception e) {
			System.out.println("Erro na conexão com o banco." + e.getMessage());
		}
		return conn;
	}
	
	public void fecharConexao(Connection conn) {
		try {
			if(conn != null)
				conn.close();
		}catch (SQLException e) {
			System.out.println("Erro ao fechar conexão " + e.getMessage());
		}
	}
	
	
	
	
	
	 
	
}
