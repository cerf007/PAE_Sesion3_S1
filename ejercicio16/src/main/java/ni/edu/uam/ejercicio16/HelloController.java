package ni.edu.uam.ejercicio16;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio16.model.ItemVenta;
import ni.edu.uam.ejercicio16.services.VentaService;

public class HelloController {
    private final VentaService servicio = new VentaService();

    @FXML
    private TextField txtProducto;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtDescuento;


    @FXML
    private Label lblDetalle;

    @FXML
    private Label lblSubtotal;

    @FXML
    private Label lblMontoDescuento;

    @FXML
    private Label lblIVA;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        txtProducto.setOnAction(event -> txtPrecio.requestFocus());
        txtPrecio.setOnAction(event -> txtCantidad.requestFocus());
        txtCantidad.setOnAction(event -> onAgregarClick());

        txtDescuento.textProperty().addListener((observable, oldValue, newValue) -> calcularTotales());
    }

    @FXML
    protected void onAgregarClick() {
        limpiarMensaje();

        String producto = txtProducto.getText().trim();
        String strPrecio = txtPrecio.getText().trim();
        String strCantidad = txtCantidad.getText().trim();

        if (producto.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del producto.");
            return;
        }

        try {
            double precio = Double.parseDouble(strPrecio);
            int cantidad = Integer.parseInt(strCantidad);

            if (precio <= 0 || cantidad <= 0) {
                mostrarMensaje("El precio y la cantidad deben ser mayores a 0.");
                return;
            }

            servicio.agregarItem(new ItemVenta(producto, precio, cantidad));
            mostrarMensaje("Producto agregado a la venta.");

            txtProducto.clear();
            txtPrecio.clear();
            txtCantidad.clear();
            txtProducto.requestFocus();

            actualizarVista();

        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese valores numéricos válidos en Precio y Cantidad.");
        }
    }

    @FXML
    protected void onNuevaVentaClick() {
        limpiarMensaje();
        servicio.nuevaVenta();
        txtProducto.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        txtDescuento.setText("0");
        mostrarMensaje("Nueva venta iniciada.");
        actualizarVista();
    }

    private void calcularTotales() {
        if (!servicio.tieneItems()) {
            lblSubtotal.setText("Subtotal: $0.00");
            lblMontoDescuento.setText("Descuento: 0.00");
            lblIVA.setText("IVA (15%): 0.00");
            lblTotal.setText("Total: 0.00");
            return;
        }

        double porcentajeDescuento = 0;
        try {
            String strDesc = txtDescuento.getText().trim();
            if (!strDesc.isEmpty()) {
                porcentajeDescuento = Double.parseDouble(strDesc);
                if (porcentajeDescuento < 0 || porcentajeDescuento > 100) {
                    mostrarMensaje("El descuento debe estar entre 0% y 100%.");
                    porcentajeDescuento = 0;
                } else {
                    limpiarMensaje();
                }
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("El valor del descuento debe ser numérico.");
        }

        double subtotal = servicio.calcularSubtotal();
        double descuentoMonto = servicio.calcularMontoDescuento(porcentajeDescuento);
        double ivaMonto = servicio.calcularMontoIVA(porcentajeDescuento);
        double total = servicio.calcularTotal(porcentajeDescuento);

        lblSubtotal.setText(String.format("Subtotal: %.2f", subtotal));
        lblMontoDescuento.setText(String.format("Descuento (%.1f%%): %.2f", porcentajeDescuento, descuentoMonto));
        lblIVA.setText(String.format("IVA (15%%): %.2f", ivaMonto));
        lblTotal.setText(String.format("Total: %.2f", total));
    }

    private void actualizarVista() {
        if (lblDetalle != null) {
            lblDetalle.setText(servicio.getDetalleFormateado());
        }
        calcularTotales();
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
