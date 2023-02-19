package model;

import java.util.ArrayList;
import java.util.Objects;

public class Cliente {

    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String email;
    private Cuenta cuentaAsociada;
    private ArrayList<Transaccion> listaTransacciones;

    public Cliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuentaAsociada, ArrayList<Transaccion> transaccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.email = email;
        this.cuentaAsociada = cuentaAsociada;
        this.listaTransacciones = transaccion;
    }

    public Cliente() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public Cuenta getCuentaAsociada() {
        return cuentaAsociada;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCuentaAsociada(Cuenta cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public void setListaTransacciones(ArrayList<Transaccion> transaccion) {
        this.listaTransacciones = transaccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(cedula, cliente.cedula) && Objects.equals(direccion, cliente.direccion) && Objects.equals(email, cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, cedula, direccion, email);
    }

    public boolean verificarTransaccion(int numero) {
        for(Transaccion transaccion : listaTransacciones){
            if(transaccion.getReferencia()== numero){
                return true;
            }
        }
        return false;
    }

    /**
     * este metodo obtiene una transacción por su número de referencia
     * @param referencia
     * @return
     */
    public Transaccion obtenerTransaccion(int referencia) {
        for(Transaccion transaccion : listaTransacciones){
            if(transaccion.getReferencia()== referencia){
                return transaccion;
            }
        }
        return null;
    }

    /**
     * Este método elimina una transacción por su número de cuenta
     * @param referencia
     */
    public void eliminarTransaccion(int referencia) {
        for(Transaccion transaccion : listaTransacciones){
            if(transaccion.getReferencia()== referencia){
                listaTransacciones.remove(transaccion);
            }
        }
    }

    /**
     * Este método permite retirar el dinero acorde al monto
     * @param nCuenta
     * @param monto
     * @return
     */
    public boolean retirarDinero(int nCuenta, double monto) {
        double saldo = cuentaAsociada.getSaldo();
        double nuevoSaldo= 0;
        int numeroCuenta = cuentaAsociada.getNumeroCuenta();
        if (numeroCuenta == nCuenta){
            if(saldo > 0 && saldo >= monto){
                nuevoSaldo = saldo - monto;
                cuentaAsociada.setSaldo(nuevoSaldo);
                return true;
            }
        }
        return false;
    }

    /**
     * Este método permite depositar dinero a la cuenta según el monto dado
     * @param nCuenta
     * @param monto
     * @return
     */
    public boolean depositarDinero(int nCuenta, double monto) {
        double saldo = cuentaAsociada.getSaldo();
        double nuevoSaldo= 0;
        int numeroCuenta= cuentaAsociada.getNumeroCuenta();
        if (numeroCuenta == nCuenta){
            if(saldo >= 0 ){
                nuevoSaldo = saldo + monto;
                cuentaAsociada.setSaldo(nuevoSaldo);
                return true;
            }
        }

        return false;
    }

    /**
     * Este método obtiene el saldo de la cuenta
     * @return
     */
    public double consultarSaldo() {
        return cuentaAsociada.getSaldo();
    }
}
