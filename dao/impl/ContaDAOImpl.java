package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.ContaDAO;
import model.Conta;
import model.util.Conexao;

public class ContaDAOImpl implements ContaDAO{
	
		Conexao conexao = new Conexao(); //aqui ele instanciou um objeto da classe Conexao!?	
		
	@Override
	public void salvar(Conta conta) {
		
		Connection conn = conexao.getConexao(); //aqui chamou o método "getConexao()"
		
		String sql = "INSERT INTO CONTA(NUMERO, SALDO, LIMITE)" + "VALUES(?, ?, ?)";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, conta.getNumero());
			ps.setDouble(2, conta.getSaldo());
			ps.setDouble(3, conta.getLimite());
			ps.execute(); //aqui seria a execução do código SQL lá no banco de dados (setinha laranja)
			System.out.println("Conta inserida com sucesso.");
		}
		catch (SQLException e) {
			System.out.println("Erro ao inserir conta no banco" + e.getMessage());	
		}
		finally {
			conexao.fecharConexao(conn);
		}						
	}

	@Override
	public void alterar(Conta conta) {
		
		Connection conn = conexao.getConexao();		
		
		String sql = "UPDATE CONTA SET SALDO = ?, LIMITE = ? WHERE NUMERO = ? ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, conta.getSaldo());
			ps.setDouble(2, conta.getLimite());
			ps.setInt(3, conta.getNumero());
			ps.execute();
			System.out.println("Update tabela Conta realizado com sucesso.");			
		} 
		catch (SQLException e) {
			System.out.println("Falha em realizar Update na tabela Conta" + e.getMessage());
		}
		finally {
			conexao.fecharConexao(conn);
		}
		
	}

	@Override
	public void remover(int numero) {
		
		Connection conn = conexao.getConexao();
		
		String sql = "DELETE FROM CONTA WHERE NUMERO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ps.execute();
			System.out.println("Conta deletada com sucesso.");		
		}
		catch(SQLException e) {
			System.out.println("Erro ao deletar conta no banco" + e.getMessage());			
		}
		catch (Exception e) {
			System.out.println("Erro ao deletar conta no banco" + e.getMessage());	
		}
		finally {
			conexao.fecharConexao(conn);
		}							
	}

	@Override
	public Conta pesquisar(int numero) {
		
		Connection conn = conexao.getConexao();
		
		Conta conta = new Conta(); //aqui instanciou um objeto da classe Conta.
		
		String sql = "SELECT * FROM CONTA WHERE NUMERO = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, numero);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { // Enquanto houver próximo(next), execulte esse bloco de código.
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
			}
		}
		catch(Exception e) {
			System.out.println("Erro ao pesquisar conta" + e.getMessage());
		}
		finally {
			conexao.fecharConexao(conn);
		}				
		return conta; //aqui o "return" está retornando um objeto da classe "Conta". Por isso, na assinatura deste método, 
	}				// usou a classe "Conta" para o return retormar um Objeto da classe Conta.  

	@Override
	public List<Conta> listarTodos() {  //na assinatura desse métudo tem um List<Conta> para o return poder retornar uma lista.
		
		Connection conn = conexao.getConexao();
		
		List<Conta> retorno = new ArrayList<Conta>();
		
		String sql = "SELECT * FROM CONTA";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Conta conta = new Conta();
				conta.setNumero(rs.getInt("NUMERO"));
				conta.setSaldo(rs.getDouble("SALDO"));
				conta.setLimite(rs.getDouble("LIMITE"));
				retorno.add(conta);		
			}
		} 
		catch (SQLException e) {
			System.out.println("Erro ao apresentar lista de Contas" + e.getMessage());
		}
		finally {
			conexao.fecharConexao(conn);
		}		
		return retorno;
	}

	
	
	
	
	
	
	
	
	
	
}
