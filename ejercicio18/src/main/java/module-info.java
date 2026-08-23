module ni.edu.uam.ejercicio18 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio18 to javafx.fxml;
    exports ni.edu.uam.ejercicio18;
}