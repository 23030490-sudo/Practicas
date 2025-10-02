package strategies;

import controllers.CajeroController;
import views.CajeroViews;

public class SalirStrategy implements OperacionStrategy {
    private CajeroController controller;
    private CajeroViews view;

    public SalirStrategy(CajeroController controller, CajeroViews view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void ejecutar() {
        view.cerrar();
        controller.desactivarSistema();
    }
}
