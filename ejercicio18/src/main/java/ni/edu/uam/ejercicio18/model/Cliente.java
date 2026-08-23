package ni.edu.uam.ejercicio18.model;

public class Cliente {
    private final String turno;
    private final String nombre;

    public Cliente(String turno, String nombre) {
        this.turno = turno;
        this.nombre = nombre;
    }

    public String getTurno() {
        return turno;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s", turno, nombre);
    }
}
