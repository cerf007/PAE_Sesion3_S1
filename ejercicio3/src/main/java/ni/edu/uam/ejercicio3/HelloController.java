package ni.edu.uam.ejercicio3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio3.utils.OrdenamientoArreglo;

public class HelloController {
    private OrdenamientoArreglo datos = new OrdenamientoArreglo();

    @FXML private TextField txtTamano;
    @FXML private TextField txtNumber;

    @FXML private Label lblOriginal;
    @FXML private Label lblOrdenado;
    @FXML private Label lblMensaje;

    // Asignar capacidad del arreglo
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
            lblOriginal.setText("Original: []");
            lblOrdenado.setText("Ordenado (MergeSort): []");
            if (lblMensaje != null) {
                lblMensaje.setText("Tamaño definido a " + tamano + ". Ingrese números.");
            }
            txtNumber.requestFocus();

        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese un entero válido para el tamaño.");
            txtTamano.selectAll();
        }
    }

    // Agregar número y recalcular
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

            // Se muestran ambas cadenas requeridas
            lblOriginal.setText("Original: [" + datos.getSerieOriginal() + "]");
            lblOrdenado.setText("Ordenado (MergeSort): [" + datos.getSerieOrdenada() + "]");

            txtNumber.clear();
            txtNumber.requestFocus();

            if (datos.isFull() && lblMensaje != null) {
                lblMensaje.setText("¡Arreglo lleno! Serie completada y ordenada.");
            } else if (lblMensaje != null) {
                lblMensaje.setText("Ingresados: " + datos.getPos() + "/" + datos.getCapacidadTotal());
            }
        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese solo números enteros.");
            txtNumber.selectAll();
        }
    }

    // Reiniciar
    @FXML
    protected void onResetClick() {
        datos.reset();
        txtTamano.setDisable(false);
        txtTamano.clear();
        txtNumber.clear();

        lblOriginal.setText("Original: []");
        lblOrdenado.setText("Ordenado (MergeSort): []");
        if (lblMensaje != null) lblMensaje.setText("Reiniciado. Ingrese un nuevo tamaño.");

        txtTamano.requestFocus();
    }
}
