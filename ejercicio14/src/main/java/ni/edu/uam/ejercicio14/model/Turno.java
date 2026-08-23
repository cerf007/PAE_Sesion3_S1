package ni.edu.uam.ejercicio14.model;

public class Turno {
    private final int numero;
    private final String codigo;

    public Turno(int numero) {
        this.numero = numero;
        this.codigo = String.format("T-%03d", numero);
    }

    public int getNumero() {
        return numero;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }
}
