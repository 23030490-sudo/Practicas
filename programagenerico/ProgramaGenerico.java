package programagenerico;

import java.util.List;

public class ProgramaGenerico {
    // Método genérico
    public static <T> void imprimirLista(List<T> lista) {
        for (T elem : lista) {
            System.out.println(elem);
        }
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        // Lista de Strings
        ListaGenerica<String> listaNombres = new ListaGenerica<>();
        listaNombres.agregar("Ana");
        listaNombres.agregar("Luis");
        listaNombres.agregar("María");

        // Lista de enteros
        ListaGenerica<Integer> listaNumeros = new ListaGenerica<>();
        listaNumeros.agregar(10);
        listaNumeros.agregar(20);
        listaNumeros.agregar(30);

        // Usamos el método genérico
        System.out.println("Lista de nombres:");
        imprimirLista(listaNombres.getElementos());

        System.out.println("Lista de números:");
        imprimirLista(listaNumeros.getElementos());
    }
}