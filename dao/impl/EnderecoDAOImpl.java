package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.EnderecoDAO;
import model.Endereco;
import model.util.Conexao;

public class EnderecoDAOImpl implements EnderecoDAO{
	
	Conexao conex = new Conexao(); //instanciado um objeto da classe conexão.

	@Override
	public int salvar(Endereco endereco) {
		
		Connection conn = conex.getConexao(); //aqui o objeto conexao chamou o método getConexao() que está na classe Conexao
		
		Integer id = null;
		
		String sql = "INSERT INTO ENDERECO (RUA, NUMERO, COMPLEMENTO)" + "VALUES(?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, endereco.getRua());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.execute();
			System.out.println("Endereço inserido com sucesso.");
			
			ResultSet rs = ps.getGeneratedKeys(); //resultSet serve para retornar algo do DB
			
			if(rs.next()) {
				id = rs.getInt(1);
				System.out.println("Insert ID - " + id);//mostrar o id que foi gerado no BD
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao inserir endereço no BD" + e.getMessage());
		}
		finally {
			conex.fecharConexao(conn);					
		}		
		return id; 
	}

	@Override
	public void alterar(Endereco endereco) {
		
		Connection conn = conex.getConexao();
		
		String sql = "UPDATE ENDERECO SET RUA = ?, NUMERO = ?, COMPLEMENTO = ?" 
		+ "WHERE ID_ENDERECO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, endereco.getRua());
			ps.setInt(2, endereco.getNumero());
			ps.setString(3, endereco.getComplemento());
			ps.setInt(4, endereco.getId());
			ps.executeUpdate();
			System.out.println("Endereco do ID " + endereco.getId() + " alterado com sucessso para rua: " + endereco.getRua());
		} 
		catch (SQLException e) {
			System.out.println("Erro ao alterar endereço." + e.getMessage());
		}
		finally {
			conex.fecharConexao(conn);
		}				
	}

	@Override
	public void remover(int id) {
		
		Connection conn = conex.getConexao();
		
		String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Endereço removido com sucesso.");
		} 
		catch (SQLException e) {
			System.out.println("Erro ao remover endereço do ID " + id + e.getMessage());		
		}
		finally {
			conex.fecharConexao(conn);
		}
	}

	@Override
	public Endereco pesquisar(int id) {
		
		Connection conn = conex.getConexao();
		
		Endereco endereco = new Endereco();
		
		String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				endereco.setRua(rs.getString("RUA"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				endereco.setId(rs.getInt("ID_ENDERECO"));			
			}
		} 
		catch (SQLException e) {
			System.out.println("Erro ao pesquisar Endereço " + e.getMessage());
		}
		finally {
			conex.fecharConexao(conn);
		}
		
		return endereco;
	}

	@Override
	public List<Endereco> listarTodos() {
		
		Connection conn = conex.getConexao();
		
		List<Endereco> retorno = new ArrayList<Endereco>();
		
		String sql = "SELECT * FROM ENDERECO";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setId(rs.getInt("ID_ENDERECO"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setComplemento(rs.getString("COMPLEMENTO"));
				endereco.setRua(rs.getString("RUA"));
				retorno.add(endereco);
			}			
		} 
		catch (SQLException e) {
			System.out.println("Erro ao pesquisar tabela Endereco" + e.getMessage());
		}
		finally {
			conex.fecharConexao(conn);
		}	
		return retorno;
	}

}
