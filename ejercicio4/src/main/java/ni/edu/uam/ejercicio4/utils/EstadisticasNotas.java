package ni.edu.uam.ejercicio4.utils;

public class EstadisticasNotas {

    private double[] notas;
    private int pos = 0;
    private static final double NOTA_MINIMA_APROBATORIA= 70.0;

    public void inicializar(int tamano){
        this.notas = new double[tamano];
        this.pos = 0;
    }

    public boolean isInicializado(){
        return notas != null;
    }

    public boolean isFull(){
        return notas != null && pos >=notas.length;
    }

    public void  add(double nota){
        if (! isFull()){
            notas[pos] =nota;
            pos++;
        }
    }

    public void reset() {
        this.notas = null;
        this.pos = 0;
    }

    public int getPos() {
        return pos;
    }

    public int getCapacidadTotal() {
        return notas != null ? notas.length : 0;
    }

    public double getPromedio() {
        if (!isInicializado() || pos == 0) return 0.0;
        double suma = 0.0;
        for (int i = 0; i < pos; i++) {
            suma += notas[i];
        }
        return suma / pos;
    }

    public double getNotaMayor(){
        if (!isInicializado() || pos == 0) return 0.0;
        double max = notas[0];
        for (int i = 1; i<pos; i++){
            if (notas[i] > max) max = notas[i];
        }
        return max;
    }

    public double getNotaMenor(){
        if (!isInicializado() || pos == 0) return 0.0;
        double min = notas[0];
        for (int i = 1; i<pos; i++){
            if (notas[i] < min) min = notas[i];
        }
        return min;
    }

    public int getCantidadAprobados(){
        if (!isInicializado()) return 0;
        int aprobados = 0;
        for (int i = 1; i<pos; i++){
            if(notas[i] >= NOTA_MINIMA_APROBATORIA) aprobados ++;
        }
        return aprobados;
    }

    public int getCantidadReprobados(){
        if (!isInicializado()) return 0;
        int reprobados = 0;
        for (int i = 1; i<pos; i++){
            if(notas[i] < NOTA_MINIMA_APROBATORIA) reprobados ++;
        }
        return reprobados;
    }

    public String getNotasRegistradas(){
        if(!isInicializado() || pos == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pos; i++) {
            sb.append(String.format("%.1f", notas[i]));
            if (i < pos - 1) sb.append(", ");
        }
        return sb.toString();
    }
}

