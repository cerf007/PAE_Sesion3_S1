package ni.edu.uam.ejercicio16.model;

public class ItemVenta {
    private final String producto;
    private final double precioUnitario;
    private final int cantidad;

    public ItemVenta(String producto, double precioUnitario, int cantidad) {
        this.producto = producto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotalItem() {
        return precioUnitario * cantidad;
    }

    @Override
    public String toString() {
        return String.format("%s x%d - $%.2f (Subtotal: $%.2f)", producto, cantidad, precioUnitario, getSubtotalItem());
    }
}
