package models;
import java.util.HashMap;
import java.util.Map;
public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;
    //Constructor
    public CajeroModel(){
        cuentas = new HashMap<>();
        inicializarCuentas();
    }
    //Método inicializar cuenta
    private void inicializarCuentas(){
        cuentas.put("12345", new Cuenta("12345","1111",5000,"Juan Perez"));
        cuentas.put("9087", new Cuenta("9087","123",3000,"Jesus Samudio"));
        cuentas.put("3333",new Cuenta("3333","3333",6000,"Mariana Vega"));
        cuentas.put("7689", new Cuenta("7689","4567",10000,"Sandra Hérnandez"));
    }
    //Método autenticar
    public boolean autenticar(String numeroCuenta, String pin){
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if(cuenta!=null && cuenta.validarPin(pin)){
            this.cuentaActual=cuenta;
            return true;
        }
        return false;
    }
    //Get regresa el objeto de cuenta
    public Cuenta getCuentaActual(){
        return this.cuentaActual;
    }
    //Método consultar saldo
    public double consultarSaldo(){
        return this.cuentaActual !=null ? cuentaActual.getSaldo():0;
    }
    //Método realizar retiro
    public boolean realizarRetiro(double cantidad){
        return cuentaActual!=null && cuentaActual.retirar(cantidad);
    }
    //Método realizar déposito
    public boolean realizarDeposito(double cantidad){
        if(cuentaActual!= null && cantidad>0){
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }
    //Método validar existencia de cuentas
    public boolean cuentaExistente(String numeroCuenta){
        return cuentas.containsKey(numeroCuenta);
    }
    //Tarea: definir el método para transferir
    
}
