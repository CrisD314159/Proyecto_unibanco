package controllers;

import application.Main;
import exceptions.TransaccionException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Transaccion;

public class RealizarDepositoController {

    private Main main;
    @FXML
    private Button CancelarButtom;

    @FXML
    private TextField CedulaField;

    @FXML
    private Button DepositoButtom;

    @FXML
    private TextField MontoField;

    @FXML
    private TextField NcuentaField;

    @FXML
    private Label NcuentaLabel;

    @FXML
    private Label CedulaLabel;

    @FXML
    private Label MontoLabel;


    @FXML
    void realizarDepositoAction(ActionEvent event) throws TransaccionException {
        realizarDepositoEvent(event);
    }

    private void realizarDepositoEvent(ActionEvent event) throws TransaccionException {
        int nCuenta =0;
        String cedula ="";
        double monto=0;
        nCuenta = Integer.parseInt(NcuentaField.getText());
        cedula= CedulaField.getText();
        monto = Double.parseDouble(MontoField.getText());
        if (verificarCampos(nCuenta, cedula, monto)){
            Transaccion depositoRealizado = main.realizarDeposito(nCuenta, cedula, monto);
            if(depositoRealizado != null){
                invocarAlerta("Deposito Realizado Exitosamente!!", "Listo!!");
                main.mostrarTansaccion(depositoRealizado);
            }else{
                invocarAlerta("No se pudo realizar el deposito, intenta de nuevo", "Error!!");
                main.mostrarTansaccion(depositoRealizado);
            }
        }else {
            invocarAlerta("Verifica los datos ingresados y vuelve a intentar", "Error!!");
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



    public void setMain(Main main) {
        this.main=main;
    }

    public void volverAction(ActionEvent event) {
        volverEvent(event);
    }
    private void volverEvent(ActionEvent event) {
        main.mostrarPanelPrincipal();
    }
}
