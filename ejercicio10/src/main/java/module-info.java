module ni.edu.uam.ejercicio10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio10 to javafx.fxml;
    exports ni.edu.uam.ejercicio10;
}