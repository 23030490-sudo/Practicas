package controllers;

import models.CajeroModel;
import views.CajeroViews;
import strategies.*;

/**
 * Controlador principal del sistema de cajero automático.
 * Se encarga de gestionar la interacción entre el modelo y la vista.
 * Implementa la lógica del flujo del cajero (autenticación, operaciones, menú).
 * 
 * Aplicación del patrón Strategy para las operaciones.
 * 
 */
public class CajeroController {
    private CajeroModel model;
    private CajeroViews view;
    private boolean sistemaActivo;

    // Estrategias disponibles
    private OperacionStrategy[] estrategias;

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

        // Se inicializan las estrategias en un arreglo
        this.estrategias = new OperacionStrategy[] {
            new ConsultarSaldoStrategy(model, view),
            new RetirarStrategy(model, view),
            new DepositarStrategy(model, view),
            new TransferirStrategy(model, view),
            new SalirStrategy(this, view)  // recibe el controlador porque modifica sistemaActivo
        };
    }

    /**
     * Método principal que inicia el flujo del cajero.
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
        while (sessionActiva && sistemaActivo) {
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            if (opcion >= 1 && opcion <= estrategias.length) {
                estrategias[opcion - 1].ejecutar();
                if (opcion == 5) { // opción salir
                    sessionActiva = false;
                }
            } else {
                view.mostrarMensajes("Opción no válida");
            }
        }
    }

    /**
     * Cambia el estado del sistema.
     * Usado por la estrategia "Salir".
     */
    public void desactivarSistema() {
        this.sistemaActivo = false;
    }
}
