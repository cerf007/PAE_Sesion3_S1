module ni.edu.uam.ejercicio13 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio13 to javafx.fxml;
    exports ni.edu.uam.ejercicio13;
}