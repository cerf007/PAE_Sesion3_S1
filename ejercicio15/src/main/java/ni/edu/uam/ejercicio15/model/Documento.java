package ni.edu.uam.ejercicio15.model;

public class Documento {
    private final String nombre;
    private final int paginas;

    public Documento(String nombre, int paginas) {
        this.nombre = nombre;
        this.paginas = paginas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPaginas() {
        return paginas;
    }

    @Override
    public String toString() {
        return nombre + " (" + paginas + " pág.)";
    }
}
