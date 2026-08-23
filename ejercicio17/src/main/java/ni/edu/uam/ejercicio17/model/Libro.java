package ni.edu.uam.ejercicio17.model;

import java.util.UUID;

public class Libro {
    private final String codigo;
    private final String titulo;
    private final String autor;
    private boolean disponible;

    public Libro(String titulo, String autor) {
        this.codigo = "LIB-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado";
        return String.format("[%s] %s - %s (%s)", codigo, titulo, autor, estado);
    }
}
