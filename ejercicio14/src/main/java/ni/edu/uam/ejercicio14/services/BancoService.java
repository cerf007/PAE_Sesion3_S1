package ni.edu.uam.ejercicio14.services;

import ni.edu.uam.ejercicio14.model.Turno;

import java.util.LinkedList;
import java.util.Queue;

public class BancoService {
    private final Queue<Turno> colaTurnos = new LinkedList<>();
    private int contadorTurnos = 0;

    public Turno generarTurno() {
        contadorTurnos++;
        Turno nuevoTurno = new Turno(contadorTurnos);
        colaTurnos.offer(nuevoTurno);
        return nuevoTurno;
    }

    public Turno llamarTurno() {
        return colaTurnos.poll();
    }

    public Turno obtenerSiguiente() {
        return colaTurnos.peek();
    }

    public int getClientesPendientes() {
        return colaTurnos.size();
    }

    public String getPendientesFormateado() {
        if (colaTurnos.isEmpty()) {
            return "No hay clientes pendientes en fila.";
        }
        StringBuilder sb = new StringBuilder();
        int posicion = 1;
        for (Turno t : colaTurnos) {
            sb.append(posicion++).append(". Turno ").append(t.getCodigo()).append("\n");
        }
        return sb.toString();
    }
}
