package ni.edu.uam.ejercicio5.services;

import ni.edu.uam.ejercicio5.models.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class GestionEstudiantes {private List<Estudiante> listaEstudiantes;

    public GestionEstudiantes() {
        this.listaEstudiantes = new ArrayList<>();
    }

    public boolean agregar(Estudiante estudiante) {
        if (buscarPorCif(estudiante.getCif()) != null) {
            return false;
        }
        listaEstudiantes.add(estudiante);
        return true;
    }

    public Estudiante buscarPorCif(String cif) {
        for (Estudiante e : listaEstudiantes) {
            if (e.getCif().equals(cif)) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarPorCif(String cif) {
        Estudiante e = buscarPorCif(cif);
        if (e != null) {
            listaEstudiantes.remove(e);
            return true;
        }
        return false;
    }

    public int getCantidad() {
        return listaEstudiantes.size();
    }

    public List<Estudiante> getLista() {
        return listaEstudiantes;
    }

    public String getListaFormateada() {
        if (listaEstudiantes.isEmpty()) return "No hay estudiantes registrados.";
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : listaEstudiantes) {
            sb.append(e.toString()).append("\n");
        }
        return sb.toString();
    }

}
