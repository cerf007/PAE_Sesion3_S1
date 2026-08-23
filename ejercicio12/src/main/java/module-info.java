module ni.edu.uam.ejercicio12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio12 to javafx.fxml;
    exports ni.edu.uam.ejercicio12;
}