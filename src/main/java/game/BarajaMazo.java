package game;

import javax.swing.*;
import java.awt.*;

public class BarajaMazo {
    private String nombreBaraja;
    private Image backgroundCarta;
    private Image imagen;
    private String acertijo;
    private int numeroCasillasPosibles;

    public BarajaMazo(String nombreBaraja, String backgroundCarta, String imagen, String acertijo, int numeroCasillasPosibles) {
        this.nombreBaraja = nombreBaraja;
        this.backgroundCarta = new ImageIcon(backgroundCarta).getImage();
        this.imagen = new ImageIcon(imagen).getImage();
        this.acertijo = acertijo;
        this.numeroCasillasPosibles = numeroCasillasPosibles;
    }

    public String getNombreBaraja() {
        return nombreBaraja;
    }

    public void setNombreBaraja(String nombreBaraja) {
        this.nombreBaraja = nombreBaraja;
    }

    public Image getBackgroundCarta() {
        return backgroundCarta;
    }

    public void setBackgroundCarta(Image backgroundCarta) {
        this.backgroundCarta = backgroundCarta;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public String getAcertijo() {
        return acertijo;
    }

    public void setAcertijo(String acertijo) {
        this.acertijo = acertijo;
    }

    public int getNumeroCasillasPosibles() {
        return numeroCasillasPosibles;
    }

    public void setNumeroCasillasPosibles(int numeroCasillasPosibles) {
        this.numeroCasillasPosibles = numeroCasillasPosibles;
    }
}
