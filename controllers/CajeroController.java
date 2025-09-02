package controllers;

import models.CajeroModel;
import views.CajeroViews;

public class CajeroController {
    private CajeroModel model;
    private CajeroViews view;
    private boolean sistemaActivo;
    
    public CajeroController(CajeroModel model, CajeroViews view){
        this.model=model;
        this.view=view;
        this.sistemaActivo=true;
    }
    
    public void inicarCajero(){
        view.mostrarBienvenida();
        while(sistemaActivo){
            if(autenticarUsuario()){
                ejecutarMenuPrincipal();
            }
            else{
                view.mostrarMensajes("Credenciales incorrectas");
            }
        }
        view.mostrarMensajes("Gracias por usar nuestro cajero");
    }
    
    private boolean autenticarUsuario(){
        String numeroCuenta = view.solicitarNumeroCuenta();
        String pin = view.solicitarPin();
        return model.autenticar(numeroCuenta, pin);
    }

    private void ejecutarMenuPrincipal() {
        boolean sessionActiva = true;
        while(sessionActiva==true){
            view.mostrarMenuPrincipal(model.getCuentaActual().getTitular());
            int opcion = view.leerOpcion();
            switch (opcion){
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarRetiro();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }
        }
    }
    
    public void consultarSaldo(){
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }
    public void retirarSaldo(){
        double cantidad = view.solicitarCantida("Retirar");
        if(cantidad <=0){
            view.mostrarMensajes("Error en la cantidad");
            return;
        }
        if(model.realizarRetiro(cantidad)){
            view.mostrarMensajes("Retiro Éxitoso de "+cantidad);
        } else{
            view.mostrarMensajes("Fondos insuficientes");
        }
    }
    
    public void realizarRetiro(){
        double cantidad = view.solicitarCantida("Deposito");
        if (cantidad<=0){
            view.mostrarMensajes("Error en la cantidad");
            return;
        } if(model.realizarDeposito(cantidad)){
            view.mostrarMensajes("Déposito éxisitoso por la cantidad de: "+cantidad);
        } else{
            view.mostrarMensajes("Error al procesar el déposito");
        }
    }
    
    
}
