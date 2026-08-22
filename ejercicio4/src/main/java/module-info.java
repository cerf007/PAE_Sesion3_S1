module ni.edu.uam.ejercicio4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio4 to javafx.fxml;
    exports ni.edu.uam.ejercicio4;
}