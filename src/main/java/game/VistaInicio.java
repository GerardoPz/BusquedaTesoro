package game;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class VistaInicio extends JFrame {
    private String ruta = ruta();
    Musica musica = new Musica(ruta + "\\src\\main\\java\\audios\\instrumental.wav");

    public VistaInicio() {
        setTitle("Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(700, 500);
        musica.start();

        String rutaImagen = ruta + "\\src\\main\\java\\fondosJuego\\fondoDia.jpg";
        ImageIcon imageIcon = new ImageIcon(rutaImagen);
        Image image = imageIcon.getImage().getScaledInstance(1000, 1000, Image.SCALE_SMOOTH);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        ImageIcon icono = new ImageIcon(ruta + "\\src\\main\\java\\botones\\iniciarJuego.png");
        Image imgEscalada = icono.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imgEscalada);

        JButton botonImagen = new JButton(iconoEscalado);
        botonImagen.setBorderPainted(false);
        botonImagen.setBackground(Color.BLACK);
        botonImagen.setContentAreaFilled(false);
        botonImagen.setFocusPainted(false);
        botonImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botonImagen.setPreferredSize(new Dimension(500, 200));

        botonImagen.addActionListener(e -> {
            musica.toggleMusic();
            this.dispose();
            new VistaSeleccionJugadores(musica);
        });

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(botonImagen);

        backgroundPanel.add(centerPanel, BorderLayout.SOUTH);
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    public String ruta() {
        String rutaActual = System.getProperty("user.dir");
        return String.valueOf(Paths.get(rutaActual));
    }

    public static void main(String[] args) {
        new VistaInicio();
    }
}