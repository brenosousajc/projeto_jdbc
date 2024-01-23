package dao;

import java.util.List;

import model.Endereco;

public interface EnderecoDAO {

	public int salvar(Endereco endereco);
	public void alterar(Endereco endereco);
	public void remover(int id);
	public Endereco pesquisar(int id);
	public List<Endereco> listarTodos();

}
