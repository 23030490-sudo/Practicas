package Views;

import Modelo.Usuario;
import java.util.List;

public interface ViewUsuario {
    void mostrarMenu();
    void mostrarUsuarios(List<Usuario> usuarios);
    void mostrarUsuario(Usuario usuario);
    void mostrarError(String mensaje);
    void mostrarMensaje(String mensaje);
    void mostrarEstadisticas(long total, long recientes, double porcentaje);
    int leerOpcion();
    Usuario leerDatosUsuario();
    String leerEmail();
    Long leerId();
    String leerNombre();
}
