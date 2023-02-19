package model;

import java.util.Objects;

public class Transaccion {

    private double valor;

    private int referencia;
    private String hora;
    private String fecha;
    private EstadoTransaccion estado;
    private TipoTransaccion tipoTransaccion;
    private  Cliente clienteAsociado;

    public Transaccion(double valor, String hora, String fecha, EstadoTransaccion estado, TipoTransaccion tipoTransaccion, Cliente clienteAsociado, int referencia) {
        this.valor = valor;
        this.hora = hora;
        this.fecha = fecha;
        this.estado = estado;
        this.tipoTransaccion = tipoTransaccion;
        this.clienteAsociado = clienteAsociado;
        this.referencia = referencia;
    }

    public Transaccion() {

    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public EstadoTransaccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransaccion estado) {
        this.estado = estado;
    }

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Cliente getClienteAsociado() {
        return clienteAsociado;
    }

    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaccion that = (Transaccion) o;
        return Double.compare(that.valor, valor) == 0 && Objects.equals(hora, that.hora) && Objects.equals(fecha, that.fecha) && Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, hora, fecha, estado);
    }
}
