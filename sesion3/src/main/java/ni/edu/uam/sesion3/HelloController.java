package ni.edu.uam.sesion3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.sesion3.utils.MisDatos;

public class HelloController {

    MisDatos datos = new MisDatos();
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txtNumber;

    @FXML
    private Label lblMax;

    @FXML
    private Label lblMin;

    @FXML
    private Label lblSerieCompleta;

    @FXML
    private Label lblAvg;

    @FXML
    private Label lblAdd;

    @FXML
    private Label lblPairs;

    @FXML
    private Label lblOdds;

    @FXML
    private Label lblMensaje;


    @FXML
    protected void onCalcClick()
    {
        add();
    }

    @FXML
    protected void onResetClick() {
        datos.reset();
        limpiarCampos();
        if (lblMensaje != null) lblMensaje.setText("Lista reiniciada con éxito.");
    }

    private void  add()
    {
        if (lblMensaje != null) lblMensaje.setText("");

        if (datos.isFull()) {
            if (lblMensaje != null) {
                lblMensaje.setText("La lista está llena (10/10). Utilice el botón Reiniciar.");
            }
            return;
        }

        String input = txtNumber.getText().trim();
        if (input.isEmpty()) {
            if (lblMensaje != null) lblMensaje.setText("Por favor, ingrese un número.");
            return;
        }

        try {
            int number = Integer.parseInt(input);
            datos.add(number);

            lblMax.setText("Max: " + datos.getMax());
            lblMin.setText("Min: " + datos.getMin());
            lblSerieCompleta.setText("Serie: [" + datos.getSerieCompleta() + "]");
            lblAvg.setText(String.format("Promedio: %.2f", datos.getPromedio()));

            if (lblAdd != null) lblAdd.setText("Suma: " + datos.getSuma());
            if (lblPairs != null) {
                lblPairs.setText("Pares (" + datos.getCantidadPares() + "): " + datos.getPares());
            }
            if (lblOdds != null) {
                lblOdds.setText("Impares (" + datos.getCantidadImpares() + "): " + datos.getImpares());
            }

            txtNumber.clear();
            txtNumber.requestFocus();

            if (datos.isFull() && lblMensaje != null) {
                lblMensaje.setText("¡Se han ingresado los 10 números!");
            }

        } catch (NumberFormatException e) {
            if (lblMensaje != null) {
                lblMensaje.setText("Error: Ingrese únicamente números enteros válidos.");
            }
            txtNumber.selectAll();
            txtNumber.requestFocus();
        }
    }

    private void limpiarCampos() {
        lblMax.setText("Max: -");
        lblMin.setText("Min: -");
        lblSerieCompleta.setText("Serie: []");
        lblAvg.setText("Promedio: -");
        if (lblAdd != null) lblAdd.setText("Suma: -");
        if (lblPairs != null) lblPairs.setText("Pares: -");
        if (lblOdds != null) lblOdds.setText("Impares: -");
        txtNumber.clear();
        txtNumber.requestFocus();
    }
}
