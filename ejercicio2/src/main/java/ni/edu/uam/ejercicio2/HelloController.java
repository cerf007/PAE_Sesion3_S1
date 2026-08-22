package ni.edu.uam.ejercicio2;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio2.utils.BusquedaArreglo;

public class HelloController {
    private BusquedaArreglo datos = new BusquedaArreglo();

    @FXML private TextField txtTamano;
    @FXML private TextField txtNumber;
    @FXML private TextField txtSearch;

    @FXML private Label lblSerie;
    @FXML private Label lblExiste;
    @FXML private Label lblPosicion;
    @FXML private Label lblConteo;
    @FXML private Label lblMensaje;


    @FXML
    protected void onSetTamanoClick() {
        String input = txtTamano.getText().trim();
        if (input.isEmpty()) {
            if (lblMensaje != null) lblMensaje.setText("Ingrese una cantidad válida.");
            return;
        }

        try {
            int tamano = Integer.parseInt(input);
            if (tamano <= 0) {
                if (lblMensaje != null) lblMensaje.setText("El tamaño debe ser mayor a 0.");
                return;
            }

            datos.inicializar(tamano);
            txtTamano.setDisable(true);
            lblSerie.setText("Arreglo: []");
            if (lblMensaje != null) {
                lblMensaje.setText("Tamaño definido a " + tamano + ". Ingrese números.");
            }
            txtNumber.requestFocus();

        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese un entero válido para el tamaño.");
            txtTamano.selectAll();
        }
    }

    @FXML
    protected void onAddClick() {
        if (lblMensaje != null) lblMensaje.setText("");

        if (!datos.isInicializado()) {
            if (lblMensaje != null) lblMensaje.setText("Primero debe presionar 'Definir Tamaño'.");
            txtTamano.requestFocus();
            return;
        }

        if (datos.isFull()) {
            if (lblMensaje != null) {
                lblMensaje.setText("El arreglo está lleno (" + datos.getPos() + "/" + datos.getCapacidadTotal() + ").");
            }
            return;
        }

        String input = txtNumber.getText().trim();
        if (input.isEmpty()) {
            if (lblMensaje != null) lblMensaje.setText("Ingrese un número para agregar.");
            return;
        }

        try {
            int num = Integer.parseInt(input);
            datos.add(num);
            lblSerie.setText("Arreglo: [" + datos.getSerieCompleta() + "]");

            txtNumber.clear();
            txtNumber.requestFocus();

            if (datos.isFull() && lblMensaje != null) {
                lblMensaje.setText("Arreglo lleno (" + datos.getCapacidadTotal() + "/" + datos.getCapacidadTotal() + "). ¡Listo para buscar!");
            } else if (lblMensaje != null) {
                lblMensaje.setText("Ingresados: " + datos.getPos() + "/" + datos.getCapacidadTotal());
            }
        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese solo números enteros.");
            txtNumber.selectAll();
        }
    }

    @FXML
    protected void onSearchClick() {
        if (lblMensaje != null) lblMensaje.setText("");

        if (!datos.isInicializado() || datos.getPos() == 0) {
            if (lblMensaje != null) lblMensaje.setText("Primero ingrese datos al arreglo.");
            return;
        }

        String input = txtSearch.getText().trim();
        if (input.isEmpty()) {
            if (lblMensaje != null) lblMensaje.setText("Ingrese un número a buscar.");
            return;
        }

        try {
            int target = Integer.parseInt(input);
            boolean existe = datos.existe(target);

            if (existe) {
                lblExiste.setText("¿Existe?: SÍ");
                lblPosicion.setText("Posición(es) [Índice]: " + (datos.getPosiciones(target)));
                lblConteo.setText("Ocurrencias: " + datos.contarOcurrencias(target));
            } else {
                lblExiste.setText("¿Existe?: NO");
                lblPosicion.setText("Posición(es): N/A");
                lblConteo.setText("Ocurrencias: 0");
            }
            txtSearch.requestFocus();
        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese un entero válido para buscar.");
            txtSearch.selectAll();
        }
    }

    @FXML
    protected void onResetClick() {
        datos.reset();
        txtTamano.setDisable(false);
        txtTamano.clear();
        txtNumber.clear();
        txtSearch.clear();

        lblSerie.setText("Arreglo: []");
        lblExiste.setText("¿Existe?: -");
        lblPosicion.setText("Posición(es): -");
        lblConteo.setText("Ocurrencias: -");
        if (lblMensaje != null) lblMensaje.setText("Reiniciado. Ingrese un nuevo tamaño.");

        txtTamano.requestFocus();
    }

}
