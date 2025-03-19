package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaInicio extends JFrame {
    private JTextField nombreJugador1;
    private JTextField nombreJugador2;
    private JCheckBox checkMaquina;
    private Jugador jugador1;
    private Jugador jugador2;
    private String placeHolder = "Jugador";

    public VistaInicio() {
        setTitle("Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String rutaImagen = "C:\\Users\\Lu\\IdeaProjects\\Busqueda\\src\\main\\java\\imagenesBackgroundCarta\\fondoNocheJuego.jpg";
        ImageIcon imageIcon = new ImageIcon(rutaImagen);
        Image image = imageIcon.getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JPanel seccionBtnMusica = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seccionBtnMusica.setOpaque(false);
        JButton btnMusica = new JButton("M");
        btnMusica.setFont(new Font("Arial", Font.BOLD, 20));
        seccionBtnMusica.add(btnMusica);

        backgroundPanel.add(seccionBtnMusica, BorderLayout.NORTH);

        JPanel seccionCentro = new JPanel(new GridBagLayout());
        seccionCentro.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel panelCentral = new JPanel();
        panelCentral.setPreferredSize(new Dimension(800, 400));
        panelCentral.setBackground(new Color(0, 0, 0, 150));
        panelCentral.setLayout(new GridLayout(1, 2, 20, 0));



        JPanel jugador1Panel = crearPanelJugador("Jugador 1", placeHolder);
        panelCentral.add(jugador1Panel);

        JPanel jugador2Panel = crearPanelJugador("Jugador 2", "Nombre del jugador 2");
        panelCentral.add(jugador2Panel);

        seccionCentro.add(panelCentral, gbc);

        JPanel botonesJuego = new JPanel();
        botonesJuego.setLayout(new BoxLayout(botonesJuego, BoxLayout.Y_AXIS));
        botonesJuego.setOpaque(false);

        checkMaquina = new JCheckBox("Jugar Con Maquina");
        checkMaquina.setOpaque(false);
        checkMaquina.setForeground(Color.WHITE);
        checkMaquina.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonesJuego.add(checkMaquina);

        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 24));
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(jugador1.getPersonaje());
                if (jugador1.getPersonaje() != null && jugador2.getPersonaje() != null && jugador1.getNombreJugador() != null) {
                    Jugador j1 = new Jugador(nombreJugador1.getText(), jugador1.getPersonaje());
                    Jugador j2 = new Jugador(nombreJugador2.getText(), jugador2.getPersonaje());
                    iniciarJuego(j1, j2);
                } else {
                    JOptionPane.showMessageDialog(null, "Ambos jugadores deben elegir un personaje.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        botonesJuego.add(Box.createRigidArea(new Dimension(0, 10)));
        botonesJuego.add(btnIniciar);

        backgroundPanel.add(seccionCentro, BorderLayout.CENTER);
        backgroundPanel.add(botonesJuego, BorderLayout.SOUTH);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private JPanel crearPanelJugador(String titulo, String nombrePlaceholder) {
        JPanel panelJugador = new JPanel(new GridBagLayout());
        panelJugador.setOpaque(false); // Hacer el panel transparente

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel jugadorLabel = new JLabel(titulo);
        jugadorLabel.setForeground(Color.WHITE);
        jugadorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panelJugador.add(jugadorLabel, gbc);

        JButton btnElegirPersonaje = new JButton("Elegir personaje");
        btnElegirPersonaje.setPreferredSize(new Dimension(100, 100));
        btnElegirPersonaje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirPersonaje(titulo);
            }
        });
        panelJugador.add(btnElegirPersonaje, gbc);

        JTextField nombreJugador = new JTextField(nombrePlaceholder);

        nombreJugador.setPreferredSize(new Dimension(200, 30));
        panelJugador.add(nombreJugador, gbc);

        if (titulo.equals("Jugador 1")) {
            nombreJugador1 = nombreJugador;
            jugador1 = new Jugador(nombreJugador1.getText(), null);
        } else {
            nombreJugador2 = nombreJugador;
            jugador2 = new Jugador(nombreJugador2.getText(), null);
        }

        return panelJugador;
    }

    private void elegirPersonaje(String jugador) {
        JFrame frame = new JFrame("Elegir Personaje");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        String[] nombresPersonajes = {"Personaje 1", "Personaje 2", "Personaje 3"};
        for (String nombrePersonaje : nombresPersonajes) {
            JButton btn = new JButton(nombrePersonaje);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Personaje personaje = new Personaje(nombrePersonaje);
                    if (jugador.equals("Jugador 1")) {
                        jugador1.setPersonaje(String.valueOf(personaje));
                    } else {
                        jugador2.setPersonaje(String.valueOf(personaje));
                    }
                    frame.dispose();
                }
            });
            panel.add(btn);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

    private void iniciarJuego(Jugador jugador1, Jugador jugador2) {
        this.dispose();
        VistaJuego juego = new VistaJuego(jugador1, jugador2);
        System.out.println(jugador1.getNombreJugador());
        System.out.println(jugador2.getNombreJugador());
        juego.setVisible(true);
        /*
        boolean jugarConMaquina = checkMaquina.isSelected();

        JFrame frame = new JFrame("Información del Juego");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(0, 0, 0, 150));

        JLabel label1 = new JLabel("Jugador 1: " + jugador1.getNombreJugador());
        label1.setForeground(Color.WHITE);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label1);

        JLabel personajeLabel1 = new JLabel("Personaje: " + jugador1.getPersonaje());
        personajeLabel1.setForeground(Color.WHITE);
        personajeLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(personajeLabel1);

        JLabel label2 = new JLabel("Jugador 2: " + jugador2.getNombreJugador());
        label2.setForeground(Color.WHITE);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label2);

        JLabel personajeLabel2 = new JLabel("Personaje: " + jugador2.getPersonaje());
        personajeLabel2.setForeground(Color.WHITE);
        personajeLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(personajeLabel2);

        JLabel labelMaquina = new JLabel("Jugar con Maquina: " + (jugarConMaquina ? "Sí" : "No"));
        labelMaquina.setForeground(Color.WHITE);
        labelMaquina.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelMaquina);

        frame.add(panel);
        frame.setVisible(true);
        */
    }

    public static void main(String[] args) {
        new VistaInicio();
    }
}