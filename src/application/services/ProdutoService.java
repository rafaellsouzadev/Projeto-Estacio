package application.services;

import java.util.List;

import application.dao.DAOProdutoRepository;
import application.domain.Produto;

public class ProdutoService {		
	
	private DAOProdutoRepository repository = new DAOProdutoRepository();
    
    public void salvar(Produto produto) {
        repository.save(produto);
    }    
     
    public void remover(Produto produto) {
        repository.delete(produto);
    }
    
    public List<Produto> listarTodos() {
        return repository.findAll(); // retorna todos os produtos do banco
    }
   
    public List<Produto> buscarPorNome(String nome) {
        return repository.pesquinaNome(nome);
    }
    
    public void atualizar(Produto produto) {
        repository.atualizar(produto);
    }


}
