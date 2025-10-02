package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crear un botón
        Button btn = new Button("Haz clic aquí");

        // Acción al hacer clic
        btn.setOnAction(e -> System.out.println("¡Hola desde JavaFX!"));

        // Layout principal
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Escena
        Scene scene = new Scene(root, 400, 250);

        // Configurar ventana
        primaryStage.setTitle("Prueba JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Método que arranca la app
    }
}
