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
        CajeroModel model = new CajeroModel();
        CajeroViews view = new CajeroViews();
        CajeroController controller = new CajeroController(model, view);

        // Inicia la ejecución del cajero automático
        controller.iniciarCajero();
    }
}
