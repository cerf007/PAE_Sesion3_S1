package ni.edu.uam.ejercicio9;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.PilaService;

public class HelloController {
    private final PilaService servicio = new PilaService();

    @FXML private TextField txtNumero;
    @FXML private Label lblPila;
    @FXML private Label lblMensaje;

    @FXML
    public void initialize() {
        txtNumero.setOnAction(e -> onPushClick());
    }

    @FXML
    protected void onPushClick() {
        limpiarMensaje();
        try {
            double num = Double.parseDouble(txtNumero.getText().trim());
            servicio.push(num);
            mostrarMensaje("Número " + num + " apilado correctamente.");
            txtNumero.clear();
            txtNumero.requestFocus();
            actualizarVista();
        } catch (NumberFormatException e) {
            mostrarMensaje("Error: Ingrese un valor numérico válido.");
        }
    }

    @FXML
    protected void onPopClick() {
        limpiarMensaje();
        Double desapilado = servicio.pop();
        if (desapilado != null) {
            mostrarMensaje("Elemento desapilado: " + desapilado);
            actualizarVista();
        } else {
            mostrarMensaje("Error: La pila está vacía, no hay elementos para desapilar.");
        }
    }

    @FXML
    protected void onPeekClick() {
        limpiarMensaje();
        Double tope = servicio.peek();
        if (tope != null) {
            mostrarMensaje("El elemento en el tope es: " + tope);
        } else {
            mostrarMensaje("La pila está vacía.");
        }
    }

    @FXML
    protected void onEstaVaciaClick() {
        limpiarMensaje();
        if (servicio.estaVacia()) {
            mostrarMensaje("La pila está VACÍA.");
        } else {
            mostrarMensaje("La pila CONTIENE elementos.");
        }
    }

    private void actualizarVista() {
        if (lblPila != null) {
            lblPila.setText(servicio.getPilaFormateada());
        }
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }

}
