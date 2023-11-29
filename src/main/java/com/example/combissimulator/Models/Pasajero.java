package com.example.combissimulator.Models;

import java.util.Random;

public class Pasajero implements Runnable {
    private int id;
    private Combi combi;
    private Cobrador cobrador;

    public Pasajero(int id, Combi combi, Cobrador cobrador) {
        this.id = id;
        this.combi = combi;
        this.cobrador = cobrador;
    }

    @Override
    public void run() {
        // Intenta abordar la combi
        if (cobrador.pagar(id)) {
            combi.abordar(id);
        } else {
            System.out.println("Pasajero " + id + " no pudo pagar. Se va de la cola.");
        }
    }
}
