package service;

import java.util.Stack;

public class PilaService {
    private final Stack<Double> pila = new Stack<>();

    public void push(double valor) {
        pila.push(valor);
    }

    public Double pop() {
        if (pila.isEmpty()) {
            return null;
        }
        return pila.pop();
    }

    public Double peek() {
        if (pila.isEmpty()) {
            return null;
        }
        return pila.peek();
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }

    public String getPilaFormateada() {
        if (pila.isEmpty()) {
            return "La pila está vacía.";
        }
        StringBuilder sb = new StringBuilder();
        // Recorrido inverso para visualizar el tope arriba
        for (int i = pila.size() - 1; i >= 0; i--) {
            if (i == pila.size() - 1) {
                sb.append("[TOPE] -> ").append(pila.get(i)).append("\n");
            } else {
                sb.append("         ").append(pila.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
