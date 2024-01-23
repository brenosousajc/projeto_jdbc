package controller;

import java.sql.Connection;
import java.util.List;

import dao.ContaDAO;
import dao.EnderecoDAO;
import dao.impl.ContaDAOImpl;
import dao.impl.EnderecoDAOImpl;
import dao.impl.PessoaDAOImpl;
import model.Conta;
import model.Endereco;
import model.Pessoa;
import model.util.Conexao;

public class Principal {

	public static void main(String[] args) {
		
		Pessoa p1 = new Pessoa();
		Conta c1 = new Conta();
		Endereco end1 = new Endereco();
		
		PessoaDAOImpl pessoaDAOImpl = new PessoaDAOImpl();
		ContaDAO contaDAOImpl = new ContaDAOImpl();
		
		c1.setNumero(1001);
		c1.setSaldo(620);
		c1.setLimite(900);
		
		end1.setRua("Av Domingos Ferreira");
		end1.setNumero(5547);
		end1.setComplemento("Apto");
		
		p1.setCpf("3625487455");
		p1.setIdade(33);
		p1.setSexo("F");
		p1.setNome("Maria Clara do Nascimento");
		p1.setEndereco(end1);
		p1.setConta(c1);
		
		//pessoaDAOImpl.salvar(p1);
		

	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
