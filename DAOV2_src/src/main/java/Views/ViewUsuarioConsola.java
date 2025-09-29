package Views;

import Modelo.Usuario;
import java.util.List;
import java.util.Scanner;

public class ViewUsuarioConsola implements ViewUsuario {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== MENÚ USUARIOS ===");
        System.out.println("1. Registrar nuevo usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Buscar usuario por ID");
        System.out.println("4. Buscar usuario por email");
        System.out.println("5. Buscar usuarios por nombre");
        System.out.println("6. Actualizar usuario");
        System.out.println("7. Eliminar usuario");
        System.out.println("8. Desactivar usuario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    @Override
    public void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }

    @Override
    public void mostrarUsuario(Usuario usuario) {
        if (usuario != null) {
            System.out.println(usuario);
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    @Override
    public void mostrarError(String mensaje) {
        System.out.println("❌ " + mensaje);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println("✅ " + mensaje);
    }

    @Override
    public void mostrarEstadisticas(long total, long recientes, double porcentaje) {
        System.out.printf("Usuarios activos: %d, recientes: %d, %.2f%% recientes%n",
                total, recientes, porcentaje);
    }

    @Override
    public int leerOpcion() {
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public Usuario leerDatosUsuario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return Usuario.crearNuevo(nombre, email, password);
    }

    @Override
    public String leerEmail() {
        System.out.print("Ingrese email: ");
        return scanner.nextLine();
    }

    @Override
    public Long leerId() {
        System.out.print("Ingrese ID: ");
        return Long.parseLong(scanner.nextLine());
    }

    @Override
    public String leerNombre() {
        System.out.print("Ingrese nombre: ");
        return scanner.nextLine();
    }
}
