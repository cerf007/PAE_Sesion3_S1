package ni.edu.uam.ejercicio12.services;

import java.util.Stack;

public class InversorServices {
    public String invertirTexto(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        }

        Stack<Character> pila = new Stack<>();

        for (char c : texto.toCharArray()) {
            pila.push(c);
        }

        StringBuilder invertido = new StringBuilder();
        while (!pila.isEmpty()) {
            invertido.append(pila.pop());
        }

        return invertido.toString();
    }
}
