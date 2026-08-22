package ni.edu.uam.sesion3.utils;

public class MisDatos {
    private int[] numbers = new int[10];
    private int pos = 0;


    public void add(int number) {
        if (!isFull()) {
            numbers[pos] = number;
            pos++;
        }
    }

    public boolean isFull() {
        return pos >= numbers.length;
    }

    public void reset() {
        pos = 0;
        numbers = new int[10];
    }

    public int getMax() {
        if (pos == 0) return 0;
        int max = numbers[0];
        for (int i = 1; i < pos; i++) {
            if (numbers[i] > max) max = numbers[i];
        }
        return max;
    }

    public int getMin() {
        if (pos == 0) return 0;
        int min = numbers[0];
        for (int i = 1; i < pos; i++) {
            if (numbers[i] < min) min = numbers[i];
        }
        return min;
    }

    public String getSerieCompleta() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            sb.append(numbers[i]);
            if (i < pos - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public int getSuma() {
        int suma = 0;
        for (int i = 0; i < pos; i++) {
            suma += numbers[i];
        }
        return suma;
    }

    public double getPromedio() {
        if (pos == 0) return 0.0;
        return (double) getSuma() / pos;
    }

    public int getCantidadPares() {
        int pares = 0;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] % 2 == 0) {
                pares++;
            }
        }
        return pares;
    }

    public int getCantidadImpares() {
        int impares = 0;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] % 2 != 0) {
                impares++;
            }
        }
        return impares;
    }

    public String getPares() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] % 2 == 0) {
                if (!first) sb.append(", ");
                sb.append(numbers[i]);
                first = false;
            }
        }
        return sb.toString();
    }

    public String getImpares() {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < pos; i++) {
            if (numbers[i] % 2 != 0) {
                if (!first) sb.append(", ");
                sb.append(numbers[i]);
                first = false;
            }
        }
        return sb.toString();
    }
}
