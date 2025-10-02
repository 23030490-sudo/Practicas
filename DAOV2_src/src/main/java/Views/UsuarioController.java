package Views;

import Modelo.Usuario;
import Persistencia.DatabaseConnection;
import Persistencia.UsuarioDAO;
import Service.UsuarioService;

import java.sql.Connection;
import java.util.List;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ViewUsuario view;

    public UsuarioController(ViewUsuario view) throws Exception {
        Connection connection = DatabaseConnection.getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
        this.usuarioService = new UsuarioService(usuarioDAO);
        this.view = view;
    }

    public void iniciar() {
        while (true) {
            view.mostrarMenu();
            int opcion = view.leerOpcion();
            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> listarUsuarios();
                case 3 -> buscarPorId();
                case 4 -> buscarPorEmail();
                case 5 -> buscarPorNombre();
                case 6 -> actualizarUsuario();
                case 7 -> eliminarUsuario();
                case 8 -> desactivarUsuario();
                case 0 -> {
                    System.out.println("👋 Saliendo...");
                    return;
                }
                default -> view.mostrarError("Opción inválida");
            }
        }
    }

    private void registrarUsuario() {
        try {
            Usuario usuario = view.leerDatosUsuario();
            Usuario registrado = usuarioService.registrarUsuario(
                    usuario.getNombre(), usuario.getEmail(), usuario.getPassword());
            view.mostrarUsuario(registrado);
        } catch (Exception e) {
            view.mostrarError("Error registrando usuario: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        view.mostrarUsuarios(usuarios);
    }

    private void buscarPorId() {
        try {
            Long id = view.leerId();
            usuarioService.obtenerUsuario(id).ifPresentOrElse(
                    view::mostrarUsuario,
                    () -> view.mostrarError("Usuario no encontrado")
            );
        } catch (Exception e) {
            view.mostrarError("Error: " + e.getMessage());
        }
    }

    private void buscarPorEmail() {
        String email = view.leerEmail();
        usuarioService.buscarPorEmail(email).ifPresentOrElse(
                view::mostrarUsuario,
                () -> view.mostrarError("Usuario no encontrado")
        );
    }

    private void buscarPorNombre() {
        String nombre = view.leerNombre();
        List<Usuario> usuarios = usuarioService.buscarPorNombre(nombre);
        view.mostrarUsuarios(usuarios);
    }

    private void actualizarUsuario() {
        try {
            Long id = view.leerId();
            usuarioService.obtenerUsuario(id).ifPresentOrElse(usuario -> {
                System.out.println("Actualizando usuario: " + usuario);
                Usuario nuevosDatos = view.leerDatosUsuario();
                usuario.setNombre(nuevosDatos.getNombre());
                usuario.setPassword(nuevosDatos.getPassword());
                usuarioService.actualizarUsuario(usuario);
                view.mostrarMensaje("Usuario actualizado");
            }, () -> view.mostrarError("Usuario no encontrado"));
        } catch (Exception e) {
            view.mostrarError("Error actualizando usuario: " + e.getMessage());
        }
    }

    private void eliminarUsuario() {
        try {
            Long id = view.leerId();
            if (usuarioService.eliminarUsuario(id)) {
                view.mostrarMensaje("Usuario eliminado");
            } else {
                view.mostrarError("Usuario no encontrado");
            }
        } catch (Exception e) {
            view.mostrarError("Error eliminando usuario: " + e.getMessage());
        }
    }

    private void desactivarUsuario() {
        try {
            Long id = view.leerId();
            if (usuarioService.desactivarUsuario(id)) {
                view.mostrarMensaje("Usuario desactivado");
            } else {
                view.mostrarError("Usuario no encontrado");
            }
        } catch (Exception e) {
            view.mostrarError("Error desactivando usuario: " + e.getMessage());
        }
    }
}
