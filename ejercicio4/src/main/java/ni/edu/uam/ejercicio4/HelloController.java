package ni.edu.uam.ejercicio4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio4.utils.EstadisticasNotas;

public class HelloController {
    private EstadisticasNotas datos = new EstadisticasNotas();

    @FXML
    private TextField txtTamano;

    @FXML
    private TextField txtNota;

    @FXML
    private Label lblNotas;

    @FXML
    private Label lblPromedio;

    @FXML
    private Label lblNotaMayor;

    @FXML
    private Label lblNotaMenor;

    @FXML
    private Label lblAprobados;

    @FXML
    private Label lblReprobados;

    @FXML
    private Label lblMensaje;

    @FXML
    protected void onSetTamanoClick() {
        String input = txtTamano.getText().trim();
        if (input.isEmpty()) {
            if (lblMensaje != null) lblMensaje.setText("Ingrese una cantidad de estudiantes.");
            return;
        }

        try {
            int tamano = Integer.parseInt(input);
            if (tamano <= 0) {
                if (lblMensaje != null) lblMensaje.setText("El número de estudiantes debe ser mayor a 0.");
                return;
            }

            datos.inicializar(tamano);
            txtTamano.setDisable(true);
            limpiarLabels();
            if (lblMensaje != null) {
                lblMensaje.setText("Registrando notas para " + tamano + " estudiantes.");
            }
            txtNota.requestFocus();

        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese un entero válido.");
            txtTamano.selectAll();
        }
    }

    @FXML
    protected void onAddClick() {
        if (lblMensaje != null) lblMensaje.setText("");

        if (!datos.isInicializado()) {
            if (lblMensaje != null) lblMensaje.setText("Primero presione 'Definir Tamaño'.");
            txtTamano.requestFocus();
            return;
        }

        if (datos.isFull()) {
            if (lblMensaje != null) {
                lblMensaje.setText("Se han ingresado todas las notas (" + datos.getPos() + "/" + datos.getCapacidadTotal() + ").");
            }
            return;
        }

        String input = txtNota.getText().trim();
        if (input.isEmpty()) {
            if (lblMensaje != null) lblMensaje.setText("Ingrese una nota.");
            return;
        }

        try {
            double nota = Double.parseDouble(input);

            if (nota < 0 || nota > 100) {
                if (lblMensaje != null) lblMensaje.setText("La nota debe estar entre 0 y 100.");
                txtNota.selectAll();
                return;
            }

            datos.add(nota);

            lblNotas.setText("Notas: [" + datos.getNotasRegistradas() + "]");
            lblPromedio.setText(String.format("Promedio General: %.2f", datos.getPromedio()));
            lblNotaMayor.setText(String.format("Nota Mayor: %.1f", datos.getNotaMayor()));
            lblNotaMenor.setText(String.format("Nota Menor: %.1f", datos.getNotaMenor()));
            lblAprobados.setText("Aprobados (>=70): " + datos.getCantidadAprobados());
            lblReprobados.setText("Reprobados (<70): " + datos.getCantidadReprobados());

            txtNota.clear();
            txtNota.requestFocus();

            if (datos.isFull() && lblMensaje != null) {
                lblMensaje.setText("¡Registro completado para todos los estudiantes!");
            } else if (lblMensaje != null) {
                lblMensaje.setText("Registradas: " + datos.getPos() + "/" + datos.getCapacidadTotal());
            }

        } catch (NumberFormatException e) {
            if (lblMensaje != null) lblMensaje.setText("Error: Ingrese una nota válida (ej: 85 o 85.5).");
            txtNota.selectAll();
        }
    }

    @FXML
    protected void onResetClick() {
        datos.reset();
        txtTamano.setDisable(false);
        txtTamano.clear();
        txtNota.clear();
        limpiarLabels();

        if (lblMensaje != null) lblMensaje.setText("Sistema reiniciado. Defina la cantidad de estudiantes.");
        txtTamano.requestFocus();
    }

    private void limpiarLabels() {
        lblNotas.setText("Notas: []");
        lblPromedio.setText("Promedio General: -");
        lblNotaMayor.setText("Nota Mayor: -");
        lblNotaMenor.setText("Nota Menor: -");
        lblAprobados.setText("Aprobados: -");
        lblReprobados.setText("Reprobados: -");
    }
}

