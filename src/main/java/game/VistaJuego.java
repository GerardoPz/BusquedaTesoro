package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VistaJuego extends JFrame {
    private static final int TAMANO_TABLERO = 100;
    private static final int FILAS = 10;
    private static final int COLUMNAS = 10;
    private static final int ANCHO_CELDA = 15;
    private static final int ALTO_CELDA = 30;
    private static final Random random = new Random();
    private int posicionJugador1 = 0;
    private int posicionJugador2 = 0;
    private boolean turnoJugador1 = true;
    private final JLabel[][] celdas = new JLabel[FILAS][COLUMNAS];
    private final JLabel etiquetaJugador1 = new JLabel();
    private final JLabel etiquetaJugador2 = new JLabel();
    private final JButton botonTirarDado = new JButton("Tirar Dado");
    private Jugador jugador1;
    private Jugador jugador2;

    public VistaJuego(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        configurarVentana();
        configurarTablero();
        configurarPaneles();
        configurarControles();
    }

    private void configurarVentana() {
        setTitle("Juego de Tablero Simple");
        setSize(ANCHO_CELDA * COLUMNAS + 300, ALTO_CELDA * FILAS + 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    private void configurarTablero() {
        JPanel panelTablero = new JPanel(new GridLayout(FILAS, COLUMNAS));
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                int numeroCelda = i * COLUMNAS + j + 1;
                String textoCelda = (numeroCelda == 1) ? "Inicio" : (numeroCelda == TAMANO_TABLERO) ? "Fin" : String.valueOf(numeroCelda);
                celdas[i][j] = new JLabel(textoCelda, SwingConstants.CENTER);
                celdas[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                celdas[i][j].setOpaque(true);
                panelTablero.add(celdas[i][j]);
            }
        }
        add(panelTablero, BorderLayout.CENTER);
    }

    private void configurarPaneles() {
        JPanel panelIzquierda = new JPanel();
        panelIzquierda.setLayout(new BoxLayout(panelIzquierda, BoxLayout.Y_AXIS));
        JLabel cartaVolteada = new JLabel();
        cartaVolteada.setPreferredSize(new Dimension(300, 150));
        cartaVolteada.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JButton botonProbarSuerte = new JButton("Probar Suerte");

        etiquetaJugador1.setText("Jugador " + jugador1.getNombreJugador() + " : " + posicionJugador1);

        panelIzquierda.add(cartaVolteada);
        panelIzquierda.add(Box.createRigidArea(new Dimension(0, 10)));
        panelIzquierda.add(botonProbarSuerte);
        panelIzquierda.add(Box.createRigidArea(new Dimension(0, 10)));
        panelIzquierda.add(botonTirarDado);

        add(panelIzquierda, BorderLayout.WEST);

        JPanel panelDerecha = new JPanel();
        panelDerecha.setPreferredSize(new Dimension(300, 150));
        panelDerecha.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        add(panelDerecha, BorderLayout.EAST);
    }

    private void configurarControles() {
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new BoxLayout(panelControles, BoxLayout.Y_AXIS));
        panelControles.add(etiquetaJugador1);
        panelControles.add(etiquetaJugador2);

        botonTirarDado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tirarDado();
            }
        });

        add(panelControles, BorderLayout.SOUTH);
    }

    private void tirarDado() {
        int dado = random.nextInt(6) + 1;
        if (turnoJugador1) {
            moverJugador(1, dado);
        } else {
            moverJugador(2, dado);
        }
        turnoJugador1 = !turnoJugador1;
    }

    private void moverJugador(int jugador, int dado) {
        int posicionActual = (jugador == 1) ? posicionJugador1 : posicionJugador2;
        int nuevaPosicion = posicionActual + dado;

        if (nuevaPosicion > TAMANO_TABLERO) {
            nuevaPosicion = TAMANO_TABLERO; // Si excede el tamaño del tablero, se coloca en la última casilla
        }

        if (jugador == 1) {
            posicionJugador1 = nuevaPosicion;

        } else {
            posicionJugador2 = nuevaPosicion;
            etiquetaJugador2.setText("Jugador " + jugador2.getNombreJugador() + " : " + posicionJugador2);
        }

        actualizarTablero();
        verificarGanador();
    }

    private void actualizarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                celdas[i][j].setBackground(Color.WHITE);
                celdas[i][j].setIcon(null);
            }
        }

        int filaJugador1 = (posicionJugador1 - 1) / COLUMNAS;
        int columnaJugador1 = (posicionJugador1 - 1) % COLUMNAS;
        if (posicionJugador1 > 0) {
            celdas[filaJugador1][columnaJugador1].setIcon(crearIconoJugador(Color.RED, Color.WHITE));
        }

        int filaJugador2 = (posicionJugador2 - 1) / COLUMNAS;
        int columnaJugador2 = (posicionJugador2 - 1) % COLUMNAS;
        if (posicionJugador2 > 0) {
            celdas[filaJugador2][columnaJugador2].setIcon(crearIconoJugador(Color.BLUE, Color.WHITE));
        }

        if (posicionJugador1 == posicionJugador2 && posicionJugador1 > 0) {
            celdas[filaJugador1][columnaJugador1].setIcon(crearIconoJugador(Color.RED, Color.BLUE));
        }
    }

    private ImageIcon crearIconoJugador(Color colorJugador1, Color colorJugador2) {
        Image imagen = new BufferedImage(ANCHO_CELDA, ALTO_CELDA, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imagen.getGraphics();

        g.setColor(colorJugador1);
        g.fillRect(0, 0, ANCHO_CELDA, ALTO_CELDA / 2);

        g.setColor(colorJugador2);
        g.fillRect(0, ALTO_CELDA / 2, ANCHO_CELDA, ALTO_CELDA / 2);

        g.dispose();
        return new ImageIcon(imagen);
    }

    private void verificarGanador() {
        if (posicionJugador1 == TAMANO_TABLERO) {
            JOptionPane.showMessageDialog(this, "¡Jugador 1 gana!");
            reiniciarJuego();
        } else if (posicionJugador2 == TAMANO_TABLERO) {
            JOptionPane.showMessageDialog(this, "¡Jugador 2 gana!");
            reiniciarJuego();
        }
    }

    private void reiniciarJuego() {
        posicionJugador1 = 0;
        posicionJugador2 = 0;
        etiquetaJugador1.setText("Jugador 1: 0");
        etiquetaJugador2.setText("Jugador 2: 0");
        actualizarTablero();
    }
}