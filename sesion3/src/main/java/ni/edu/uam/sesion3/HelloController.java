package ni.edu.uam.sesion3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.sesion3.utils.MisDatos;

public class HelloController {

    MisDatos datos = new MisDatos();
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txtNumber;

    @FXML
    private Label lblMax;

    @FXML
    private Label lblMin;

    @FXML
    private Label lblSerieCompleta;


    @FXML
    protected void onCalcClick()
    {
        add();
    }

    private void  add()
    {
        int number = Integer.parseInt(txtNumber.getText());
        datos.add(number);
        lblMax.setText("Max: " + datos.getMax());
        lblMin.setText("Min: " + datos.getMin());

    }
}
