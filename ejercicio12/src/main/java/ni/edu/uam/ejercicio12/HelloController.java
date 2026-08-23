package ni.edu.uam.ejercicio12;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio12.services.InversorServices;

public class HelloController {
    private final InversorServices servicio = new InversorServices();

    @FXML private TextField txtTexto;
    @FXML private Label lblResultado;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        txtTexto.setOnAction(event -> onInvertirClick());
    }

    @FXML
    protected void onInvertirClick() {
        limpiarMensajes();

        String texto = txtTexto.getText();
        if (texto == null || texto.trim().isEmpty()) {
            mostrarMensaje("Ingrese una palabra o frase para invertir.");
            lblResultado.setText("Resultado: -");
            return;
        }

        String resultado = servicio.invertirTexto(texto);
        lblResultado.setText("Resultado: " + resultado);
    }

    private void limpiarMensajes() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }

}
