package ni.edu.uam.ejercicio17;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.ejercicio17.model.Libro;
import ni.edu.uam.ejercicio17.services.BibliotecaService;

public class HelloController {

    private final BibliotecaService servicio = new BibliotecaService();

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtCodigoAccion;


    @FXML
    private Label lblListaLibros;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblDisponibles;

    @FXML
    private Label lblMensaje;

    @FXML
    public void initialize() {
        txtTitulo.setOnAction(event -> txtAutor.requestFocus());
        txtAutor.setOnAction(event -> onRegistrarClick());
    }

    @FXML
    protected void onRegistrarClick() {
        limpiarMensaje();
        String titulo = txtTitulo.getText().trim();
        String autor = txtAutor.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty()) {
            mostrarMensaje("Complete los campos de Título y Autor.");
            return;
        }

        Libro nuevo = servicio.registrarLibro(titulo, autor);
        mostrarMensaje("Libro registrado. Código asignado: " + nuevo.getCodigo());

        txtTitulo.clear();
        txtAutor.clear();
        txtTitulo.requestFocus();
        actualizarVista();
    }

    @FXML
    protected void onBuscarClick() {
        limpiarMensaje();
        String codigo = txtCodigoAccion.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje("Ingrese el código UUID del libro a buscar.");
            return;
        }

        Libro libro = servicio.buscarLibro(codigo);
        if (libro != null) {
            mostrarMensaje("Encontrado: " + libro.toString());
        } else {
            mostrarMensaje("No se encontró ningún libro con el código: " + codigo);
        }
    }

    @FXML
    protected void onPrestarClick() {
        limpiarMensaje();
        String codigo = txtCodigoAccion.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje("Ingrese el código UUID del libro a prestar.");
            return;
        }

        if (servicio.prestarLibro(codigo)) {
            mostrarMensaje("Libro " + codigo + " prestado con éxito.");
            txtCodigoAccion.clear();
            actualizarVista();
        } else {
            mostrarMensaje("No se pudo prestar (código no existe o el libro ya está prestado).");
        }
    }

    @FXML
    protected void onDevolverClick() {
        limpiarMensaje();
        String codigo = txtCodigoAccion.getText().trim();
        if (codigo.isEmpty()) {
            mostrarMensaje("Ingrese el código UUID del libro a devolver.");
            return;
        }

        if (servicio.devolverLibro(codigo)) {
            mostrarMensaje("Libro " + codigo + " devuelto con éxito.");
            txtCodigoAccion.clear();
            actualizarVista();
        } else {
            mostrarMensaje("No se pudo devolver (código no existe o el libro ya estaba disponible).");
        }
    }

    private void actualizarVista() {
        if (lblListaLibros != null) {
            lblListaLibros.setText(servicio.getListaFormateada());
        }
        if (lblTotal != null) {
            lblTotal.setText("Total de libros: " + servicio.getCantidadTotal());
        }
        if (lblDisponibles != null) {
            lblDisponibles.setText("Libros disponibles: " + servicio.contarLibrosDisponibles());
        }
    }

    private void limpiarMensaje() {
        if (lblMensaje != null) lblMensaje.setText("");
    }

    private void mostrarMensaje(String msj) {
        if (lblMensaje != null) lblMensaje.setText(msj);
    }

}
