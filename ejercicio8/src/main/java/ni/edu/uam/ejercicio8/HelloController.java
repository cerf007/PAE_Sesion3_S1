package ni.edu.uam.ejercicio8;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.CalculadoraService;

public class HelloController {
    private final CalculadoraService servicio = new CalculadoraService();

    @FXML private TextField txtNum1;
    @FXML private TextField txtNum2;

    @FXML private Label lblResultado;
    @FXML private Label lblHistorial;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        txtNum1.setOnAction(e -> txtNum2.requestFocus());
    }

    @FXML
    protected void onSumarClick() {
        procesarOperacion('+');
    }

    @FXML
    protected void onRestarClick() {
        procesarOperacion('-');
    }

    @FXML
    protected void onMultiplicarClick() {
        procesarOperacion('*');
    }

    @FXML
    protected void onDividirClick() {
        procesarOperacion('/');
    }

    private void procesarOperacion(char operacion) {
        limpiarMensaje();

        try {
            double n1 = Double.parseDouble(txtNum1.getText().trim());
            double n2 = Double.parseDouble(txtNum2.getText().trim());
            double resultado = 0;

            switch (operacion) {
                case '+' -> resultado = servicio.sumar(n1, n2);
                case '-' -> resultado = servicio.restar(n1, n2);
                case '*' -> resultado = servicio.multiplicar(n1, n2);
                case '/' -> resultado = servicio.dividir(n1, n2);
            }

            lblResultado.setText(String.format("Resultado: %.2f", resultado));
            actualizarHistorial();

        } catch (NumberFormatException e) {
            mostrarMensaje("Error: Ingrese valores numéricos válidos en ambos campos.");
        } catch (ArithmeticException e) {
            mostrarMensaje(e.getMessage());
        }
    }

    @FXML
    protected void onLimpiarHistorialClick() {
        limpiarMensaje();
        servicio.limpiarHistorial();
        actualizarHistorial();
        lblResultado.setText("Resultado: -");
    }

    private void actualizarHistorial() {
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
