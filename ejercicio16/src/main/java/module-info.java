module ni.edu.uam.ejercicio16 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio16 to javafx.fxml;
    exports ni.edu.uam.ejercicio16;
}