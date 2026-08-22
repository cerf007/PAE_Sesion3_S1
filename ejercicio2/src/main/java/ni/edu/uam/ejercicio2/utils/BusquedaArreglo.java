package ni.edu.uam.ejercicio2.utils;

public class BusquedaArreglo {
    private int[] numbers;
    private int pos = 0;

    public void inicializar(int tamano) {
        this.numbers = new int[tamano];
        this.pos = 0;
    }

    public boolean isInicializado() {
        return numbers != null;
    }

    public boolean isFull() {
        return numbers != null && pos >= numbers.length;
    }

    public void add(int number) {
        if (!isFull()) {
            numbers[pos] = number;
            pos++;
        }
    }

    public int getCapacidadTotal() {
        return numbers != null ? numbers.length : 0;
    }

    public void reset() {
        this.pos = 0;
        this.numbers = null;
    }

    public boolean existe(int target) {
        if (!isInicializado()) return false;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] == target) return true;
        }
        return false;
    }

    public int contarOcurrencias(int target) {
        if (!isInicializado()) return 0;
        int contador = 0;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] == target) contador++;
        }
        return contador;
    }

    public String getPosiciones(int target) {
        if (!isInicializado()) return "";
        StringBuilder sb = new StringBuilder();
        boolean primero = true;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] == target) {
                if (!primero) sb.append(", ");
                sb.append(i + 1);
                primero = false;
            }
        }
        return sb.toString();
    }

    public String getSerieCompleta() {
        if (!isInicializado() || pos == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            sb.append(numbers[i]);
            if (i < pos - 1) sb.append(", ");
        }
        return sb.toString();
    }

    public int getPos() {
        return pos;
    }
}
