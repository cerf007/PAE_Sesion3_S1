package service;

import java.util.Stack;

public class DeshacerService {
    private final Stack<String> pilaOperaciones = new Stack<>();

    public void realizarOperacion(String accion) {
        pilaOperaciones.push(accion);
    }

    public String deshacer() {
        if (pilaOperaciones.isEmpty()) {
            return null;
        }
        return pilaOperaciones.pop();
    }

    public String getOperacionActual() {
        if (pilaOperaciones.isEmpty()) {
            return "Ninguna (Sin operaciones)";
        }
        return pilaOperaciones.peek();
    }

    public String getHistorialFormateado() {
        if (pilaOperaciones.isEmpty()) {
            return "No hay operaciones registradas.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = pilaOperaciones.size() - 1; i >= 0; i--) {
            if (i == pilaOperaciones.size() - 1) {
                sb.append("[ACTUAL] -> ").append(pilaOperaciones.get(i)).append("\n");
            } else {
                sb.append("            ").append(pilaOperaciones.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
