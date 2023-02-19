package application;

import controllers.*;
import exceptions.ClienteException;
import exceptions.CuentaException;
import exceptions.TransaccionException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;


import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    Banco banco = new Banco("Unibanco");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)  {
        this.stage=stage;
        mostrarPanelPrincipal();
    }




    public void mostrarPanelPrincipal() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(Main.class.getResource("../views/PanelPrincipal.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores

            PanelPrincipalController controller = loader.getController();
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("../Stylesheets/BackgroundFont.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Que bueno verte de vuelta!!");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarConsultarSaldo() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(Main.class.getResource("../views/ConsultarSaldo.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores

            ConsultarSaldoController controller = loader.getController();
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("../Stylesheets/BackgroundFont.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Consulta aquí el saldo de tu cuenta!!");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarCrearCuenta() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(Main.class.getResource("../views/CrearCuenta.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores

            CrearCuentaController controller = loader.getController();
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("../Stylesheets/BackgroundFont.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Crea tu cuenta fácil y rápido!!!");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarRealizarDeposito() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(Main.class.getResource("../views/RealizarDeposito.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores

            RealizarDepositoController controller = loader.getController();
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("../Stylesheets/BackgroundFont.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Realiza un depósito fácil y rápido");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarTansaccion(Transaccion depositoRealizado) {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(Main.class.getResource("../views/ReciboTransaccion.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores

            ReciboTransaccionController controller = loader.getController();
            controller.setMain(this);
            controller.enviarTransaccion(depositoRealizado);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("../Stylesheets/BackgroundFont.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Transacción realizada con éxito");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void mostrarRealizarRetiro() {
        try{
            //carga el fxml
            FXMLLoader loader = new FXMLLoader();
            //localiza el fxml
            loader.setLocation(Main.class.getResource("../views/RealizarRetiro.fxml"));
            AnchorPane rootLayout = loader.load();
            //invoca los controladores

            RealizarRetiroController controller = loader.getController();
            controller.setMain(this);
            //inicializa la escena
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("../Stylesheets/BackgroundFont.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Retira tu dinero de manera segura");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Transaccion realizarDeposito(int nCuenta, String cedula, double monto) throws TransaccionException {
        return banco.depositarDinero(nCuenta,cedula,monto);
    }

    public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) throws ClienteException {
        return banco.crearCliente(nombre, apellido,cedula,direccion,email, cuenta);
    }

    public Cuenta crearCuenta(String cedula, TipoCuenta tipo) throws CuentaException {
        return banco.crearCuenta(cedula, tipo);
    }


    public Transaccion realizarRetiro(int nCuenta, String cedula, double monto) throws TransaccionException {
        return banco.retirarDinero(nCuenta, cedula, monto);
    }

    public Transaccion obtenerCuentaTransaccion(int cuenta, String cedula) throws TransaccionException {
        return banco.solicitarSaldo(cuenta, cedula);
    }

    public Cuenta obtenerCuenta(int cuenta, String cedula) throws CuentaException {
        return banco.obtenerCuenta(cuenta, cedula);
    }
}
