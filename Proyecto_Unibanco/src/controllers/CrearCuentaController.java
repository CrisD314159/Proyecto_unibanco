package controllers;

import application.Main;
import exceptions.ClienteException;
import exceptions.CuentaException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Cliente;
import model.Cuenta;
import model.TipoCuenta;

import java.net.URL;
import java.util.ResourceBundle;

public class CrearCuentaController implements Initializable {

    private TipoCuenta[] tipo ={TipoCuenta.AHORROS, TipoCuenta.CORRIENTE};
    @FXML
    private ChoiceBox<TipoCuenta> TipoCuentaChoice;

    private Main main;
    @FXML
    private TextField apellidoLabel;

    @FXML
    private Button cancelarButtom;

    @FXML
    private TextField cedulaLabel;

    @FXML
    private Button crearCuentaButtom;

    @FXML
    private TextField direccionLabel;

    @FXML
    private TextField emailLabel;

    @FXML
    private TextField nombreLabel;

    @FXML
    void crearCuentaAction(ActionEvent event) throws ClienteException, CuentaException {

        String nombre="";
        String apellido="";
        String cedula="";
        String direccion="";
        String email="";
        TipoCuenta tipo = TipoCuentaChoice.getValue();
        nombre= nombreLabel.getText();
        apellido= apellidoLabel.getText();
        cedula=cedulaLabel.getText();
        email=emailLabel.getText();
        if(verificarCampos(nombre, apellido, cedula, direccion, email)){
            Cuenta cuentaCreada= main.crearCuenta(cedula, tipo);
            Cliente clienteCreado = main.crearCliente(nombre, apellido, cedula, direccion, email, cuentaCreada);

            if(clienteCreado != null){
                invocarAlerta("Cuenta creada con exito, ahora, tu numero de cuenta es "+ cuentaCreada.getNumeroCuenta(), "Listo");
            }else {
                invocarAlerta("Imposible crear la cuenta", "Error");
            }
        }else {
            invocarAlerta("Verifica los campos y vuelve a intentar", "Error");
        }


    }
    /**
     * Este método genera un alerta flotante
     * @param
     * @param
     */
    private boolean verificarCampos(String nombre, String apellido, String cedula, String direccion, String email) {
        if(nombre.equals("")){
            return false;
        }
        if(apellido.equals("")){
            return false;
        }
        if(cedula.equals("")){
            return false;
        }

        if(email.equals("")){
            return false;
        }
        return true;
    }

    @FXML
    void volverAction(ActionEvent event) {
        main.mostrarPanelPrincipal();
    }
    private void invocarAlerta(String mensaje, String header) {
        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(header);
        alerta.setContentText(mensaje);
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../Stylesheets/AlertsStylesheets.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        alerta.showAndWait();
    }

    public void setMain(Main main) {
        this.main=main;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TipoCuentaChoice.getItems().setAll(tipo);

    }
}
