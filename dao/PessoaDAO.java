package dao;

import java.util.List;

import model.Pessoa;

public interface PessoaDAO {

	public void salvar(Pessoa pessoa);
	public void alterar(Pessoa pessoa);
	public void remover(String cpf);
	public Pessoa pesquisar(String cpf);
	public List<Pessoa> listarTodos();
	
	
	
}
