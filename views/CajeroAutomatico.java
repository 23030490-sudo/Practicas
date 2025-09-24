package views;

import controllers.CajeroController;
import models.CajeroModel;

/**
 * Clase principal {@code CajeroAutomatico}.
 * 
 * Es el punto de entrada de la aplicación y se encarga de inicializar
 * el modelo, la vista y el controlador para ejecutar el sistema de cajero.
 * 
 * Forma parte de la capa de Vista (MVC).
 * 
 * @author TuNombre
 */
public class CajeroAutomatico {

    /**
     * Método principal que arranca la aplicación del cajero automático.
     * 
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Se crean las cuentas usando el patrón Builder
        Cuenta cuenta1 = new CuentaBuilder()
                .setNumeroCuenta("12345")
                .setPin("1111")
                .setSaldo(5000)
                .setTitular("Juan Perez")
                .build();

        Cuenta cuenta2 = new CuentaBuilder()
                .setNumeroCuenta("9087")
                .setPin("123")
                .setSaldo(3000)
                .setTitular("Jesus Samudio")
                .build();

        Cuenta cuenta3 = new CuentaBuilder()
                .setNumeroCuenta("3333")
                .setPin("3333")
                .setSaldo(6000)
                .setTitular("Mariana Vega")
                .build();

        Cuenta cuenta4 = new CuentaBuilder()
                .setNumeroCuenta("7689")
                .setPin("4567")
                .setSaldo(10000)
                .setTitular("Sandra Hernández")
                .build();

        // Creamos el modelo y le agregamos las cuentas construidas
        CajeroModel model = new CajeroModel();
        model.agregarCuenta(cuenta1);
        model.agregarCuenta(cuenta2);
        model.agregarCuenta(cuenta3);
        model.agregarCuenta(cuenta4);

        CajeroViews view = new CajeroViews();
        CajeroController controller = new CajeroController(model, view);

        // Inicia la ejecución del cajero automático
        controller.iniciarCajero();
    }
}
