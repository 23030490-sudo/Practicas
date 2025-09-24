package strategy;

import models.CajeroModel;
import views.CajeroViews;

/**
 * Estrategia genérica para operaciones del cajero automático.
 */
public interface OperacionStrategy {
    void ejecutar(CajeroModel model, CajeroViews view);
}
