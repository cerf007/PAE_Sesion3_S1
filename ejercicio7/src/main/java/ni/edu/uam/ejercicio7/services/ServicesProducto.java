package ni.edu.uam.ejercicio7.services;

import ni.edu.uam.ejercicio7.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ServicesProducto {
    private final List<Producto> inventario = new ArrayList<>();

    public boolean agregarProducto(Producto producto){
        if (buscarProducto(producto.getNombre()) != null){
            return false;
        }
        inventario.add(producto);
        return true;
    }

    public boolean eliminarProducto(String nombre){
        Producto p = buscarProducto(nombre);
        if (p != null) {
            inventario.remove(p);
            return true;
        }
        return false;
    }

    public boolean modificarProducto(String nombreOriginal, Double nuevoPrecio, Integer nuevoStock, Integer nuevoMinimo){
        Producto p = buscarProducto(nombreOriginal);
        if (p != null) {
            p.setPrecio(nuevoPrecio);
            p.setInventarioActual(nuevoStock);
            p.setInventarioMinimo(nuevoMinimo);
            return true;
        }
        return false;
    }

    public double calcularValorTotal(){
        double total = 0;
        for (Producto p : inventario) {
            total += (p.getPrecio() * p.getInventarioActual());
        }
        return total;
    }

    public String getListaFormateada() {
        if (inventario.isEmpty()) return "El inventario está vacío.";
        StringBuilder sb = new StringBuilder();
        for (Producto p : inventario) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

}
