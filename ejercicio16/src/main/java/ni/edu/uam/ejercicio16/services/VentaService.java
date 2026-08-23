package ni.edu.uam.ejercicio16.services;

import ni.edu.uam.ejercicio16.model.ItemVenta;

import java.util.ArrayList;
import java.util.List;

public class VentaService {
    private final List<ItemVenta> detalleVenta = new ArrayList<>();
    private static final double IVA_PORCENTAJE = 0.15; // 15% IVA

    public void agregarItem(ItemVenta item) {
        detalleVenta.add(item);
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemVenta item : detalleVenta) {
            subtotal += item.getSubtotalItem();
        }
        return subtotal;
    }

    public double calcularMontoDescuento(double porcentajeDescuento) {
        return calcularSubtotal() * (porcentajeDescuento / 100.0);
    }

    public double calcularMontoIVA(double porcentajeDescuento) {
        double subtotalConDescuento = calcularSubtotal() - calcularMontoDescuento(porcentajeDescuento);
        return subtotalConDescuento * IVA_PORCENTAJE;
    }

    public double calcularTotal(double porcentajeDescuento) {
        double subtotal = calcularSubtotal();
        double descuento = calcularMontoDescuento(porcentajeDescuento);
        double iva = calcularMontoIVA(porcentajeDescuento);
        return (subtotal - descuento) + iva;
    }

    public String getDetalleFormateado() {
        if (detalleVenta.isEmpty()) {
            return "No hay productos registrados en la venta.";
        }
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        for (ItemVenta item : detalleVenta) {
            sb.append(idx++).append(". ").append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    public void nuevaVenta() {
        detalleVenta.clear();
    }

    public boolean tieneItems() {
        return !detalleVenta.isEmpty();
    }
}
