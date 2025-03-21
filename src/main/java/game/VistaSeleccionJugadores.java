package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VistaSeleccionJugadores extends JFrame {
    private JTextField nombreJugador1;
    private JTextField nombreJugador2;
    private JCheckBox checkMaquina;
    private Jugador jugador1;
    private Jugador jugador2;
    private String placeHolder = "Jugador";

    public VistaSeleccionJugadores(Musica musica) {
        musica.toggleMusic();
        setTitle("Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        String rutaImagen = "C:\\Users\\Lu\\IdeaProjects\\BusquedaTesoro\\src\\main\\java\\fondosJuego\\fondoSalon.jpeg";
        ImageIcon imageIcon = new ImageIcon(rutaImagen);
        Image image = imageIcon.getImage();

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new BorderLayout());

        JPanel seccionBtnMusica = new JPanel(new FlowLayout(FlowLayout.LEFT));
        seccionBtnMusica.setOpaque(false);
        JButton btnMusica = new JButton("M");
        btnMusica.addActionListener(e -> {
            musica.toggleMusic();
            System.out.println("Estas presionando");
            if(btnMusica.getText().equals("M")) {
                System.out.println("Vas a pararla");
                btnMusica.setText("UM");
            } else {
                btnMusica.setText("M");
            }
        });
        btnMusica.setFont(new Font("Arial", Font.BOLD, 20));
        seccionBtnMusica.add(btnMusica);

        backgroundPanel.add(seccionBtnMusica, BorderLayout.NORTH);

        JPanel seccionCentro = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        JPanel panelCentral = new JPanel();
        panelCentral.setPreferredSize(new Dimension(800, 400));
        panelCentral.setBackground(new Color(0, 0, 0, 0));
        panelCentral.setLayout(new GridLayout(1, 2, 20, 0));

        panelCentral.add(crearPanelJugador("jugadorUno.png", placeHolder));
        panelCentral.add(crearPanelJugador("jugadorDos.png", placeHolder));

        seccionCentro.add(panelCentral, gbc);

        JPanel botonesJuego = new JPanel();
        botonesJuego.setLayout(new BoxLayout(botonesJuego, BoxLayout.Y_AXIS));
        botonesJuego.setOpaque(false);

        checkMaquina = new JCheckBox("Jugar Con Maquina");
        checkMaquina.setOpaque(false);
        checkMaquina.setForeground(Color.WHITE);
        checkMaquina.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonesJuego.add(checkMaquina);

        JButton btnIniciar = crearBoton("C:\\Users\\Lu\\IdeaProjects\\BusquedaTesoro\\src\\main\\java\\botones\\iniciarJuego.png", 200, 100);
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(e -> {
            System.out.println("N" + jugador1.getNombreJugador());
            System.out.println("N2" + jugador2.getNombreJugador());

            if (jugador1 != null && jugador2 != null && jugador1.getPersonaje() != null && jugador2.getPersonaje() != null) {
                iniciarJuego(new Jugador(nombreJugador1.getText(), jugador1.getPersonaje()),
                        new Jugador(nombreJugador2.getText(), jugador2.getPersonaje()));
                musica.toggleMusic();
            } else {
                JOptionPane.showMessageDialog(null, "Ambos jugadores deben elegir un personaje.", "Error", JOptionPane.ERROR_MESSAGE);
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
        panelJugador.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        ImageIcon backgroundImage = new ImageIcon("C:\\Users\\Lu\\IdeaProjects\\BusquedaTesoro\\src\\main\\java\\fondosJuego\\"+titulo);
        Image img = backgroundImage.getImage();

        Image scaledImg = img.getScaledInstance(250, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);

        JLabel backgroundLabel = new JLabel(scaledIcon);

        panelJugador.add(backgroundLabel, gbc);

        JButton btnElegirPersonaje = crearBoton("C:\\Users\\Lu\\IdeaProjects\\BusquedaTesoro\\src\\main\\java\\botones\\escogerJugador.png", 200, 100);
        btnElegirPersonaje.addActionListener(e -> elegirPersonaje(titulo));
        panelJugador.add(btnElegirPersonaje, gbc);

        JTextField nombreJugador = new JTextField(nombrePlaceholder);
        nombreJugador.setPreferredSize(new Dimension(200, 30));
        nombreJugador.setForeground(Color.GRAY);
        nombreJugador.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nombreJugador.getText().equals(nombrePlaceholder)) {
                    nombreJugador.setText("");
                    nombreJugador.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nombreJugador.getText().isEmpty()) {
                    nombreJugador.setText(nombrePlaceholder);
                    nombreJugador.setForeground(Color.GRAY);
                }
            }
        });
        panelJugador.add(nombreJugador, gbc);

        if (titulo.equals("jugadorUno.png")) {
            nombreJugador1 = nombreJugador;
            jugador1 = new Jugador(nombreJugador1.getText(), null);
        } else {
            nombreJugador2 = nombreJugador;
            jugador2 = new Jugador(nombreJugador2.getText(), null);
        }

        return panelJugador;
    }

    private JButton crearBoton(String rutaImagen, int t, int t2) {
        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(t, t2, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        JButton boton = new JButton(iconoEscalado);

        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setRolloverEnabled(false);

        boton.setPressedIcon(iconoEscalado);
        boton.setDisabledIcon(iconoEscalado);
        boton.setSelectedIcon(iconoEscalado);

        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return boton;
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
                    if (jugador.equals("jugadorUno.png")) {
                        jugador1.setPersonaje(String.valueOf(personaje));
                    } else if(jugador.equals("jugadorDos.png")){
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
        System.out.println(jugador1.getPersonaje());
        System.out.println(jugador2.getPersonaje());
        juego.setVisible(true);
    }
}
