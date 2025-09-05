package controllers;

import models.CajeroModel;
import views.CajeroViews;

/**
 * Controlador principal del sistema de cajero automático.
 * Se encarga de gestionar la interacción entre el modelo y la vista.
 * Implementa la lógica del flujo del cajero (autenticación, operaciones, menú).
 * 
 * @author 
 */
public class CajeroController {
    private CajeroModel model;
    private CajeroViews view;
    private boolean sistemaActivo;

    /**
     * Constructor de la clase CajeroController.
     *
     * @param model Modelo que gestiona la lógica de negocio (cuentas, saldos).
     * @param view Vista encargada de mostrar mensajes y solicitar datos.
     */
    public CajeroController(CajeroModel model, CajeroViews view) {
        this.model = model;
        this.view = view;
        this.sistemaActivo = true;
    }

    /**
     * Método principal que inicia el flujo del cajero.
     * Se encarga de mostrar bienvenida, autenticar y desplegar el menú principal.
     */
    public void iniciarCajero() {
        view.mostrarBienvenida();
        while (sistemaActivo) {
            if (autenticarUsuario()) {
                ejecutarMenuPrincipal();
            } else {
                view.mostrarMensajes("Credenciales incorrectas");
            }
        }
        view.mostrarMensajes("Gracias por usar nuestro cajero");
    }

    /**
     * Solicita credenciales al usuario y valida la autenticidad.
     *
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    private boolean autenticarUsuario() {
        String numeroCuenta = view.solicitarNumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    /**
     * Muestra el menú principal y ejecuta la opción seleccionada por el usuario.
     */
    private void ejecutarMenuPrincipal() {
        boolean sessionActiva = true;
        while (sessionActiva) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    retirarSaldo();
                    break;
                case 3:
                    realizarDeposito();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    salir();
                    sessionActiva = false; // Se sale de la sesión actual
                    break;
                default:
                    view.mostrarMensajes("Opción no válida");
            }
        }
    }

    /**
     * Consulta y muestra el saldo de la cuenta actual.
     */
    public void consultarSaldo() {
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }

    /**
     * Solicita una cantidad y realiza un depósito en la cuenta actual.
     */
    public void realizarDeposito() {
        double cantidad = view.solicitarCantida("Depósito");
        if (cantidad <= 0) {
            view.mostrarMensajes("Error en la cantidad");
            return;
        }
        if (model.realizarDeposito(cantidad)) {
            view.mostrarMensajes("Depósito exitoso por la cantidad de: " + cantidad);
        } else {
            view.mostrarMensajes("Error al procesar el depósito");
        }
    }

    /**
     * Solicita una cantidad y realiza un retiro de la cuenta actual.
     */
    public void retirarSaldo() {
        double cantidad = view.solicitarCantida("Retiro");
        if (cantidad <= 0) {
            view.mostrarMensajes("Error en la cantidad");
            return;
        }
        if (model.realizarRetiro(cantidad)) {
            view.mostrarMensajes("Retiro exitoso de " + cantidad);
        } else {
            view.mostrarMensajes("Fondos insuficientes");
        }
    }

    /**
     * Solicita datos y realiza una transferencia a otra cuenta.
     */
    public void transferir() {
        double cantidad = view.solicitarCantida("Transferencia");
        if (cantidad <= 0) {
            view.mostrarMensajes("Error en la cantidad");
            return;
        }
        if (model.transferir(view.solicitarNumeroCuenta(), cantidad)) {
            view.mostrarMensajes("Transferencia exitosa de " + cantidad);
        } else {
            view.mostrarMensajes("Fondos insuficientes");
        }
    }

    /**
     * Finaliza la sesión del cajero.
     */
    public void salir() {
        view.cerrar();
        sistemaActivo = false;
    }
}
