package practica1;

import java.util.Scanner;

public class Cajero {
    private Usuario usuario; // el usuario actual
    private Scanner teclado = new Scanner(System.in);

    public Cajero(Usuario usuario) {
        this.usuario = usuario;
    }

    public void Bienvenida() {
        System.out.println("Bienvenido, " + usuario.getNombre());
    }

    public void VerSaldo() {
        System.out.println("Su saldo es: $" + usuario.getSaldo());
    }

    public void Retirar() {
        System.out.print("Ingrese cantidad a retirar: ");
        double retiro = teclado.nextDouble();
        if (retiro <= usuario.getSaldo()) {
            usuario.setSaldo(usuario.getSaldo() - retiro);
            System.out.println("Retiro exitoso. Nuevo saldo: $" + usuario.getSaldo());
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void Depositar() {
        System.out.print("Ingrese cantidad a depositar: ");
        double deposito = teclado.nextDouble();
        usuario.setSaldo(usuario.getSaldo() + deposito);
        System.out.println("Depósito exitoso. Nuevo saldo: $" + usuario.getSaldo());
    }

    public void Salir() {
        System.out.println("Gracias por usar el cajero, " + usuario.getNombre());
    }
}
