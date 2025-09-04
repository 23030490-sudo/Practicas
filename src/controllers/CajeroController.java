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
                    retirarSaldo();
                    break;
                case 3:
                    //Depositar
                    realizarDeposito();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    salir();
                    break;
            }
        }
    }
    
    public void consultarSaldo(){
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }
    
    public void realizarDeposito(){
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
        double cantidad = view.solicitarCantida("Retiro");
        if (cantidad<=0){
            view.mostrarMensajes("Error en la cantidad");
            return;
        } if(model.realizarDeposito(cantidad)){
            view.mostrarMensajes("Déposito éxisitoso por la cantidad de: "+cantidad);
        } else{
            view.mostrarMensajes("Error al procesar el déposito");
        }
    }
    
    public void transferir(){
        double cantidad = view.solicitarCantida("Transferencia");
        if(cantidad <=0){
            view.mostrarMensajes("Error en la cantidad");
            return;
        }
        if(model.transferir(view.solicitarNumeroCuenta(),cantidad)){
            view.mostrarMensajes("Transferencia Éxitosa de "+cantidad);
        } else{
            view.mostrarMensajes("Fondos insuficientes");
        }
    }
    
    public <T> void salir(){
        view.cerrar();
    }
    
    
    
    
    
}
