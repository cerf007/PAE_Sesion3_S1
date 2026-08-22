package services;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraService {
    private final List<String> historial = new ArrayList<>();

    public double sumar(double a, double b) {
        double res = a + b;
        historial.add(String.format("%.2f + %.2f = %.2f", a, b, res));
        return res;
    }

    public double restar(double a, double b) {
        double res = a - b;
        historial.add(String.format("%.2f - %.2f = %.2f", a, b, res));
        return res;
    }

    public double multiplicar(double a, double b) {
        double res = a * b;
        historial.add(String.format("%.2f × %.2f = %.2f", a, b, res));
        return res;
    }

    public double dividir(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("No es posible dividir entre cero.");
        }
        double res = a / b;
        historial.add(String.format("%.2f ÷ %.2f = %.2f", a, b, res));
        return res;
    }

    public String getHistorialFormateado() {
        if (historial.isEmpty()) {
            return "El historial está vacío.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < historial.size(); i++) {
            sb.append(i + 1).append(". ").append(historial.get(i)).append("\n");
        }
        return sb.toString();
    }

    public void limpiarHistorial() {
        historial.clear();
    }
}
