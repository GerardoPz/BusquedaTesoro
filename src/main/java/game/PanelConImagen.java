package game;
import javax.swing.*;
import java.awt.*;

public class PanelConImagen extends JPanel {
    private Image fondo;

    public PanelConImagen(String rutaImagen) {
        fondo = new ImageIcon(rutaImagen).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        JFrame padre = new JFrame("Panel con Imagen de Fondo");
        PanelConImagen panel = new PanelConImagen("C:\\Users\\Lu\\IdeaProjects\\Busqueda\\src\\main\\java\\imagenesBackgroundCarta\\prueba.jpg");
        panel.setSize(200, 100);
        JPanel ventana = new JPanel();
        padre.add(panel);
        padre.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximizada al iniciar
        // padre.setUndecorated(true);  // Sin bordes ni barra de título (opcional)
        padre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        padre.setVisible(true);
    }
}
