package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.connection.SigleConectionBanco;
import application.domain.Produto;

public class DAOProdutoRepository {

	private Connection connection;

	public DAOProdutoRepository() {
		connection = SigleConectionBanco.getConnection();
	}

	public List<Produto> findAll() {
		String sql = "SELECT * FROM PRODUTO";
		List<Produto> produtos = new ArrayList<>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();
				produto.setId(resultSet.getInt("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setDescricao(resultSet.getString("descricao"));
				produto.setPreco(resultSet.getBigDecimal("preco"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setPrecoTotal(resultSet.getBigDecimal("precoTotal"));

				produtos.add(produto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public void save(Produto produto) {
		try {
			String sql = "INSERT INTO produto (nome, descricao, preco, quantidade, precoTotal) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setString(2, produto.getDescricao());
			statement.setBigDecimal(3, produto.getPreco());
			statement.setInt(4, produto.getQuantidade());
			statement.setBigDecimal(5, produto.getPrecoTotal());

			statement.executeUpdate();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Produto produto) {
		try {
			String sql = "DELETE FROM produto WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, produto.getId());
			statement.executeUpdate();

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Produto> pesquinaNome(String nome) {

		String sql = "SELECT * FROM produto WHERE nome ILIKE ?";
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + nome + "%");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Produto produto = new Produto();
				produto.setId(resultSet.getInt("id"));
				produto.setNome(resultSet.getString("nome"));
				produto.setDescricao(resultSet.getString("descricao"));
				produto.setPreco(resultSet.getBigDecimal("preco"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setPrecoTotal(resultSet.getBigDecimal("precoTotal"));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produtos;

	}
	
	public void atualizar(Produto produto) {
	    String sql = "UPDATE produto SET nome=?, descricao=?, preco=?, quantidade=?, precoTotal=? WHERE id=?";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setString(1, produto.getNome());
	        statement.setString(2, produto.getDescricao());
	        statement.setBigDecimal(3, produto.getPreco());
	        statement.setInt(4, produto.getQuantidade());
	        statement.setBigDecimal(5, produto.getPrecoTotal());
	        statement.setLong(6, produto.getId());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


}
