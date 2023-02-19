package controllers;

import application.Main;
import exceptions.TransaccionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import model.Transaccion;

public class RealizarRetiroController {

    private Main main;
    @FXML
    private Button cancelarButton;

    @FXML
    private TextField cedulaField;

    @FXML
    private TextField cuentaField;

    @FXML
    private TextField montoField;

    @FXML
    private Button realizarRetiroButton;

    @FXML
    void realizarRetiroAction(ActionEvent event) throws TransaccionException {
        realizarRetiroEvent(event);

    }

    private void realizarRetiroEvent(ActionEvent event) throws TransaccionException {
        int nCuenta = 0;
        String cedula = "";
        double monto = 0;
        nCuenta = Integer.parseInt(cuentaField.getText());
        cedula = cedulaField.getText();
        monto = Double.parseDouble(montoField.getText());
        if(verificarCampos(nCuenta,cedula,monto)){
            Transaccion retiro = main.realizarRetiro(nCuenta, cedula, monto);
            if(retiro != null){
                invocarAlerta("Retiro realizado con exito", "Listo");
                main.mostrarTansaccion(retiro);
            }else{
                invocarAlerta("No fue posible realizar el retiro", "Error");
            }
        }else{
            invocarAlerta("Verifica los datos e intenta de nuevo", "Error");
        }

    }
    /**
     * Este método genera un alerta flotante
     * @param
     * @param
     */
    private void invocarAlerta(String mensaje, String header) {
        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(header);
        alerta.setContentText(mensaje);
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../Stylesheets/AlertsStylesheets.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        alerta.showAndWait();
    }

    private boolean verificarCampos(int nCuenta, String cedula, double monto) {
        if(nCuenta == 0){
            return false;
        }
        if (cedula.equals("")){
            return false;
        }
        if(monto==0){
            return false;
        }
        return true;
    }

    @FXML
    void volverAction(ActionEvent event) {
        main.mostrarPanelPrincipal();

    }

    public void setMain(Main main) {
        this.main=main;
    }
}
