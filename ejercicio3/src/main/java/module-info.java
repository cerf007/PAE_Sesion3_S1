module ni.edu.uam.ejercicio3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio3 to javafx.fxml;
    exports ni.edu.uam.ejercicio3;
}