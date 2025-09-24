package strategies;

import models.CajeroModel;
import views.CajeroViews;

public class TransferirStrategy implements OperacionStrategy {
    private CajeroModel model;
    private CajeroViews view;

    public TransferirStrategy(CajeroModel model, CajeroViews view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void ejecutar() {
        double cantidad = view.solicitarCantida("Transferencia");
        if (cantidad <= 0) {
            view.mostrarMensajes("Error en la cantidad");
            return;
        }
        if (model.transferir(view.solicitarNumeroCuenta(), cantidad)) {
            view.mostrarMensajes("Transferencia exitosa de " + cantidad);
        } else {
            view.mostrarMensajes("Fondos insuficientes");
        }
    }
}
