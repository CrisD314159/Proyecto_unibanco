package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PanelPrincipalController {

    private Main main;

    @FXML
    private Button crearCuentaButton;

    @FXML
    private Button depositoButton;

    @FXML
    private Button retiroButton;

    @FXML
    private Button saldoButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    void consultarSaldoAction(ActionEvent event) {
        consultarSaldoEvent(event);

    }

    private void consultarSaldoEvent(ActionEvent event) {
        main.mostrarConsultarSaldo();
    }

    @FXML
    void crearCuentaAction(ActionEvent event) {
        crearCuentaEvent(event);
    }

    private void crearCuentaEvent(ActionEvent event) {
        main.mostrarCrearCuenta();
    }

    @FXML
    void realizarDepositoAction(ActionEvent event) {
        realiarDepositoEvent(event);
    }

    private void realiarDepositoEvent(ActionEvent event) {
        main.mostrarRealizarDeposito();
    }

    @FXML
    void realizarRetiroAction(ActionEvent event) {
        realiarRetiroEvent(event);
    }

    private void realiarRetiroEvent(ActionEvent event) {
        main.mostrarRealizarRetiro();
    }






    public void setMain(application.Main main) {
        this.main = main;
    }
}
