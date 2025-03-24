package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.TimerTask;

public class VistaInicio extends JFrame {
    private String ruta = ruta();
    Musica musica = new Musica(ruta + "\\src\\main\\java\\audios\\instrumental.wav");
    private JPanel backgroundPanel;
    private ImageIcon fondoDia, fondoNoche;
    private Image imagenActual;
    private Timer timer;


    public VistaInicio() {
        setTitle("Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(700, 500);
        musica.start();

        fondoDia = new ImageIcon(ruta + "\\src\\main\\java\\fondosJuego\\fondoDia.jpg");
        fondoNoche = new ImageIcon(ruta + "\\src\\main\\java\\fondosJuego\\fondoNocheCorrecto.jpg");


        imagenActual = esDeDia() ? fondoDia.getImage() : fondoNoche.getImage();

        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenActual, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel seccionBtnMusica = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seccionBtnMusica.setOpaque(false);

        ImageIcon iconoAltavoz = new ImageIcon(ruta + "\\src\\main\\java\\imagenes\\altavoz.png");
        Image imgAltavozEscalada = iconoAltavoz.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon iconoAltavozEscalado = new ImageIcon(imgAltavozEscalada);

        ImageIcon iconoMute = new ImageIcon(ruta + "\\src\\main\\java\\imagenes\\mute.png");
        Image imgMuteEscalada = iconoMute.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon iconoMuteEscalado = new ImageIcon(imgMuteEscalada);

        JButton btnMusica = new JButton(iconoAltavozEscalado);

        btnMusica.setBorderPainted(false);
        btnMusica.setContentAreaFilled(false);
        btnMusica.setFocusPainted(false);
        btnMusica.setOpaque(false);

        btnMusica.addActionListener(e -> {
            musica.toggleMusic();
            System.out.println("Estas presionando");
            if(btnMusica.getIcon().equals(iconoAltavozEscalado)) {
                System.out.println("Vas a pararla");
                btnMusica.setIcon(iconoMuteEscalado);
            } else {
                btnMusica.setIcon(iconoAltavozEscalado);
            }
        });

        seccionBtnMusica.add(btnMusica);

        backgroundPanel.add(seccionBtnMusica, BorderLayout.NORTH);

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

        iniciarCambioDeFondo();

    }

    public static String ruta() {
        String rutaActual = System.getProperty("user.dir");
        return String.valueOf(Paths.get(rutaActual));
    }

    public static boolean esDeDia() {
        int hora = LocalTime.now().getHour();
        return hora >= 6 && hora < 18; // Día entre 6 AM y 5:59 PM
    }

    private void iniciarCambioDeFondo() {
        timer = new Timer(60000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean esDeDia = esDeDia();
                Image nuevaImagen = esDeDia ? fondoDia.getImage() : fondoNoche.getImage();

                if (imagenActual != nuevaImagen) {
                    imagenActual = nuevaImagen;
                    repaint();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new VistaInicio();
    }
}