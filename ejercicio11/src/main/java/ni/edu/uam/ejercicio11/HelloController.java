package ni.edu.uam.ejercicio11;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio11.services.VerificadorService;

public class HelloController {
    private final VerificadorService servicio = new VerificadorService();

    @FXML private TextField txtExpresion;
    @FXML private Label lblResultado;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        txtExpresion.setOnAction(event -> onVerificarClick());
    }

    @FXML
    protected void onVerificarClick() {
        limpiarMensajes();

        String expresion = txtExpresion.getText().trim();

        if (expresion.isEmpty()) {
            mostrarMensaje("Por favor, ingrese una expresión matemática.");
            return;
        }

        if (!expresion.contains("(") && !expresion.contains(")")) {
            lblResultado.setText("La expresión no contiene paréntesis.");
            return;
        }

        boolean esBalanceado = servicio.estaBalanceado(expresion);

        if (esBalanceado) {
            lblResultado.setText("Los paréntesis están CORRECTAMENTE balanceados.");
        } else {
            lblResultado.setText("Los paréntesis NO están balanceados.");
        }
    }

    private void limpiarMensajes() {
        if (lblMensaje != null) lblMensaje.setText("");
        if (lblResultado != null) lblResultado.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }
}
