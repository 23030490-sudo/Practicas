package main;

import Views.UsuarioController;
import Views.ViewUsuarioConsola;

public class main {
    public static void main(String[] args) {
        try {
            UsuarioController controller = new UsuarioController(new ViewUsuarioConsola());
            controller.iniciar();
        } catch (Exception e) {
            System.err.println("❌ Error iniciando la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
