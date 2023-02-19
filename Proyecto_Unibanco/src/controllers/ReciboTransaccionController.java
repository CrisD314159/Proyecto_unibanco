package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.TipoTransaccion;
import model.Transaccion;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReciboTransaccionController {

    private Main main;
    @FXML
    private TextField fechaField;

    @FXML
    private TextField horaField;

    @FXML
    private TextField montoField;

    @FXML
    private TextField referenciaField;

    @FXML
    private TextField tipoField;

    @FXML
    void volverInicioAction(ActionEvent event) {
        main.mostrarPanelPrincipal();
    }

    public void setMain(Main main) {
        this.main= main;
    }

    /**
     * Este método permite mostrar una transacción al usuario en forma de factura
     * @param deposito
     */
    public void enviarTransaccion(Transaccion deposito) {
        int referencia = deposito.getReferencia();
        double monto = deposito.getValor();
        Date fecha = new Date();
        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
        Date hora = new Date();
        TipoTransaccion tipo = deposito.getTipoTransaccion();
        referenciaField.setText(String.valueOf(referencia));
        montoField.setText(String.valueOf(monto));
        tipoField.setText(String.valueOf(tipo));
        fechaField.setText(dateFormat2.format(fecha));
        horaField.setText(dateFormat1.format(hora));
    }
}
