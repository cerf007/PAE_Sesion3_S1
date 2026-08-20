module ni.edu.uam.sesion3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.sesion3 to javafx.fxml;
    exports ni.edu.uam.sesion3;
}