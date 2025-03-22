package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temporizador {
    private Timer timer;
    private int tiempoRestante;
    private RompeCabezas juego;

    public Temporizador(int segundos, RompeCabezas juego) {
        this.tiempoRestante = segundos;
        this.juego = juego;
        this.timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                if (tiempoRestante <= 0) {
                    detener();
                    juego.perderJuego();
                }
            }
        });
    }

    public void iniciar() {
        timer.start();
    }

    public void detener() {
        timer.stop();
    }
}
