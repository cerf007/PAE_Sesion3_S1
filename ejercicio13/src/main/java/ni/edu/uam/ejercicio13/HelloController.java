package ni.edu.uam.ejercicio13;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio13.model.Cliente;
import ni.edu.uam.ejercicio13.services.ColaClientesServices;

public class HelloController {
    private final ColaClientesServices servicio = new ColaClientesServices();

    @FXML
    private TextField txtNombre;

    @FXML
    private Label lblCantidad;

    @FXML
    private Label lblSiguiente;

    @FXML
    private Label lblCola;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        txtNombre.setOnAction(e -> onAgregarClick());
    }

    @FXML
    protected void onAgregarClick() {
        limpiarMensaje();
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del cliente.");
            return;
        }

        servicio.agregarCliente(new Cliente(nombre));
        mostrarMensaje("Cliente '" + nombre + "' agregado a la cola.");
        txtNombre.clear();
        txtNombre.requestFocus();
        actualizarVista();
    }

    @FXML
    protected void onAtenderClick() {
        limpiarMensaje();
        Cliente atendido = servicio.atenderCliente();

        if (atendido != null) {
            mostrarMensaje("Atendiendo a: " + atendido.getNombre());
            actualizarVista();
        } else {
            mostrarMensaje("No hay clientes en la cola para atender.");
        }
    }

    @FXML
    protected void onMostrarSiguienteClick() {
        limpiarMensaje();
        Cliente siguiente = servicio.obtenerSiguiente();

        if (siguiente != null) {
            mostrarMensaje("El siguiente en la cola es: " + siguiente.getNombre());
        } else {
            mostrarMensaje("La cola está vacía.");
        }
    }

    private void actualizarVista() {
        if (lblCantidad != null) {
            lblCantidad.setText("Clientes en espera: " + servicio.getCantidad());
        }

        Cliente siguiente = servicio.obtenerSiguiente();
        if (lblSiguiente != null) {
            if (siguiente != null) {
                lblSiguiente.setText("Siguiente en ser atendido: " + siguiente.getNombre());
            } else {
                lblSiguiente.setText("Siguiente en ser atendido: Ninguno");
            }
        }

        if (lblCola != null) {
            lblCola.setText(servicio.getColaFormateada());
        }
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
