package models;
public class Cuenta {
    //Atributos
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String titular;
    //Constructor
    public Cuenta(String numeroCuenta, String pin, double saldo, String titular){
        this.numeroCuenta=numeroCuenta;
        this.pin=pin;
        this.saldo=saldo;
        this.titular=titular;
    }
    //Getters
    public String getNumeroCuenta(){
        return numeroCuenta;
    }
    public String getPin(){
        return pin;
    }
    public double getSaldo(){
        return saldo;
    }
    public String getTitular(){
        return titular;
    }
    //Método validar PIN
    public boolean validarPin(String pinIngresado){
        //Regresa booleano si el pin ingresado es igual al pin 
        return this.pin.equals(pinIngresado);
    }
    //Método retirar
    public boolean retirar(double cantidad){
        //Si la cantidad es mayor a 0 y menor al saldo, procede a retirar
        if( cantidad>0 && cantidad<=this.saldo){
            //saldo=saldo-cantidad
            saldo-=cantidad;
            return true;
        }
        return false;
    }
    //Método depositar
    public void depositar(double cantidad){
        if(cantidad>0){
            //saldo=saldo+cantidad;
            saldo+=cantidad;
        }
    }
    //Tarea: diseñar los restantes
    //Tarea: consultar saldo
    public double consultarSaldo(){
        return this.saldo;
    }

    //Método cambiar PIN
    public boolean cambiarPin(String pinActual, String nuevoPin){
        if(this.pin.equals(pinActual) && nuevoPin != null && !nuevoPin.isEmpty()){
            this.pin = nuevoPin;
            return true;
        }
        return false;
    }

    //Método ransferir a otra cuenta
    public boolean transferir(Cuenta destino, double cantidad){
        if(this.retirar(cantidad)){ // reutiliza el método retirar
            destino.depositar(cantidad);
            return true;
        }
        return false;
    }

}
