package controllers;

import application.Main;
import exceptions.CuentaException;
import exceptions.TransaccionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import model.Cuenta;
import model.Transaccion;

public class ConsultarSaldoController {

    private Main main;
    @FXML
    private TextField cedulaField;

    @FXML
    private TextField cuentaField;

    @FXML
    private Button saldoButton;

    @FXML
    void volverAction(ActionEvent event) {
        main.mostrarPanelPrincipal();
    }

    public void consultarSaldoAction(ActionEvent event) throws CuentaException, TransaccionException {
        String cedula ="";
        int cuenta = 0;
        cedula = cedulaField.getText();
        cuenta = Integer.parseInt(cuentaField.getText());
        if(verificarCampos(cuenta, cedula)){
            Transaccion obtenerTransaccion = main.obtenerCuentaTransaccion(cuenta, cedula);
            Cuenta obtenerCuenta = main.obtenerCuenta(cuenta, cedula);
            if(obtenerTransaccion != null && obtenerCuenta!= null){
                invocarAlerta("El saldo de tu cuenta es: "+ obtenerCuenta.getSaldo(), "Listo!");
                main.mostrarTansaccion(obtenerTransaccion);
            }else{
                invocarAlerta("No se puedo realizar la consulta, intenta de nuevo", "Error");
            }

        }else {
            invocarAlerta("Verifica los datos ingresados e intentalo de nuevo", "Error");
        }
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

    private boolean verificarCampos(int nCuenta, String cedula) {
        if(nCuenta == 0){
            return false;
        }
        if (cedula.equals("")){
            return false;
        }

        return true;
    }

    public void setMain(Main main) {
        this.main=main;
    }
}
