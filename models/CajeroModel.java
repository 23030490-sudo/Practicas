package models;

import java.util.HashMap;
import java.util.Map;

/**
 * Modelo del sistema de cajero automático.
 * Administra las cuentas de los usuarios y las operaciones
 * de saldo, depósitos, retiros y transferencias.
 * 
 * Este modelo sigue el patrón MVC, y se conecta con:
 * - {@link controllers.CajeroController} para la lógica de negocio
 * - {@link views.CajeroViews} para la interacción con el usuario
 * 
 * @author 
 */
public class CajeroModel {
    private Map<String, Cuenta> cuentas;
    private Cuenta cuentaActual;

    /**
     * Constructor del modelo.
     * Inicializa la estructura de cuentas con datos de ejemplo.
     */
    public CajeroModel() {
        cuentas = new HashMap<>();
        inicializarCuentas();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.put(cuenta.getNumeroCuenta(), cuenta);
    }

    /**
     * Inicializa las cuentas con información predefinida.
     * Método de apoyo para el constructor.
     */
    private void inicializarCuentas() {
        cuentas.put("12345", new Cuenta("12345", "1111", 5000, "Juan Perez"));
        cuentas.put("9087", new Cuenta("9087", "123", 3000, "Jesus Samudio"));
        cuentas.put("3333", new Cuenta("3333", "3333", 6000, "Mariana Vega"));
        cuentas.put("7689", new Cuenta("7689", "4567", 10000, "Sandra Hernández"));
    }

    /**
     * Autentica a un usuario a partir de número de cuenta y PIN.
     *
     * @param numeroCuenta Número de cuenta ingresado.
     * @param pin          PIN ingresado.
     * @return true si la cuenta existe y el PIN es correcto, false en caso contrario.
     */
    public boolean autenticar(String numeroCuenta, String pin) {
        Cuenta cuenta = cuentas.get(numeroCuenta);
        if (cuenta != null && cuenta.validarPin(pin)) {
            this.cuentaActual = cuenta;
            return true;
        }
        return false;
    }

    /**
     * Obtiene la cuenta actual autenticada.
     *
     * @return la cuenta actual, o null si no hay ninguna autenticada.
     */
    public Cuenta getCuentaActual() {
        return this.cuentaActual;
    }

    /**
     * Consulta el saldo de la cuenta actual.
     *
     * @return saldo actual si hay una cuenta autenticada, 0 en caso contrario.
     */
    public double consultarSaldo() {
        return this.cuentaActual != null ? cuentaActual.getSaldo() : 0;
    }

    /**
     * Realiza un retiro desde la cuenta actual.
     *
     * @param cantidad monto a retirar.
     * @return true si el retiro fue exitoso, false en caso contrario.
     */
    public boolean realizarRetiro(double cantidad) {
        return cuentaActual != null && cuentaActual.retirar(cantidad);
    }

    /**
     * Realiza un depósito en la cuenta actual.
     *
     * @param cantidad monto a depositar.
     * @return true si el depósito fue exitoso, false en caso contrario.
     */
    public boolean realizarDeposito(double cantidad) {
        if (cuentaActual != null && cantidad > 0) {
            cuentaActual.depositar(cantidad);
            return true;
        }
        return false;
    }

    /**
     * Verifica si una cuenta existe en el sistema.
     *
     * @param numeroCuenta número de cuenta a buscar.
     * @return true si la cuenta existe, false en caso contrario.
     */
    public boolean cuentaExistente(String numeroCuenta) {
        return cuentas.containsKey(numeroCuenta);
    }

    /**
     * Realiza una transferencia desde la cuenta actual a otra cuenta.
     *
     * @param numeroCuentaDestino número de cuenta destino.
     * @param cantidad            monto a transferir.
     * @return true si la transferencia fue exitosa, false en caso contrario.
     */
    public boolean transferir(String numeroCuentaDestino, double cantidad) {
        if (cuentaActual != null && cuentas.containsKey(numeroCuentaDestino) && cantidad > 0) {
            Cuenta destino = cuentas.get(numeroCuentaDestino);
            if (cuentaActual.retirar(cantidad)) {
                destino.depositar(cantidad);
                return true;
            }
        }
        return false;
    }
}
