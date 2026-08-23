package ni.edu.uam.ejercicio17.services;

import ni.edu.uam.ejercicio17.model.Libro;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private final List<Libro> libros = new ArrayList<>();

    public Libro registrarLibro(String titulo, String autor) {
        Libro nuevo = new Libro(titulo, autor);
        libros.add(nuevo);
        return nuevo;
    }

    public Libro buscarLibro(String codigo) {
        for (int i = 0; i < libros.size(); i++) {
            Libro l = libros.get(i);
            if (l.getCodigo().equalsIgnoreCase(codigo)) {
                return l;
            }
        }
        return null;
    }

    public boolean prestarLibro(String codigo) {
        Libro libro = buscarLibro(codigo);
        if (libro != null && libro.isDisponible()) {
            libro.setDisponible(false);
            return true;
        }
        return false;
    }

    public boolean devolverLibro(String codigo) {
        Libro libro = buscarLibro(codigo);
        if (libro != null && !libro.isDisponible()) {
            libro.setDisponible(true);
            return true;
        }
        return false;
    }

    public int contarLibrosDisponibles() {
        int disponibles = 0;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).isDisponible()) {
                disponibles++;
            }
        }
        return disponibles;
    }

    public String getListaFormateada() {
        if (libros.isEmpty()) {
            return "No hay libros registrados en la biblioteca.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < libros.size(); i++) {
            sb.append(i + 1).append(". ").append(libros.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public int getCantidadTotal() {
        return libros.size();
    }
}
