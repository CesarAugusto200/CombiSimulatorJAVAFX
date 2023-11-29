package com.example.combissimulator.Models;

public class Cobrador {
    private boolean atendiendo = false;

    public synchronized boolean pagar(int pasajeroId) {
        while (atendiendo) {
            try {
                wait(); // Espera si el cobrador está ocupado
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        atendiendo = true;
        System.out.println("Cobrador atendiendo a pasajero " + pasajeroId);

        try {
            Thread.sleep(1000); // Simula el tiempo que lleva realizar el pago
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cobrador ha recibido el pago de pasajero " + pasajeroId);
        atendiendo = false;
        notify(); // Notifica a los hilos que estén esperando
        return true;
    }
}