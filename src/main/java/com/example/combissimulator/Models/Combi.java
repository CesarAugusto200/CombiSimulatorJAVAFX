package com.example.combissimulator.Models;

public class Combi {
    private int lugaresDisponibles = 20;

    public synchronized void abordar(int pasajeroId) {
        while (lugaresDisponibles == 0) {
            try {
                wait(); // Espera si no hay lugares disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        lugaresDisponibles--;
        System.out.println("Pasajero " + pasajeroId + " abordó la combi. Lugares disponibles: " + lugaresDisponibles);

        if (lugaresDisponibles == 0) {
            System.out.println("La combi está llena. Partiendo...");
            resetCombi();
        }
    }

    private void resetCombi() {
        lugaresDisponibles = 20;
        System.out.println("La combi ha sido reiniciada. Lugares disponibles: " + lugaresDisponibles);
        notifyAll(); // Notifica a los hilos que estén esperando
    }
}

