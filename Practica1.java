package practica1;

import java.util.Scanner;

public class Practica1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Base de datos de usuarios
        Usuario[] usuarios = {
            new Usuario("1234", "Juan", 1000.0),
            new Usuario("5678", "Maria", 2500.0)
        };

        int intentos = 0;
        Usuario usuarioActual = null;

        System.out.println("=== Bienvenido al Cajero ===");

        while (intentos < 3 && usuarioActual == null) {
            System.out.print("Ingrese su PIN: ");
            String pin = scanner.nextLine();

            for (Usuario u : usuarios) {
                if (u.getPin().equals(pin)) {
                    usuarioActual = u;
                    break;
                }
            }

            if (usuarioActual == null) {
                System.out.println("PIN incorrecto.");
                intentos++;
            }
        }

        if (usuarioActual == null) {
            System.out.println("Demasiados intentos fallidos. Adiós.");
            return;
        }

        Cajero cajero = new Cajero(usuarioActual);
        cajero.Bienvenida();

        boolean salir = false;
        while (!salir) {
            System.out.println("\n1. Ver saldo");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Depositar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    cajero.VerSaldo();
                    break;
                case 2:
                    cajero.Retirar();
                    break;
                case 3:
                    cajero.Depositar();
                    break;
                case 4:
                    cajero.Salir();
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
