module ni.edu.uam.ejercicio14 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio14 to javafx.fxml;
    exports ni.edu.uam.ejercicio14;
}