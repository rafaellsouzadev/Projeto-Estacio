package application.domain;

import java.text.NumberFormat;
import java.util.Locale;

public class Produto {
	
	private String nome;
	private Double preco;
	private String descricao;
	private int quantidade;
	
	public Produto() {
	}

	public Produto(String nome, Double preco, String descricao, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
	public String getPrecoFormat() {
		NumberFormat fm = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));   
		return fm.format(preco);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	
	

}
