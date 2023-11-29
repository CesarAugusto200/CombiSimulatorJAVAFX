package com.example.combissimulator.Controller;

import com.example.combissimulator.Models.Cobrador;
import com.example.combissimulator.Models.Combi;
import com.example.combissimulator.Models.Pasajero;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Random;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private HBox simulationContainer;

    public void initialize() {

        initSimulation();
    }

    public void initSimulation() {

        Combi combi = new Combi();
        Cobrador cobrador = new Cobrador();

        for (int i = 1; i <= 30; i++) {
            Pasajero pasajero = new Pasajero(i, combi, cobrador);
            Thread hiloPasajero = new Thread(pasajero);
            hiloPasajero.start();


            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
