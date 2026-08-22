package ni.edu.uam.ejercicio6;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio6.models.Contacto;
import ni.edu.uam.ejercicio6.services.GestionContactos;

import java.util.List;

public class HelloController {
    private final GestionContactos gestion = new GestionContactos();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtBuscar;


    @FXML
    private Label lblCantidad;

    @FXML
    private Label lblResultadoBusqueda;

    @FXML
    private Label lblListaCompleta;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        txtNombre.setOnAction(event -> txtTelefono.requestFocus());
        txtTelefono.setOnAction(event -> txtCorreo.requestFocus());
        txtCorreo.setOnAction(event -> onAgregarClick());
        txtBuscar.setOnAction(event -> onBuscarClick());
    }

    @FXML
    protected void onAgregarClick() {
        limpiarMensaje();

        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText() != null ? txtTelefono.getText().trim() : "";
        String correo = txtCorreo.getText() != null ? txtCorreo.getText().trim() : "";

        if (nombre.isEmpty()) {
            mostrarMensaje("El campo de nombre es obligatorio.");
            return;
        }

        if (telefono.isEmpty()) {
            mostrarMensaje("El campo de teléfono es obligatorio.");
            return;
        }

        if (!telefono.matches("\\d{8}")) {
            mostrarMensaje("El teléfono debe contener exactamente 8 dígitos numéricos.");
            txtTelefono.requestFocus();
            return;
        }

        // Validación de correo opcional con Regex estricto
        if (!correo.isEmpty()) {
            String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!correo.matches(regexEmail)) {
                mostrarMensaje("El correo no tiene un formato válido (ejemplo: nombre@dominio.com).");
                txtCorreo.requestFocus();
                return;
            }
        }

        Contacto contacto = new Contacto(nombre, telefono, correo);
        int resultado = gestion.agregar(contacto);

        if (resultado == 1) {
            mostrarMensaje("Contacto guardado correctamente.");
            actualizarVista();
            limpiarCampos();
        } else if (resultado == -1) {
            mostrarMensaje("Error: Ya existe un contacto con ese número de teléfono.");
        } else if (resultado == -2) {
            mostrarMensaje("Error: El correo especificado ya está registrado por otro contacto.");
        }
    }

    @FXML
    protected void onBuscarClick() {
        limpiarMensaje();
        String busqueda = txtBuscar.getText().trim();

        if (busqueda.isEmpty()) {
            mostrarMensaje("Ingrese un nombre o teléfono para buscar.");
            return;
        }

        List<Contacto> encontrados = gestion.buscarCoincidencias(busqueda);

        if (!encontrados.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Contacto c : encontrados) {
                sb.append(c.toString()).append("\n");
            }
            if (lblResultadoBusqueda != null) {
                lblResultadoBusqueda.setText("Coincidencias (" + encontrados.size() + "):\n" + sb.toString().trim());
            }
            mostrarMensaje("Se encontraron " + encontrados.size() + " coincidencia(s).");
        } else {
            if (lblResultadoBusqueda != null) {
                lblResultadoBusqueda.setText("Encontrado: Ninguno");
            }
            mostrarMensaje("No se encontró ningún contacto con ese criterio.");
        }
    }

    @FXML
    protected void onEliminarClick() {
        limpiarMensaje();
        String telefono = txtBuscar.getText().trim();

        if (telefono.isEmpty()) {
            mostrarMensaje("Para eliminar, ingrese el NÚMERO DE TELÉFONO del contacto.");
            return;
        }

        if (!telefono.matches("\\d{8}")) {
            mostrarMensaje("Ingrese un teléfono válido de 8 dígitos para eliminar.");
            return;
        }

        if (gestion.eliminarPorTelefono(telefono)) {
            mostrarMensaje("Contacto con teléfono " + telefono + " eliminado correctamente.");
            if (lblResultadoBusqueda != null) {
                lblResultadoBusqueda.setText("Encontrado: -");
            }
            txtBuscar.clear();
            actualizarVista();
        } else {
            mostrarMensaje("No se pudo eliminar: El número de teléfono no existe en la agenda.");
        }
    }

    private void actualizarVista() {
        if (lblCantidad != null) {
            lblCantidad.setText("Total Contactos: " + gestion.getCantidad());
        }
        if (lblListaCompleta != null) {
            lblListaCompleta.setText(gestion.getListaFormateada());
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtNombre.requestFocus();
    }

    private void limpiarMensaje() {
        mostrarMensaje("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) {
            lblMensaje.setText(msj);
        }
    }
}
