package ni.edu.uam.ejercicio5.models;

public class Estudiante {
    private String nombre;
    private String cif;
    private int anio;
    private String carrera;

    public Estudiante(String nombre, String cif, int anio, String carrera) {
        this.nombre = nombre;
        this.cif = cif;
        this.anio = anio;
        this.carrera = carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCif() {
        return cif;
    }

    public int getAnio() {
        return anio;
    }

    public String getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return String.format("CIF: %s | %s | Carrera: %s | Año: %d", cif, nombre, carrera, anio);
    }
}
