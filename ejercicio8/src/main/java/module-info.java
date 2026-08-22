module ni.edu.uam.ejercicio8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio8 to javafx.fxml;
    exports ni.edu.uam.ejercicio8;
}