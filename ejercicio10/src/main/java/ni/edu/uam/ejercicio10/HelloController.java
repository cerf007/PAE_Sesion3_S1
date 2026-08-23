package ni.edu.uam.ejercicio10;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.DeshacerService;

public class HelloController {
    private final DeshacerService servicio = new DeshacerService();

    @FXML private TextField txtAccion;
    @FXML private Label lblOperacionActual;
    @FXML private Label lblHistorial;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        txtAccion.setOnAction(event -> onRealizarClick());
    }

    @FXML
    protected void onRealizarClick() {
        limpiarMensaje();

        String accion = txtAccion.getText().trim();
        if (accion.isEmpty()) {
            mostrarMensaje("Ingrese una descripción para la operación.");
            return;
        }

        servicio.realizarOperacion(accion);
        mostrarMensaje("Operación '" + accion + "' realizada.");
        txtAccion.clear();
        txtAccion.requestFocus();
        actualizarVista();
    }

    @FXML
    protected void onDeshacerClick() {
        limpiarMensaje();

        String deshecha = servicio.deshacer();
        if (deshecha != null) {
            mostrarMensaje("Se deshizo la operación: '" + deshecha + "'");
            actualizarVista();
        } else {
            mostrarMensaje("No hay operaciones para deshacer.");
        }
    }

    private void actualizarVista() {
        if (lblOperacionActual != null) {
            lblOperacionActual.setText("Operación Actual: " + servicio.getOperacionActual());
        }
        if (lblHistorial != null) {
            lblHistorial.setText(servicio.getHistorialFormateado());
        }
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
