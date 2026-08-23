module ni.edu.uam.ejercicio17 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio17 to javafx.fxml;
    exports ni.edu.uam.ejercicio17;
}