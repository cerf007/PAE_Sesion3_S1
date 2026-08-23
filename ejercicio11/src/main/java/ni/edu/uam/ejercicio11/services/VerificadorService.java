package ni.edu.uam.ejercicio11.services;

import java.util.Stack;

public class VerificadorService {
    public  boolean estaBalanceado(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                if (pila.isEmpty()) {
                    return false;
                }
                pila.pop();
            }
        }

        return pila.isEmpty();
    }
}
