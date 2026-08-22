module ni.edu.uam.ejercicio5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio5 to javafx.fxml;
    exports ni.edu.uam.ejercicio5;
}