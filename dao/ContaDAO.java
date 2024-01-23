package dao;

import java.util.List;
import model.Conta;

public interface ContaDAO {

	 void salvar(Conta conta);
	 void alterar(Conta conta);
	 void remover(int numero);
	 Conta pesquisar(int numero); //classe Conta na assinatura do método para o método poder retornar um objeto da classe Conta.
	 List<Conta> listarTodos(); //na assinatura desse métudo tem um List<Conta> para o return poder retornar uma lista.
	
	
	
	
	
	
	
}
