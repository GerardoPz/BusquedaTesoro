package game;

import javax.swing.*;
import java.awt.*;

public class FrameConCarta extends JFrame {

    public FrameConCarta() {
        setTitle("Frame con Carta");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel carta = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.LIGHT_GRAY);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };

        // Estilo de la carta
        carta.setPreferredSize(new Dimension(300, 400));
        carta.setBackground(new Color(240, 240, 240));
        carta.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

        // Agregar contenido a la carta
        JLabel titulo = new JLabel("Carta Personalizada", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel contenido = new JLabel("<html>Esto es una carta dentro del frame.<br>¡Personalízala a tu gusto!</html>");
        contenido.setHorizontalAlignment(SwingConstants.CENTER);

        carta.setLayout(new BorderLayout());
        carta.add(titulo, BorderLayout.NORTH);
        carta.add(contenido, BorderLayout.CENTER);

        // Agregar la carta al centro del Frame
        setLayout(new GridBagLayout());
        add(carta);

        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameConCarta();
    }
}
