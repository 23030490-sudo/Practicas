package views;

import java.util.Scanner;

/**
 * Clase {@code CajeroViews} que gestiona la interacción con el usuario.
 * Permite mostrar menús, solicitar datos de entrada y mostrar mensajes
 * relacionados con las operaciones del cajero automático.
 * 
 * Forma parte de la capa de Vista (MVC).
 * 
 * @author TuNombre
 */
public class CajeroViews {

    /** Objeto Scanner para la entrada de datos del usuario */
    private Scanner scanner;

    /**
     * Constructor de la clase que inicializa el {@code Scanner}.
     */
    public CajeroViews() {
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra un mensaje de bienvenida al usuario.
     */
    public void mostrarBienvenida() {
        System.out.println("=============================");
        System.out.println("¡Bienvenido Usuario!, AL BANCO MEXA");
        System.out.println("=============================");
    }

    /**
     * Solicita al usuario que ingrese su número de cuenta.
     * 
     * @return Número de cuenta ingresado como cadena.
     */
    public String solicitarNumeroCuenta() {
        System.out.println("Ingresa tu número de cuenta: ");
        return scanner.nextLine();
    }

    /**
     * Solicita al usuario que ingrese su PIN.
     * 
     * @return PIN ingresado como cadena.
     */
    public String solicitarPin() {
        System.out.println("Ingresa tu PIN: ");
        return scanner.nextLine();
    }

    /**
     * Muestra el menú principal con las operaciones disponibles.
     * 
     * @param titular Nombre del titular de la cuenta.
     */
    public void mostrarMenuPrincipal(String titular) {
        System.out.println("=============================");
        System.out.println("¡Bienvenid@ " + titular + "!");
        System.out.println("=============================");
        System.out.println("1.- Consultar saldo");
        System.out.println("2.- Retirar");
        System.out.println("3.- Depositar");
        System.out.println("4.- Transferir");
        // TAREA: Definir las opciones faltantes
        System.out.println("5.- Salir");
    }

    /**
     * Lee la opción del menú seleccionada por el usuario.
     * 
     * @return Entero con la opción seleccionada, o -1 si hay error en la entrada.
     */
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Muestra el saldo actual de la cuenta del usuario.
     * 
     * @param saldo Saldo de la cuenta.
     */
    public void mostrarSaldo(double saldo) {
        System.out.println("=============================");
        System.out.println("Tu saldo actual es: " + saldo);
        System.out.println("=============================");
    }

    /**
     * Solicita al usuario que ingrese una cantidad para una operación
     * (ejemplo: retirar o depositar).
     * 
     * @param operacion Nombre de la operación (ejemplo: "retirar", "depositar").
     * @return Cantidad ingresada como {@code double}, o -1 si hubo error.
     */
    public double solicitarCantida(String operacion) {
        System.out.println("Ingresa la cantidad a " + operacion + ": ");
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Muestra un mensaje genérico al usuario.
     * 
     * @param mensaje Mensaje a mostrar.
     */
    public void mostrarMensajes(String mensaje) {
        System.out.println("===== " + mensaje);
    }

    /**
     * Muestra un mensaje de error personalizado.
     * 
     * @param mensaje Mensaje de error.
     */
    public void mostrarError(String mensaje) {
        System.out.println("❌ ERROR: " + mensaje);
    }

    /**
     * Muestra un mensaje de éxito personalizado.
     * 
     * @param mensaje Mensaje de éxito.
     */
    public void mostrarExito(String mensaje) {
        System.out.println("✅ ÉXITO: " + mensaje);
    }

    /**
     * Cierra el {@code Scanner} y muestra un mensaje de despedida.
     */
    public void cerrar() {
        System.out.println("=============================");
        System.out.println("Gracias por usar BANCO MEXA. ¡Hasta pronto!");
        System.out.println("=============================");
        if (scanner != null) {
            scanner.close();
        }
    }
}
