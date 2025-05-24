module exercicio_estacio_loja {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;

    opens application to javafx.graphics, javafx.fxml;
    opens application.controllers to javafx.fxml;
    opens application.domain to javafx.base;
}
