package com.example.combissimulator.Scenes;

import com.example.combissimulator.Models.Cobrador;
import com.example.combissimulator.Models.Combi;
import com.example.combissimulator.Models.Pasajero;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.AnimationTimer;

import java.io.File;

public class Ecenas extends Application {

    private ImageView combiImageView;
    private ImageView pasajeroImageView;

    private Combi combi;
    private Cobrador cobrador;

    private int pasajeroCount;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Cargar imágenes
        Image backgroundImage = loadImage("C:\\Users\\aguil\\IdeaProjects\\CombisSimulator\\src\\main\\java\\com\\example\\combissimulator\\assets\\Ecenario.jpg");
        Image combiImage = loadImage("C:\\Users\\aguil\\IdeaProjects\\CombisSimulator\\src\\main\\java\\com\\example\\combissimulator\\assets\\Combi.png");
        Image pasajeroImage = loadImage("C:\\Users\\aguil\\IdeaProjects\\CombisSimulator\\src\\main\\java\\com\\example\\combissimulator\\assets\\pasajero.png");

        // Crear ImageView para mostrar las imágenes
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800); // ajusta según sea necesario
        backgroundImageView.setFitHeight(600); // ajusta según sea necesario

        combiImageView = createImageView(combiImage, 200, 200);
        pasajeroImageView = createImageView(pasajeroImage, 100, 100);


        combiImageView.setTranslateY(120);
        pasajeroImageView.setTranslateY(80);


        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, combiImageView, pasajeroImageView);


        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Simulador de Combis");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Iniciar la simulación
        combi = new Combi();
        cobrador = new Cobrador();
        pasajeroCount = 0;


        TranslateTransition combiTranslateTransition = createTranslateTransition(combiImageView, 800, 2);
        TranslateTransition pasajeroTranslateTransition = createTranslateTransition(pasajeroImageView, -50, 1);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                simulate(combiTranslateTransition, pasajeroTranslateTransition);
            }
        };

        animationTimer.start();
    }

    private Image loadImage(String absolutePath) {
        File file = new File(absolutePath);
        return new Image(file.toURI().toString());
    }

    private ImageView createImageView(Image image, double fitWidth, double fitHeight) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        return imageView;
    }

    private TranslateTransition createTranslateTransition(ImageView imageView, double distance, double durationSeconds) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(durationSeconds), imageView);
        translateTransition.setByX(distance);
        return translateTransition;
    }

    private void simulate(TranslateTransition combiTranslateTransition, TranslateTransition pasajeroTranslateTransition) {
        if (pasajeroCount < 30) {
            Pasajero pasajero = new Pasajero(++pasajeroCount, combi, cobrador);
            Thread hiloPasajero = new Thread(() -> {
                pasajeroTranslateTransition.play();
                pasajeroTranslateTransition.setOnFinished(event -> {

                    pasajeroImageView.setTranslateX(0); // Restaurar la posición original
                });
                pasajero.run();
            });
            hiloPasajero.start();


            try {
                Thread.sleep(new java.util.Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {

            combiTranslateTransition.play();
            combiTranslateTransition.setOnFinished(event -> {
                combiImageView.setTranslateX(0);
                pasajeroImageView.setVisible(false);

                try {
                    Thread.sleep(new java.util.Random().nextInt(2000) + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Preparar para la llegada de un nuevo colectivo y pasajero
                combiImageView.setVisible(true);
                pasajeroImageView.setVisible(true);
                combi = new Combi();
                cobrador = new Cobrador();
                pasajeroCount = 0;
            });
        }
    }
}
