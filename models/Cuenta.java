package models;

/**
 * Representa una cuenta bancaria dentro del sistema de cajero automático.
 * Contiene atributos básicos de la cuenta, como número, PIN, saldo y titular.
 * Además, permite realizar operaciones de autenticación y transacciones
 * (retiros, depósitos, transferencias y cambio de PIN).
 * 
 * Forma parte del modelo en el patrón MVC.
 * 
 * @author 
 */
public class Cuenta {
    // ===== Atributos =====
    private String numeroCuenta;
    private String pin;
    private double saldo;
    private String titular;

    /**
     * Constructor de la clase Cuenta.
     *
     * @param numeroCuenta número único de la cuenta
     * @param pin          clave de acceso de la cuenta
     * @param saldo        saldo inicial de la cuenta
     * @param titular      nombre del titular de la cuenta
     */
    public Cuenta(String numeroCuenta, String pin, double saldo, String titular) {
        this.numeroCuenta = numeroCuenta;
        this.pin = pin;
        this.saldo = saldo;
        this.titular = titular;
    }

    // ===== Getters =====

    /**
     * Obtiene el número de cuenta.
     *
     * @return número de cuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * Obtiene el PIN de la cuenta.
     * ⚠️ Solo debería usarse con fines internos, nunca mostrarse directamente.
     *
     * @return PIN actual
     */
    public String getPin() {
        return pin;
    }

    /**
     * Obtiene el saldo de la cuenta.
     *
     * @return saldo disponible
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Obtiene el nombre del titular de la cuenta.
     *
     * @return nombre del titular
     */
    public String getTitular() {
        return titular;
    }

    // ===== Métodos principales =====

    /**
     * Valida si el PIN ingresado coincide con el de la cuenta.
     *
     * @param pinIngresado PIN ingresado por el usuario
     * @return true si el PIN es correcto, false en caso contrario
     */
    public boolean validarPin(String pinIngresado) {
        return this.pin.equals(pinIngresado);
    }

    /**
     * Retira una cantidad de dinero de la cuenta.
     *
     * @param cantidad monto a retirar
     * @return true si la operación fue exitosa, false si no hay fondos o el monto es inválido
     */
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= this.saldo) {
            saldo -= cantidad;
            return true;
        }
        return false;
    }

    /**
     * Deposita una cantidad de dinero en la cuenta.
     *
     * @param cantidad monto a depositar
     */
    public void depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
        }
    }

    /**
     * Consulta el saldo de la cuenta.
     *
     * @return saldo actual
     */
    public double consultarSaldo() {
        return this.saldo;
    }

    /**
     * Cambia el PIN de la cuenta si el actual es correcto.
     *
     * @param pinActual PIN actual
     * @param nuevoPin  nuevo PIN a establecer
     * @return true si el cambio fue exitoso, false si el PIN actual no coincide o el nuevo es inválido
     */
    public boolean cambiarPin(String pinActual, String nuevoPin) {
        if (this.pin.equals(pinActual) && nuevoPin != null && !nuevoPin.isEmpty()) {
            this.pin = nuevoPin;
            return true;
        }
        return false;
    }

    /**
     * Realiza una transferencia hacia otra cuenta.
     * Internamente usa {@link #retirar(double)} y {@link #depositar(double)}.
     *
     * @param destino  cuenta destino
     * @param cantidad monto a transferir
     * @return true si la transferencia fue exitosa, false en caso contrario
     */
    public boolean transferir(Cuenta destino, double cantidad) {
        if (this.retirar(cantidad)) {
            destino.depositar(cantidad);
            return true;
        }
        return false;
    }
}
