module ni.edu.uam.ejercicio6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio6 to javafx.fxml;
    exports ni.edu.uam.ejercicio6;
}