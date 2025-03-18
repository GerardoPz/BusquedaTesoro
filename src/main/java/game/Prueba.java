package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Prueba extends JFrame {
    public Prueba() {
        BarajaMazo baraja = new BarajaMazo("Hola", "C:\\Users\\Lu\\IdeaProjects\\Busqueda\\src\\main\\java\\imagenesBackgroundCarta\\prueba.jpg", "C:\\Users\\Lu\\IdeaProjects\\Busqueda\\src\\main\\java\\imagenesBackgroundCarta\\prueba.jpg", "Hola", 6);
        System.out.println(baraja.getImagen().toString());
    }
    public static void main(String[] args) {

        new Prueba();
    }
}
