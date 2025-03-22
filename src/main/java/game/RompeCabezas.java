package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.ArrayList;

public class RompeCabezas extends JFrame {
    private JLabel[][] piezas = new JLabel[5][5];
    private String[] imagenes = new String[25];
    private ArrayList<String> listaImagenes;
    private JLabel vacio;
    private Temporizador temporizador;

    public RompeCabezas(int segundos) {
        setTitle("Rompecabezas 5x5");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 5));

        listaImagenes = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            imagenes[i] = "imagenes/imagenesRC/img" + (i + 1) + ".png";
            listaImagenes.add(imagenes[i]);
        }
        imagenes[24] = null; // Espacio vacío sin imagen
        listaImagenes.add(null);

        Collections.shuffle(listaImagenes);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (listaImagenes.get(i * 5 + j) != null) {
                    piezas[i][j] = new JLabel(escalarImagen(listaImagenes.get(i * 5 + j), 80, 80));
                    piezas[i][j].setName(listaImagenes.get(i * 5 + j));
                } else {
                    piezas[i][j] = new JLabel(); // Espacio vacío
                    piezas[i][j].setName("vacio");
                    vacio = piezas[i][j];
                }
                piezas[i][j].addMouseListener(new MovimientoListener(i, j));
                add(piezas[i][j]);
            }
        }

        temporizador = new Temporizador(segundos, this);
        temporizador.iniciar();
    }

    private class MovimientoListener extends java.awt.event.MouseAdapter {
        int fila, columna;

        public MovimientoListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            moverFicha(fila, columna);
            verificarVictoria();
        }
    }

    private void moverFicha(int fila, int columna) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nuevaFila = fila + dr[i], nuevaColumna = columna + dc[i];
            if (esValido(nuevaFila, nuevaColumna) && piezas[nuevaFila][nuevaColumna] == vacio) {
                vacio.setIcon(piezas[fila][columna].getIcon());
                vacio.setName(piezas[fila][columna].getName());
                piezas[fila][columna].setIcon(null);
                piezas[fila][columna].setName("vacio");
                vacio = piezas[fila][columna];
                break;
            }
        }
    }

    private boolean esValido(int fila, int columna) {
        return fila >= 0 && fila < 5 && columna >= 0 && columna < 5;
    }

    private void verificarVictoria() {
        int contador = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (contador < 25 && !piezas[i][j].getName().equals("imagenes/imagenesRC" + contador + ".png")) {
                    return;
                }
                contador++;
            }
        }
        JOptionPane.showMessageDialog(this, "¡Ganaste!");
        temporizador.detener();
    }

    private ImageIcon escalarImagen(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    public void perderJuego() {
        JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo! Has perdido.");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RompeCabezas(60).setVisible(true));
    }
}
