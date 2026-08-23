package ni.edu.uam.ejercicio18;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio18.model.Cliente;
import ni.edu.uam.ejercicio18.services.TurnoService;

public class HelloController {
    private final TurnoService servicio = new TurnoService();

    @FXML
    private TextField txtNombre;

    @FXML
    private Label lblPendientes;

    @FXML
    private Label lblHistorial;

    @FXML
    private Label lblSiguiente;

    @FXML
    private Label lblTotales;

    @FXML
    private Label lblMensaje;


    @FXML
    public void initialize() {
        txtNombre.setOnAction(event -> onRegistrarClick());
    }

    @FXML
    protected void onRegistrarClick() {
        limpiarMensaje();
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del cliente para asignar un turno.");
            return;
        }

        Cliente cliente = servicio.registrarYAsignarTurno(nombre);
        mostrarMensaje("Turno asignado: " + cliente.getTurno() + " para " + cliente.getNombre());

        txtNombre.clear();
        txtNombre.requestFocus();
        actualizarVista();
    }

    @FXML
    protected void onAtenderClick() {
        limpiarMensaje();
        Cliente atendido = servicio.atenderCliente();

        if (atendido != null) {
            mostrarMensaje("Atendiendo a: " + atendido.toString());
            actualizarVista();
        } else {
            mostrarMensaje("No hay clientes pendientes en la cola.");
        }
    }

    private void actualizarVista() {
        if (lblPendientes != null) {
            lblPendientes.setText(servicio.getPendientesFormateado());
        }
        if (lblHistorial != null) {
            lblHistorial.setText(servicio.getHistorialFormateado());
        }
        if (lblSiguiente != null) {
            Cliente sig = servicio.verSiguiente();
            lblSiguiente.setText("Siguiente en turno: " + (sig != null ? sig.toString() : "Ninguno"));
        }
        if (lblTotales != null) {
            lblTotales.setText(String.format("En espera: %d | Atendidos: %d",
                    servicio.getCantidadPendientes(),
                    servicio.getCantidadAtendidos()));
        }
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
