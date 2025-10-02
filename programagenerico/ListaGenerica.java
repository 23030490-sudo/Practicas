package programagenerico;
import java.util.ArrayList;
import java.util.List;

// Clase genérica
class ListaGenerica<T> {
    private List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public T obtener(int indice) {
        return elementos.get(indice);
    }

    public List<T> getElementos() {
        return elementos;
    }
}