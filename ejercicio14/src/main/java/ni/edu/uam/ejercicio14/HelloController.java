package ni.edu.uam.ejercicio14;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ni.edu.uam.ejercicio14.model.Turno;
import ni.edu.uam.ejercicio14.services.BancoService;

public class HelloController {
    private final BancoService servicio = new BancoService();

    @FXML
    private Label lblTurnoLlamado;

    @FXML
    private Label lblSiguiente;

    @FXML
    private Label lblPendientes;

    @FXML
    private Label lblListaPendientes;

    @FXML
    private Label lblMensaje;

    @FXML
    protected void onGenerarTurnoClick() {
        limpiarMensaje();
        Turno creado = servicio.generarTurno();
        mostrarMensaje("Turno " + creado.getCodigo() + " generado exitosamente.");
        actualizarVista();
    }

    @FXML
    protected void onLlamarTurnoClick() {
        limpiarMensaje();
        Turno llamado = servicio.llamarTurno();

        if (llamado != null) {
            lblTurnoLlamado.setText("Turno en atención: " + llamado.getCodigo());
            mostrarMensaje("Atendiendo al turno " + llamado.getCodigo());
            actualizarVista();
        } else {
            lblTurnoLlamado.setText("Turno en atención: Ninguno");
            mostrarMensaje("No hay turnos pendientes por llamar.");
        }
    }

    @FXML
    protected void onMostrarSiguienteClick() {
        limpiarMensaje();
        Turno siguiente = servicio.obtenerSiguiente();

        if (siguiente != null) {
            mostrarMensaje("El siguiente turno a ser llamado es: " + siguiente.getCodigo());
        } else {
            mostrarMensaje("La cola de turnos está vacía.");
        }
    }

    private void actualizarVista() {
        if (lblPendientes != null) {
            lblPendientes.setText("Clientes pendientes: " + servicio.getClientesPendientes());
        }

        Turno siguiente = servicio.obtenerSiguiente();
        if (lblSiguiente != null) {
            if (siguiente != null) {
                lblSiguiente.setText("Siguiente en fila: " + siguiente.getCodigo());
            } else {
                lblSiguiente.setText("Siguiente en fila: Ninguno");
            }
        }

        if (lblListaPendientes != null) {
            lblListaPendientes.setText(servicio.getPendientesFormateado());
        }
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
