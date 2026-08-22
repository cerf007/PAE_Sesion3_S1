package ni.edu.uam.ejercicio6.services;

import ni.edu.uam.ejercicio6.models.Contacto;

import java.util.ArrayList;
import java.util.List;

public class GestionContactos {
    private final List<Contacto> listaContactos = new ArrayList<>();

    public int agregar(Contacto contacto) {
        if (buscarPorTelefono(contacto.getTelefono()) != null) {
            return -1; // Teléfono duplicado
        }
        if (!contacto.getCorreo().isEmpty() && buscarPorCorreo(contacto.getCorreo()) != null) {
            return -2; // Correo duplicado
        }
        listaContactos.add(contacto);
        return 1; // Éxito
    }

    public Contacto buscarPorTelefono(String telefono) {
        for (Contacto c : listaContactos) {
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

    public Contacto buscarPorCorreo(String correo) {
        for (Contacto c : listaContactos) {
            if (!c.getCorreo().isEmpty() && c.getCorreo().equalsIgnoreCase(correo)) {
                return c;
            }
        }
        return null;
    }

    // Búsqueda por coincidencias parciales (Nombre o Teléfono)
    public List<Contacto> buscarCoincidencias(String criterio) {
        List<Contacto> resultados = new ArrayList<>();
        String filtro = criterio.toLowerCase().trim();

        for (Contacto c : listaContactos) {
            if (c.getNombre().toLowerCase().contains(filtro) || c.getTelefono().contains(filtro)) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    public boolean eliminarPorTelefono(String telefono) {
        Contacto c = buscarPorTelefono(telefono);
        if (c != null) {
            listaContactos.remove(c);
            return true;
        }
        return false;
    }

    public int getCantidad() {
        return listaContactos.size();
    }

    public String getListaFormateada() {
        if (listaContactos.isEmpty()) return "La agenda está vacía.";
        StringBuilder sb = new StringBuilder();
        for (Contacto c : listaContactos) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
