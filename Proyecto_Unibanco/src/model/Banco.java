package model;

import controllers.ReciboTransaccionController;
import exceptions.ClienteException;
import exceptions.CuentaException;
import exceptions.TransaccionException;

import java.util.ArrayList;
import java.util.Objects;

public class Banco {
    private String nombre;
    private ArrayList<Transaccion> listaTransacciones;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Cuenta> listaCuenta;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.listaTransacciones = new ArrayList<Transaccion>();
        this.listaClientes = new ArrayList<Cliente>();
        this.listaCuenta = new ArrayList<Cuenta>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public void setListaTransacciones(ArrayList<Transaccion> listaTransacciones) {
        this.listaTransacciones = listaTransacciones;
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Cuenta> getListaCuenta() {
        return listaCuenta;
    }

    public void setListaCuenta(ArrayList<Cuenta> listaCuenta) {
        this.listaCuenta = listaCuenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(nombre, banco.nombre) && Objects.equals(listaTransacciones, banco.listaTransacciones) && Objects.equals(listaClientes, banco.listaClientes) && Objects.equals(listaCuenta, banco.listaCuenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, listaTransacciones, listaClientes, listaCuenta);
    }


    //CRUD DE CLIENTE
    /**
     * Método para crear un cliente
     * @param nombre
     * @param apellido
     * @param cedula
     * @param direccion
     * @param email
     * @return
     * @throws ClienteException
     */
    public Cliente crearCliente(String nombre, String apellido, String cedula, String direccion, String email, Cuenta cuenta) throws ClienteException {
        Cliente cliente = new Cliente();
        ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
        cliente.setListaTransacciones(listaTransacciones);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setCedula(cedula);
        cliente.setDireccion(direccion);
        cliente.setEmail(email);
        cliente.setCuentaAsociada(cuenta);
        if(existeCliente(cedula)== true){
            throw new ClienteException("El cliente ingresado ya existe");
        }
        listaClientes.add(cliente);
        return cliente;
    }

    /**
     * Método para verificar si un cliente existe
     * @param cedula
     * @return
     */
    private boolean existeCliente(String cedula) {
        for (Cliente cliente : listaClientes){
            if (cliente.getCedula().equals(cedula)){
                    return true;
            }
        }

        return false;
    }


    /**
     * Método que obtiene un cliente por su cédula
     * @param cedula
     * @return
     * @throws ClienteException
     */
    public Cliente obtenerCliente(String cedula) throws ClienteException {
        Cliente client =null;
        for (Cliente cliente : listaClientes){
            if (cliente.getCedula().equals(cedula)){
                client = cliente;
                return client;
            }
        }
        if (client == null){
            throw new ClienteException("El cliente buscado no se encuentra creado");
        }
        return client;
    }

    /**
     * Método para actualizar los datos de un cliente
     * @param nombre
     * @param apellido
     * @param cedula
     * @param direccion
     * @param email
     * @return
     */
    public boolean actualizarCliente(String nombre, String apellido, String cedula, String direccion, String email){
        if(existeCliente(cedula)){
            for (Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula)){
                    cliente.setNombre(nombre);
                    cliente.setEmail(email);
                    cliente.setApellido(apellido);
                    cliente.setDireccion(direccion);
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Método para eliminar un cliente
     * @param cedula
     * @return
     */
    public boolean eliminarCliente(String cedula){
        if (existeCliente(cedula)) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getCedula().equals(cedula)){
                    listaClientes.remove(cliente);
                    return true;
                }

            }
        }
        return false;
    }

    //CRUD DE CUENTA

    /**
     * Este método crea un cuenta y se la asigna al cliente
     * @param cedula
     * @return
     */
    public Cuenta crearCuenta(String cedula, TipoCuenta tipo) throws CuentaException {
        Cuenta cuenta = new Cuenta();
        int numero = generarNumeroCuenta(cedula);
        cuenta.setNumeroCuenta(numero);
        cuenta.setTipoCuenta(tipo);
        cuenta.setSaldo(0);
        return cuenta;
        /**
         *  if(cuentaExiste(numero, cedula)){
         *             return cuenta;
         *         }else {
         *             throw new CuentaException("La cuenta ya existe");
         *         }
         */

    }

    /**
     * Este método genera un nùmero único aleatorio que se la asigna
     * a una cuenta
     * @param cedula
     * @return
     */
    private int generarNumeroCuenta(String cedula) {
        int numero = (int)(Math.random()*100000+1);
        while (cuentaExiste(numero, cedula)){
            numero++;
        }
        return numero;
    }

    /**
     * Este método verifica si una cuenta ya existe
     * @param numero
     * @param cedula
     * @return
     */
    private boolean cuentaExiste(int numero, String cedula) {
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula) && cliente.getCuentaAsociada().getNumeroCuenta()==numero){
                    return true;

                }
            }
        }
        return false;
    }

    /**
     * Este método busca una cuenta existente por su número
     * @param numero
     * @param cedula
     * @return
     * @throws CuentaException
     */
    public Cuenta obtenerCuenta(int numero, String cedula) throws CuentaException {
        Cuenta cuenta =null;
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula) && cliente.getCuentaAsociada().getNumeroCuenta()==numero){
                    cuenta = cliente.getCuentaAsociada();
                    return cuenta;

                }
            }
        }else{
            throw new CuentaException("La cuenta no existe");
        }
        return null;
    }


    /**
     * Este método elimina la cuenta de un cliente
     * @param cedula
     * @param numero
     * @return
     */
    public boolean eliminarCuenta(String cedula, int numero){
        if (existeCliente(cedula)) {
            for (Cliente cliente : listaClientes) {
                if (cliente.getCedula().equals(cedula) && cliente.getCuentaAsociada().getNumeroCuenta()==numero){
                    cliente.setCuentaAsociada(null);
                    return true;
                }

            }
        }
        return false;

    }

    //CRUD TRANSACCION

    /**
     * Este metodo crea una transaccion
     * @param cedula
     * @param monto
     * @param estado
     * @param tipo
     * @return
     * @throws TransaccionException
     */
    public Transaccion crearTransaccion(String cedula, double monto, EstadoTransaccion estado, TipoTransaccion tipo) throws TransaccionException {
        Transaccion transaccion = new Transaccion();
        transaccion.setTipoTransaccion(tipo);
        transaccion.setEstado(estado);
        transaccion.setValor(monto);
        int referencia= generarReferencia(cedula);
        transaccion.setReferencia(referencia);
        if (transaccion!= null){
           return transaccion;

        }else {
            throw new TransaccionException("La transaccion no se pudo realizar");
        }
    }

    private int generarReferencia(String cedula) {
        int numero = (int)(Math.random()*100+1);
        while (transaccionExiste(numero, cedula)){
            numero++;
        }
        return numero;

    }

    /**
     * Este metodo verifica si una transaccion ya existe
     * @param numero
     * @param cedula
     * @return
     */
    private boolean transaccionExiste(int numero, String cedula) {
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula) && cliente.verificarTransaccion(numero)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * este método obtiene una transacción por su número de referencia
     * @param referencia
     * @param cedula
     * @return
     */
    public Transaccion obtenerTransacicon(int referencia, String cedula){
        Transaccion transaccion = null;
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula) && cliente.verificarTransaccion(referencia)){
                    transaccion = cliente.obtenerTransaccion(referencia);
                    return transaccion;
                }
            }
        }
        return null;
    }

    /**
     * Este método elimina una transacción
     * @param referencia
     * @param cedula
     * @return
     */
    public boolean eliminarTransccion(int referencia, String cedula){
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula) && cliente.verificarTransaccion(referencia)){
                    cliente.eliminarTransaccion(referencia);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Este método permite retirar dinero de la cuenta
     * @param cedula
     * @param monto
     * @return
     */
    public Transaccion retirarDinero(int nCuenta, String cedula, double monto) throws TransaccionException {
        Transaccion transaccion = null;
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula)){
                    cliente.retirarDinero(nCuenta, monto);
                    transaccion = crearTransaccion(cedula, monto,EstadoTransaccion.EXITOSA, TipoTransaccion.RETIRO);
                    cliente.getListaTransacciones().add(transaccion);
                    return transaccion;

                }
            }
        }


        return transaccion = crearTransaccion(cedula, monto,EstadoTransaccion.RECHAZADA, TipoTransaccion.RETIRO);
    }


    /**
     * Este método permite depositar dinero en una cuenta
     * @param cedula
     * @param monto
     * @return
     */
    public Transaccion depositarDinero(int nCuenta, String cedula, double monto) throws TransaccionException {
        Transaccion transaccion = null;
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula)){
                   cliente.depositarDinero(nCuenta, monto);
                    transaccion = crearTransaccion(cedula, monto,EstadoTransaccion.EXITOSA, TipoTransaccion.DEPOSITO);
                    cliente.getListaTransacciones().add(transaccion);
                    return transaccion;

                }
            }
        }

        return transaccion = crearTransaccion(cedula, monto,EstadoTransaccion.RECHAZADA, TipoTransaccion.DEPOSITO);

    }


    /**
     * Este método permite consultar el saldo de la cuenta
     * @param cedula
     * @return
     */
    public Transaccion solicitarSaldo( int cuenta, String cedula) throws TransaccionException {
        Transaccion transaccion = null;
        double saldo = 0;
        if(existeCliente(cedula)){
            for(Cliente cliente : listaClientes){
                if(cliente.getCedula().equals(cedula)){
                   saldo =  cliente.consultarSaldo();
                    transaccion = crearTransaccion(cedula, 0 ,EstadoTransaccion.EXITOSA, TipoTransaccion.SALDO);
                    return transaccion;
                }
            }
        }

        return transaccion = crearTransaccion(cedula, 0 ,EstadoTransaccion.RECHAZADA, TipoTransaccion.SALDO);

    }





}




