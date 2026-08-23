package ni.edu.uam.ejercicio15;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio15.model.Documento;
import ni.edu.uam.ejercicio15.services.ImpresoraService;

public class HelloController {
    private final ImpresoraService servicio = new ImpresoraService();

    @FXML
    private TextField txtNombreDoc;

    @FXML
    private TextField txtPaginas;


    @FXML
    private Label lblDocumentoImprimiendo;

    @FXML
    private Label lblSiguiente;

    @FXML
    private Label lblPendientes;

    @FXML
    private Label lblListaPendientes;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        txtNombreDoc.setOnAction(event -> txtPaginas.requestFocus());
        txtPaginas.setOnAction(event -> onAgregarClick());
    }

    @FXML
    protected void onAgregarClick() {
        limpiarMensaje();

        String nombre = txtNombreDoc.getText().trim();
        String strPaginas = txtPaginas.getText().trim();

        if (nombre.isEmpty()) {
            mostrarMensaje("Ingrese el nombre del documento.");
            return;
        }

        try {
            int paginas = Integer.parseInt(strPaginas);
            if (paginas <= 0) {
                mostrarMensaje("El número de páginas debe ser mayor a 0.");
                return;
            }

            Documento nuevoDoc = new Documento(nombre, paginas);
            servicio.agregarDocumento(nuevoDoc);
            mostrarMensaje("Documento '" + nombre + "' enviado a la cola de impresión.");

            txtNombreDoc.clear();
            txtPaginas.clear();
            txtNombreDoc.requestFocus();
            actualizarVista();

        } catch (NumberFormatException e) {
            mostrarMensaje("Ingrese un número entero válido para las páginas.");
        }
    }

    @FXML
    protected void onImprimirClick() {
        limpiarMensaje();
        Documento impreso = servicio.imprimirDocumento();

        if (impreso != null) {
            lblDocumentoImprimiendo.setText("Imprimiendo: " + impreso.toString());
            mostrarMensaje("Imprimiendo documento: " + impreso.getNombre());
            actualizarVista();
        } else {
            lblDocumentoImprimiendo.setText("Imprimiendo: Ninguno");
            mostrarMensaje("La cola está vacía, no hay documentos para imprimir.");
        }
    }

    @FXML
    protected void onMostrarSiguienteClick() {
        limpiarMensaje();
        Documento siguiente = servicio.obtenerSiguiente();

        if (siguiente != null) {
            mostrarMensaje("El siguiente documento en cola es: " + siguiente.toString());
        } else {
            mostrarMensaje("No hay documentos en espera.");
        }
    }

    private void actualizarVista() {
        if (lblPendientes != null) {
            lblPendientes.setText("Documentos pendientes: " + servicio.getCantidadPendientes());
        }

        Documento siguiente = servicio.obtenerSiguiente();
        if (lblSiguiente != null) {
            if (siguiente != null) {
                lblSiguiente.setText("Siguiente a imprimir: " + siguiente.toString());
            } else {
                lblSiguiente.setText("Siguiente a imprimir: Ninguno");
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
