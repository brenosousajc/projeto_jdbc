package model;

public class Pessoa{

	@Override
	public String toString() {
		return "Pessoa [cpf = " + cpf + ", idade = " + idade + ", sexo = " + sexo + ", nome = " + nome + ", endereco = "
				+ endereco + ", conta = " + conta + "]";
	}

	private String cpf;
	private int idade;
	private String sexo;
	private String nome;
	private Endereco endereco;
	private Conta conta;
	
	public Pessoa(String cpf, int idade, String sexo, String nome, Endereco endereco, Conta conta) {
		
		this.cpf = cpf;
		this.idade = idade;
		this.sexo = sexo;
		this.nome = nome;
		this.endereco = endereco;
		this.conta = conta;
	}
	
	public Pessoa () {}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	
	
}
