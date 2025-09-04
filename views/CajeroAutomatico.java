package views;

import controllers.CajeroController;
import models.CajeroModel;
import controllers.CajeroController;

public class CajeroAutomatico {
    public static void main(String[] args) {
        CajeroModel model = new CajeroModel();
        CajeroViews view = new CajeroViews();
        CajeroController controller = new CajeroController(model,view);
        controller.inicarCajero();
        
    }
}
