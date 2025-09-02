package views;
import java.util.Scanner;
public class CajeroViews {
    private Scanner scanner;
    public CajeroViews(){
        scanner = new Scanner(System.in);
    }
    //Mostar bienvenida
    public void mostrarBienvenida(){
        System.out.println("=============================");
        System.out.println("¡Bienvenido Usuario!, AL BANCO MEXA");
        System.out.println("=============================");
    }
    //Método solicitar Número de cuenta
    public String solicitarNumeroCuenta(){
        System.out.println("Ingresa tú número de cuenta: ");
        return scanner.nextLine();
    }
    //Método solicitar PIN
    public String solicitarPin(){
        System.out.println("Ingresa tú de PIN: ");
        return scanner.nextLine();
    }
    //Método para mostrar el Menú principal
    public void mostrarMenuPrincipal(String titular){
        System.out.println("=============================");
        System.out.println("¡Bienvenid@ "+titular);
        System.out.println("=============================");
        System.out.println("1.- Consultar saldo");
        System.out.println("2.- Retirar");
        System.out.println("3.- Depositar");
        //TAREA: Definir las opciones faltantes
        System.out.println("9.- Salir");
    }
    //Método para leer la opción del menú
    public int leerOpcion(){
        try{
            return Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException e){
            return -1;
        }
    }
    //Método mostrar saldo
    public void mostrarSaldo(double saldo){
        System.out.println("=============================");
        System.out.println("Tú saldo actual es: "+saldo);
        System.out.println("=============================");
    }
    
    public double solicitarCantida(String operacion){
        System.out.println("Ingresa la cantidad a "+operacion+": ");
        try{
            return Double.parseDouble(scanner.nextLine());
        }catch (NumberFormatException e){
            return -1;
        }
    }
    //Método para mostrar mensajes
    public void mostrarMensajes(String mensaje){
        System.out.println("===== "+mensaje);
    }
    //Tarea: Personalizar mensajes de error y de éxito
    public void mostrarError(String mensaje){
        System.out.println("❌ ERROR: " + mensaje);
    }

    public void mostrarExito(String mensaje){
        System.out.println("✅ ÉXITO: " + mensaje);
    }

    //Tarea: Método para salir y cerrar el Scanner
    public void cerrar(){
        System.out.println("=============================");
        System.out.println("Gracias por usar BANCO MEXA. ¡Hasta pronto!");
        System.out.println("=============================");
        if(scanner != null){
            scanner.close();
        }
    }

}
