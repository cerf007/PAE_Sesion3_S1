package ni.edu.uam.ejercicio3.utils;

import java.util.Arrays;

public class OrdenamientoArreglo {
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

    public void reset() {
        this.numbers = null;
        this.pos = 0;
    }

    public int getPos() {
        return pos;
    }

    public int getCapacidadTotal() {
        return numbers != null ? numbers.length : 0;
    }

    // Devuelve los elementos en el orden exacto en que fueron ingresados
    public String getSerieOriginal() {
        if (!isInicializado() || pos == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            sb.append(numbers[i]);
            if (i < pos - 1) sb.append(", ");
        }
        return sb.toString();
    }

    // Crea una copia de los elementos actuales y los ordena usando MergeSort
    public String getSerieOrdenada() {
        if (!isInicializado() || pos == 0) return "";

        int[] copia = Arrays.copyOf(numbers, pos);
        mergeSort(copia, 0, copia.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < copia.length; i++) {
            sb.append(copia[i]);
            if (i < copia.length - 1) sb.append(", ");
        }
        return sb.toString();
    }

    // --- ALGORITMO MERGESORT ---

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}