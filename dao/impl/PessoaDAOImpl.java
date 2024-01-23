package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ContaDAO;
import dao.EnderecoDAO;
import dao.PessoaDAO;
import model.Pessoa;
import model.util.Conexao;

public class PessoaDAOImpl implements PessoaDAO {
	
	Conexao conexao = new Conexao();
	EnderecoDAO endDAO = new EnderecoDAOImpl(); //objeto endDAO instanciado da classe EnderecoDAOImpl
	ContaDAO contaDAO = new ContaDAOImpl(); //objeto contaDAO instanciado da classe ContaDAOImpl
	
	
	
	public void salvar(Pessoa pessoa) {
		
		Connection conn = conexao.getConexao(); //aqui ativa a conexão com o banco de dados.
		
		String sql = "INSERT INTO PESSOA (CPF, IDADE, SEXO, NOME, ID_ENDERECO, NUMERO_CONTA)" + 
		"VALUES (?, ?, ?, ?, ?, ?)";
		
		try { //salvar um endereço e ainda retorna o ID gerado no BD. *OBS: O getEnderece() é um objeto completo pertencente a Classe Endereco*
			Integer id = this.endDAO.salvar(pessoa.getEndereco()); //ta tudo zerado** levando em consideração que ainda nao foi criado pessoas e conta. estamos criando e não atualizando
			
			this.contaDAO.salvar(pessoa.getConta()); //salvar uma conta. OBS: *O getConta é um objeto completo da Classe Conta. O método salvar conta pede como parâmetro um objeto do tipo Conta vindo da classe Conta. 
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getCpf());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setString(4, pessoa.getNome());
			ps.setInt(5, id);
			ps.setInt(6, pessoa.getConta().getNumero());
			ps.execute();
			System.out.println("Pessoa foi inserida no banco de dados.");
		} 
		catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa no banco de dados." + e.getMessage());		
		}
		finally {
			conexao.fecharConexao(conn);
		}		
	}

	
	public void alterar(Pessoa pessoa) {
		
		Connection conn = conexao.getConexao();
		
		String sql = "UPDATE PESSOA SET NOME = ?, IDADE = ?, SEXO = ?" 
				+ "ID_ENDERECO = ?, NUMERO_CONTA = ? WHERE CPF = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setInt(2, pessoa.getIdade());
			ps.setString(3, pessoa.getSexo());
			ps.setInt(4, pessoa.getEndereco().getId());
			ps.setInt(5, pessoa.getConta().getNumero());
			ps.setString(6, pessoa.getCpf());
			ps.execute();
			System.out.println("Alteração na tabela Pessoa realizada com sucesso.");
		}
		catch (SQLException e) {
			System.out.println("Erro na alteração da tabela pessoa" + e.getMessage());
		}
		finally {
			conexao.fecharConexao(conn);
		}		
	}
	
	public void remover(String cpf) {	
		Connection conn = conexao.getConexao();
		//variável do tipo pessoa aque recebe objeto do tipo pessoa
		Pessoa P = pesquisar(cpf); //aqui p guardar as informações de conta e endereço para depois excluir
		
		String sql = "DELETE FROM PESSOA WHERE CPF = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ps.execute();
			System.out.println("Pessoa Removida com Sucesso");
			contaDAO.remover(P.getConta().getNumero());//retirar o número da conta da tabela conta
			endDAO.remover(P.getEndereco().getId());//retirar o id_endereço da tabela endereço		
		} 
		catch (SQLException e) {
			System.out.println("Erro ao remover Pessoa" + e.getMessage());
		}
		finally {
			conexao.getConexao();
		}		
	}
	
	public Pessoa pesquisar(String cpf) {
		
		Connection conn = conexao.getConexao();
		Pessoa pessoa = new Pessoa();
		
		String sql = "SELECT * FROM PESSOA WHERE CPF = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cpf);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("SEXO"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setEndereco(this.endDAO.pesquisar(rs.getInt("ID_ENDERECO"))); //pesquisar do endereço dentro do pesquisar de pessoa
				pessoa.setConta(this.contaDAO.pesquisar(rs.getInt("NUMERO_CONTA"))); //aqui  
			}
		} 
		catch (SQLException e) {
			System.out.println("Erro ao pesquisar" + e.getMessage());
		}
		finally {
			conexao.fecharConexao(conn);
		}			
		return pessoa; 
	}

	
	public List<Pessoa> listarTodos() {	
		Connection conn = conexao.getConexao();	
		List<Pessoa> retorno = new ArrayList<Pessoa>();
		
		String sql = "SELECT * FROM PESSOA";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setNome(rs.getString("NOME"));
				pessoa.setCpf(rs.getString("CPF"));
				pessoa.setSexo(rs.getString("SEXO"));
				pessoa.setIdade(rs.getInt("IDADE"));
				pessoa.setEndereco(this.endDAO.pesquisar(rs.getInt("ID_ENDERECO")));
				pessoa.setConta(this.contaDAO.pesquisar(rs.getInt("NUMERO_CONTA")));
				retorno.add(pessoa);
			}
		}
		catch (SQLException e) {
			System.out.println("Erro ao pesquisar pessoa - " + e.getMessage());
		}
		finally {
			conexao.fecharConexao(conn);
		}	
		return retorno;
	}

	
	
}
