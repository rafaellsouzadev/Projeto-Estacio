package application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import application.domain.Produto;

public class ProdutoService {	
	
	private List<Produto> produtos = new ArrayList<>();
	
	public ProdutoService() {	
		   
		produtos.add(new Produto("Impressora", 600.00, 3000.00, "Epson EcoTank L3250 - Multifuncional, Tanque de Tinta Colorida, Wi-Fi Direct, USB, Bivolt, Preto", 5));
		produtos.add(new Produto("Processador AMD Ryzen 7 5700X3D", 1649.99,4949.97, "Epson EcoTank L3250 - Multifuncional, Tanque de Tinta Colorida, Wi-Fi Direct, USB, Bivolt, Preto", 3));
		produtos.add(new Produto("Processador Intel Core i7-12700KF", 1569.99,6279.96, "i7-12700KF, 3.6GHz (5.0GHz Max Turbo), Cache 25MB, 12 Núcleos, 20 Threads, LGA 1700 - BX8071512700KF", 4));
		
	}

    
    public void salvar(Produto produto) {
        produtos.add(produto);
    }
    
    public void salvar(List<Produto> produto) {
        produtos.addAll(produto);
    }

    
    public void remover(Produto produto) {
        produtos.remove(produto);
    }

    
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos); // retorna cópia para evitar modificações externas
    }

   
    public List<Produto> buscarPorNome(String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

}
