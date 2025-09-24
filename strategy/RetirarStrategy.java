package strategy;

import models.CajeroModel;
import views.CajeroViews;

public class RetirarStrategy implements OperacionStrategy {
    @Override
    public void ejecutar(CajeroModel model, CajeroViews view) {
        double cantidad = view.solicitarCantida("retirar");
        if (model.realizarRetiro(cantidad)) {
            view.mostrarExito("Retiro exitoso de $" + cantidad);
        } else {
            view.mostrarError("No fue posible realizar el retiro.");
        }
    }
}
