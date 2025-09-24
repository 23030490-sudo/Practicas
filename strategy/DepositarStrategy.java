package strategy;

import models.CajeroModel;
import views.CajeroViews;

public class DepositarStrategy implements OperacionStrategy {
    @Override
    public void ejecutar(CajeroModel model, CajeroViews view) {
        double cantidad = view.solicitarCantida("depositar");
        if (model.realizarDeposito(cantidad)) {
            view.mostrarExito("Depósito exitoso de $" + cantidad);
        } else {
            view.mostrarError("No fue posible realizar el depósito.");
        }
    }
}
