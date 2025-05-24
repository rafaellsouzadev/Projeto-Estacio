package application.controllers;


import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import application.domain.Produto;
import application.services.ProdutoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ProdutoController {

    @FXML
    private TableView<Produto> tableProdutos;

    @FXML
    private TableColumn<Produto, String> colunaNome;

    @FXML
    private TableColumn<Produto, Double> colunaPreco;
    
    @FXML
    private TableColumn<Produto, Double> colunaTotal;
    
    @FXML
    private TableColumn<Produto, String> colunaDescricao;
    
    @FXML
    private TableColumn<Produto, Integer> colunaQuantidade;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPreco;
    
    @FXML
    private TextField txtDescricao;
    
    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnSalvar;

    @FXML
    private Button btnRemover;

    @FXML
    private Button btnPesquisar;

    private ProdutoService produtoService = new ProdutoService();

    private ObservableList<Produto> observableProdutos;

    @FXML
    public void initialize() {
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaPreco.setCellFactory(tc -> new TableCell<Produto, Double>() {
            @Override
            protected void updateItem(Double preco, boolean empty) {
                super.updateItem(preco, empty);
                if (empty || preco == null) {
                    setText(null);
                } else {
                    NumberFormat formatador = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
                    setText(formatador.format(preco));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });
        
        colunaTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
        colunaTotal.setCellFactory(tc -> new TableCell<Produto, Double>() {
            @Override
            protected void updateItem(Double precoTotal, boolean empty) {
                super.updateItem(precoTotal, empty);                
                if (empty || precoTotal == null) {
                    setText(null);
                } else {
                    NumberFormat formatador = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));
                    setText(formatador.format(precoTotal));
                    setStyle("-fx-alignment: CENTER;");
                }
            }
        });

        
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));        
        colunaDescricao.setCellFactory(tc -> new TableCell<Produto, String>() {
            private final Text text = new Text();

            {
                text.wrappingWidthProperty().bind(colunaDescricao.widthProperty().subtract(10));
                setGraphic(text);
                setPrefHeight(Control.USE_COMPUTED_SIZE); 
            }

            
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                text.setText(empty || item == null ? "" : item);
                
            }
        });


        
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        carregarProdutos();
    }

    public void carregarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        observableProdutos = FXCollections.observableArrayList(produtos);
        tableProdutos.setItems(observableProdutos);
    }

    @FXML
    private void salvarProduto() {
        String nome = txtNome.getText();
        String precoStr = txtPreco.getText();
        String descricao = txtDescricao.getText();
        String quantidadeStr = txtQuantidade.getText();

        if (nome.isEmpty() || precoStr.isEmpty() || descricao.isEmpty() || quantidadeStr.isEmpty()) {
            mostrarAlerta("Campos obrigatórios", "Preencha nome e preço.");
            return;
        }

        try {
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(quantidadeStr);  
            double precoTotal = preco * quantidade;
            Produto produto = new Produto(nome, preco, precoTotal, descricao, quantidade);           
            produtoService.salvar(produto);            
            limparCampos();
            carregarProdutos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Erro de formato", "Preço deve ser um número válido.");
        }
    }

    @FXML
    private void removerProduto() {
        Produto produtoSelecionado = tableProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            produtoService.remover(produtoSelecionado);
            carregarProdutos();
        } else {
            mostrarAlerta("Nenhum item selecionado", "Selecione um produto para remover.");
        }
    }

    @FXML
    private void pesquisarProduto() {
        String termo = txtPesquisa.getText().trim();
        List<Produto> resultado;

        if (termo.isEmpty()) {
            resultado = produtoService.listarTodos();
        } else {
            resultado = produtoService.buscarPorNome(termo);
        }

        observableProdutos = FXCollections.observableArrayList(resultado);
        tableProdutos.setItems(observableProdutos);
    }

    private void limparCampos() {
        txtNome.clear();
        txtPreco.clear();
        txtDescricao.clear();
        txtQuantidade.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}

