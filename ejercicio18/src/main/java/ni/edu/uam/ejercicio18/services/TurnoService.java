package ni.edu.uam.ejercicio18.services;

import ni.edu.uam.ejercicio18.model.Cliente;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TurnoService {
    private final Queue<Cliente> colaPendientes = new ArrayDeque<>();

    private final List<Cliente> historialAtendidos = new ArrayList<>();

    private int contadorTurno = 1;

    public Cliente registrarYAsignarTurno(String nombre) {
        String codigoTurno = String.format("T-%03d", contadorTurno++);
        Cliente nuevoCliente = new Cliente(codigoTurno, nombre);
        colaPendientes.offer(nuevoCliente);
        return nuevoCliente;
    }

    public Cliente atenderCliente() {
        if (colaPendientes.isEmpty()) {
            return null;
        }
        Cliente atendido = colaPendientes.poll();
        historialAtendidos.add(atendido);
        return atendido;
    }

    public Cliente verSiguiente() {
        return colaPendientes.peek();
    }

    public String getPendientesFormateado() {
        if (colaPendientes.isEmpty()) {
            return "No hay clientes en espera.";
        }
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        for (Cliente c : colaPendientes) {
            sb.append(idx++).append(". ").append(c.toString()).append("\n");
        }
        return sb.toString();
    }

    public String getHistorialFormateado() {
        if (historialAtendidos.isEmpty()) {
            return "Aún no se ha atendido a ningún cliente.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historialAtendidos.size(); i++) {
            sb.append(i + 1).append(". ").append(historialAtendidos.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public int getCantidadPendientes() {
        return colaPendientes.size();
    }

    public int getCantidadAtendidos() {
        return historialAtendidos.size();
    }
}
