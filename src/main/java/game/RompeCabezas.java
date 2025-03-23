package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RompeCabezas extends JFrame {
    private JLabel[][] piezas = new JLabel[5][5];
    private String[] imagenes = {
            "fila-1-columna-1.png", "fila-1-columna-2.png", "fila-1-columna-3.png", "fila-1-columna-4.png", "fila-1-columna-5.png",
            "fila-2-columna-1.png", "fila-2-columna-2.png", "fila-2-columna-3.png", "fila-2-columna-4.png", "fila-2-columna-5.png",
            "fila-3-columna-1.png", "fila-3-columna-2.png", "fila-3-columna-3.png", "fila-3-columna-4.png", "fila-3-columna-5.png",
            "fila-4-columna-1.png", "fila-4-columna-2.png", "fila-4-columna-3.png", "fila-4-columna-4.png", "fila-4-columna-5.png",
            "fila-5-columna-1.png", "fila-5-columna-2.png", "fila-5-columna-3.png", "fila-5-columna-4.png", "fila-5-columna-5.png",
    };
    private JLabel vacio;
    private Temporizador temporizador;

    public RompeCabezas(int segundos) {
        setTitle("Rompecabezas 5x5");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 5));

        List<String> listaImagenes = obtenerImagenes();
        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (index < listaImagenes.size() - 1) {
                    piezas[i][j] = new JLabel(escalarImagen(listaImagenes.get(index), 200, 200));
                    piezas[i][j].setName(listaImagenes.get(index));
                } else {
                    piezas[i][j] = new JLabel();
                    piezas[i][j].setName("vacio");
                    vacio = piezas[i][j];
                }
                piezas[i][j].addMouseListener(new MovimientoListener(i, j));
                add(piezas[i][j]);
                index++;
            }
        }

        temporizador = new Temporizador(segundos, this);
        temporizador.iniciar();
    }

    private List<String> obtenerImagenes() {
        List<String> lista = new ArrayList<>();
        for (int i = 0; i < imagenes.length; i++) {
            lista.add(imagenes[i]);
        }
        lista.add(null);
        Collections.shuffle(lista);
        return lista;
    }

    private class MovimientoListener extends MouseAdapter {
        int fila, columna;

        public MovimientoListener(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            moverFicha(fila, columna);
            verificarVictoria();
        }
    }

    private void moverFicha(int fila, int columna) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int nuevaFila = fila + dr[i];
            int nuevaColumna = columna + dc[i];

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
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                contador++;
                if (contador < 25 && piezas[i][j].getName() != null
                        && !piezas[i][j].getName().equals(imagenes[contador - 1])) {
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "¡Felicidades! Has completado el rompecabezas.");
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

    //METODO QUE CREA PARA CREAR EL PANEL CON EL ROMPECABEZAS
    private JPanel crearPanelRompecabezas() {
        JPanel panel = new JPanel(new GridLayout(5, 5));
        List<String> listaImagenes = obtenerImagenes();
        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (index < listaImagenes.size() - 1) {
                    piezas[i][j] = new JLabel(escalarImagen(listaImagenes.get(index), 200, 200));
                    piezas[i][j].setName(listaImagenes.get(index));
                } else {
                    piezas[i][j] = new JLabel();
                    piezas[i][j].setName("vacio");
                    vacio = piezas[i][j];
                }
                piezas[i][j].addMouseListener(new MovimientoListener(i, j));
                panel.add(piezas[i][j]);
                index++;
            }
        }

        return panel;
    }

}