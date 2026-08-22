package ni.edu.uam.ejercicio7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio7.model.Producto;
import ni.edu.uam.ejercicio7.services.ServicesProducto;

public class HelloController {
    private final ServicesProducto servicio = new ServicesProducto();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtInventarioActual;

    @FXML
    private TextField txtInventarioMinimo;

    @FXML
    private TextField txtBuscar;


    @FXML
    private Label lblResultadoBusqueda;

    @FXML
    private Label lblValorTotal;

    @FXML
    private Label lblListaCompleta;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        txtNombre.setOnAction(event -> txtPrecio.requestFocus());

        txtPrecio.setOnAction(event -> txtInventarioActual.requestFocus());

        txtInventarioActual.setOnAction(event -> txtInventarioMinimo.requestFocus());

        txtInventarioMinimo.setOnAction(event -> onAgregarClick());
    }

    @FXML
    protected void onAgregarClick() {
        limpiarMensaje();

        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarMensaje("El nombre del producto es obligatorio.");
            return;
        }

        try {
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int invActual = Integer.parseInt(txtInventarioActual.getText().trim());
            int invMinimo = Integer.parseInt(txtInventarioMinimo.getText().trim());

            if (precio < 0 || invActual < 0 || invMinimo < 0) {
                mostrarMensaje("Los valores numéricos deben ser mayores o iguales a cero.");
                return;
            }

            Producto nuevo = new Producto(nombre, precio, invActual, invMinimo);
            if (servicio.agregarProducto(nuevo)) {
                mostrarMensaje("Producto agregado correctamente.");
                limpiarCamposFormulario();
                actualizarVista();
            } else {
                mostrarMensaje("Error: Ya existe un producto registrado con ese nombre.");
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error: Ingrese números válidos en Precio e Inventarios.");
        }
    }

    @FXML
    protected void onBuscarClick() {
        limpiarMensaje();
        String busqueda = txtBuscar.getText().trim();

        if (busqueda.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del producto a buscar.");
            return;
        }

        Producto p = servicio.buscarProducto(busqueda);
        if (p != null) {
            lblResultadoBusqueda.setText("Encontrado: " + p.toString());
            // Cargar en los campos superiores por si se desea modificar
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtInventarioActual.setText(String.valueOf(p.getInventarioActual()));
            txtInventarioMinimo.setText(String.valueOf(p.getInventarioMinimo()));
            mostrarMensaje("Producto localizado.");
        } else {
            lblResultadoBusqueda.setText("Encontrado: Ninguno");
            mostrarMensaje("No se encontró ningún producto con ese nombre.");
        }
    }

    @FXML
    protected void onModificarClick() {
        limpiarMensaje();
        String busqueda = txtBuscar.getText().trim();

        if (busqueda.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del producto que desea modificar en el campo de búsqueda.");
            return;
        }

        try {
            double nuevoPrecio = Double.parseDouble(txtPrecio.getText().trim());
            int nuevoStock = Integer.parseInt(txtInventarioActual.getText().trim());
            int nuevoMinimo = Integer.parseInt(txtInventarioMinimo.getText().trim());

            if (servicio.modificarProducto(busqueda, nuevoPrecio, nuevoStock, nuevoMinimo)) {
                mostrarMensaje("Producto modificado exitosamente.");
                actualizarVista();
                limpiarCamposFormulario();
                txtBuscar.clear();
            } else {
                mostrarMensaje("No se pudo modificar: No existe un producto con ese nombre.");
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Error: Ingrese valores numéricos válidos en Precio e Inventario.");
        }
    }

    @FXML
    protected void onEliminarClick() {
        limpiarMensaje();
        String busqueda = txtBuscar.getText().trim();

        if (busqueda.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del producto que desea eliminar.");
            return;
        }

        if (servicio.eliminarProducto(busqueda)) {
            mostrarMensaje("Producto eliminado correctamente.");
            lblResultadoBusqueda.setText("");
            txtBuscar.clear();
            actualizarVista();
        } else {
            mostrarMensaje("Error: No se encontró el producto a eliminar.");
        }
    }



    private void actualizarVista() {
        if (lblListaCompleta != null) {
            lblListaCompleta.setText(servicio.getListaFormateada());
        }
        double total = servicio.calcularValorTotal();
        lblValorTotal.setText(String.format("Valor Total del Inventario: %.2f", total));
    }

    private void limpiarCamposFormulario() {
        txtNombre.clear();
        txtPrecio.clear();
        txtInventarioActual.clear();
        txtInventarioMinimo.clear();
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
