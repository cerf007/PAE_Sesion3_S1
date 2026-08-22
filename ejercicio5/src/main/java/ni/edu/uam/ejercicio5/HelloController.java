package ni.edu.uam.ejercicio5;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio5.models.Estudiante;
import ni.edu.uam.ejercicio5.services.GestionEstudiantes;

public class HelloController {
    private GestionEstudiantes gestion = new GestionEstudiantes();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCif;

    @FXML
    private TextField txtAnio;

    @FXML
    private TextField txtCarrera;

    @FXML
    private TextField txtBuscarCif;


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
        txtNombre.setOnAction(event -> txtCif.requestFocus());
        txtCif.setOnAction(event -> txtAnio.requestFocus());
        txtAnio.setOnAction(event -> txtCarrera.requestFocus());
        txtCarrera.setOnAction(event -> onAgregarClick());
    }


    @FXML
    protected void onAgregarClick() {
        if (lblMensaje != null) lblMensaje.setText("");

        String nombre = txtNombre.getText().trim();
        String cif = txtCif.getText().trim();
        String anioStr = txtAnio.getText().trim();
        String carrera = txtCarrera.getText().trim();

        if (nombre.isEmpty() || cif.isEmpty() || anioStr.isEmpty() || carrera.isEmpty()) {
            lblMensaje.setText("Todos los campos son obligatorios.");
            return;
        }

        if (!cif.matches("\\d{8}")) {
            lblMensaje.setText("El CIF debe contener exactamente 8 números.");
            txtCif.requestFocus();
            return;
        }

        try {
            int anio = Integer.parseInt(anioStr);
            if (anio < 1 || anio > 6) {
                lblMensaje.setText("El año académico debe estar entre 1 y 6.");
                txtAnio.requestFocus();
                return;
            }

            Estudiante estudiante = new Estudiante(nombre, cif, anio, carrera);
            if (gestion.agregar(estudiante)) {
                lblMensaje.setText("Estudiante agregado correctamente.");
                actualizarVista();
                limpiarCamposEntrada();
            } else {
                lblMensaje.setText("Error: Ya existe un estudiante con ese CIF.");
            }

        } catch (NumberFormatException e) {
            lblMensaje.setText("El año debe ser un número entero.");
            txtAnio.requestFocus();
        }
    }

    @FXML
    protected void onBuscarClick() {
        if (lblMensaje != null) lblMensaje.setText("");
        String cif = txtBuscarCif.getText().trim();

        if (cif.isEmpty()) {
            lblMensaje.setText("Ingrese un CIF para buscar.");
            return;
        }

        Estudiante encontrado = gestion.buscarPorCif(cif);
        if (encontrado != null) {
            lblResultadoBusqueda.setText("Encontrado: " + encontrado.toString());
            lblMensaje.setText("Búsqueda exitosa.");
        } else {
            lblResultadoBusqueda.setText("Encontrado: Ninguno");
            lblMensaje.setText("No se encontró ningún estudiante con el CIF: " + cif);
        }
    }

    @FXML
    protected void onEliminarClick() {
        if (lblMensaje != null) lblMensaje.setText("");
        String cif = txtBuscarCif.getText().trim();

        if (cif.isEmpty()) {
            lblMensaje.setText("Ingrese un CIF para eliminar.");
            return;
        }

        if (gestion.eliminarPorCif(cif)) {
            lblMensaje.setText("Estudiante con CIF " + cif + " eliminado.");
            lblResultadoBusqueda.setText("Encontrado: -");
            txtBuscarCif.clear();
            actualizarVista();
        } else {
            lblMensaje.setText("No se pudo eliminar: El CIF no existe.");
        }
    }

    private void actualizarVista() {
        lblCantidad.setText("Total Estudiantes: " + gestion.getCantidad());
        if (lblListaCompleta != null) {
            lblListaCompleta.setText(gestion.getListaFormateada());
        }
    }

    private void limpiarCamposEntrada() {
        txtNombre.clear();
        txtCif.clear();
        txtAnio.clear();
        txtCarrera.clear();
        txtNombre.requestFocus();
    }
}
