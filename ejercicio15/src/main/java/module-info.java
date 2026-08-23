module ni.edu.uam.ejercicio15 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio15 to javafx.fxml;
    exports ni.edu.uam.ejercicio15;
}