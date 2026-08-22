module ni.edu.uam.ejercicio7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.ejercicio7 to javafx.fxml;
    exports ni.edu.uam.ejercicio7;
}