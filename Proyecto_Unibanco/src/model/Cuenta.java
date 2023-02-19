package model;

import java.util.Objects;

public class Cuenta {

    private int numeroCuenta;
    private double saldo;
    private TipoCuenta tipoCuenta;
    private Cliente clienteAsosciados;

    public Cuenta(int numeroCuenta, double saldo, TipoCuenta tipoCuenta, Cliente clienteAsosciados) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.clienteAsosciados = clienteAsosciados;
    }

    public Cuenta() {
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Cliente getClienteAsosciados() {
        return clienteAsosciados;
    }

    public void setClienteAsosciados(Cliente clienteAsosciados) {
        this.clienteAsosciados = clienteAsosciados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return Double.compare(cuenta.saldo, saldo) == 0 && Objects.equals(numeroCuenta, cuenta.numeroCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCuenta, saldo);
    }

}
