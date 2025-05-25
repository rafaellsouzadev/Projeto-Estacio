package application.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

public class Produto {
	
	private Integer id;
	private String nome;
	private BigDecimal preco;
	private BigDecimal precoTotal;
	private String descricao;
	private int quantidade;
	
	public Produto() {
	}

	public Produto(String nome, BigDecimal preco,BigDecimal precoTotal, String descricao, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.precoTotal = precoTotal;
		this.descricao = descricao;
		this.quantidade = quantidade;
	}
	
	public String getPrecoFormat() {
		NumberFormat fm = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));   
		return fm.format(preco);
	}
	
	public String getPrecoTotalFormat() {
		NumberFormat fm = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));   
		return fm.format(precoTotal);
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}	

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	

}
