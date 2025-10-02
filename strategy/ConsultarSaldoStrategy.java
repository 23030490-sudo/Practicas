package strategy;

import models.CajeroModel;
import views.CajeroViews;

public class ConsultarSaldoStrategy implements OperacionStrategy {
    @Override
    public void ejecutar(CajeroModel model, CajeroViews view) {
        double saldo = model.consultarSaldo();
        view.mostrarSaldo(saldo);
    }
}
