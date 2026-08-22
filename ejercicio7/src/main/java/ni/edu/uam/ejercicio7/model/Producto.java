package ni.edu.uam.ejercicio7.model;

public class Producto {
    private String nombre;
    private Double precio;
    private Integer inventarioActual;
    private Integer inventarioMinimo;

    public Producto(String nombre, Double precio, Integer inventarioActual, Integer inventarioMinimo) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventarioActual = inventarioActual;
        this.inventarioMinimo = inventarioMinimo;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public Integer getInventarioActual() {
        return inventarioActual;
    }

    public Integer getInventarioMinimo() {
        return inventarioMinimo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setInventarioActual(Integer inventarioActual) {
        this.inventarioActual = inventarioActual;
    }

    public void setInventarioMinimo(Integer inventarioMinimo) {
        this.inventarioMinimo = inventarioMinimo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", inventarioActual=" + inventarioActual +
                ", inventarioMinimo=" + inventarioMinimo +
                '}';
    }

}
