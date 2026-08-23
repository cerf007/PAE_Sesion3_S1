package ni.edu.uam.ejercicio13.services;

import ni.edu.uam.ejercicio13.model.Cliente;

import java.util.LinkedList;
import java.util.Queue;

public class ColaClientesServices {
    private final Queue<Cliente> cola = new LinkedList<>();

    public void agregarCliente(Cliente cliente) {
        cola.offer(cliente);
    }

    public Cliente atenderCliente() {
        return cola.poll();
    }

    public Cliente obtenerSiguiente() {
        return cola.peek();
    }

    public int getCantidad() {
        return cola.size();
    }

    public String getColaFormateada() {
        if (cola.isEmpty()) {
            return "La cola está vacía.";
        }
        StringBuilder sb = new StringBuilder();
        int posicion = 1;
        for (Cliente c : cola) {
            sb.append(posicion++).append(". ").append(c.getNombre()).append("\n");
        }
        return sb.toString();
    }
}
