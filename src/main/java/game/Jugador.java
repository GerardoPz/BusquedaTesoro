package game;

public class Jugador {
    private String nombreJugador;
    private String personaje;

    public Jugador(String nombreJugador, String personaje) {
        this.nombreJugador = nombreJugador;
        this.personaje = personaje;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }
}
