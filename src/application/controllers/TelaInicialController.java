package application.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaInicialController {
	
	 @FXML
	    private void abrirTelaProdutos(ActionEvent event) {
	        try {
	            Parent root = FXMLLoader.load(getClass().getResource("TelaProdutos.fxml"));

	            Stage stage = new Stage(); // ou use o Stage atual se quiser trocar a tela
	            stage.setTitle("Produtos");
	            stage.setScene(new Scene(root));
	            stage.show();
	            
	            Stage janelaAtual = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
	            janelaAtual.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
