module ni.edu.uam.ejercicio9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio9 to javafx.fxml;
    exports ni.edu.uam.ejercicio9;
}