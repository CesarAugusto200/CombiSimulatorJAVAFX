package com.example.combissimulator;

import com.example.combissimulator.Controller.HelloController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Obtener el controlador para acceder a su funcionalidad
        HelloController controller = fxmlLoader.getController();

        // Configurar la escena
        Scene scene = new Scene(root, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        // Iniciar la simulación directamente al cargar la interfaz
        controller.initSimulation();
    }

    public static void main(String[] args) {
        launch();
    }
}
