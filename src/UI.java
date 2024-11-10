// muestra matriz
// muestra puntuacion
// imprime mensajes del juego
// maneja input del juego
import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private Motor motor; //Instancia motor para manejar el juego

    public UI() {
         // Instanciar la clase que maneja la lógica del juego
        this.scanner = new Scanner(System.in); //Lee la entrada del usuario
        this.motor = new Motor();
    }


    //Método que maneja las opciones del menú
    public void menu() {
        boolean continuar = true;
    
        while (continuar) {
            System.out.print("Determinar\n1. Iniciar juego\n2. Instrucciones del juego\n3. Salir\nOpcion: ");
            
            try {
                // Chequea si hay input válido
                if (scanner.hasNextInt()) {
                    int opcion = scanner.nextInt();
                    
                    switch (opcion) {
                        case 1:
                            motor.iniciarJuego();
                            break;
                        case 2:
                            mostrarInstrucciones();
                            break;
                        case 3:
                            continuar = false;
                            break;
                        default:
                            System.out.println("Digite una opcion válida (1-3)");
                    }
                } else {
                    // Si el input es inválido (no es un int)
                    System.out.println("Por favor ingrese un número válido");
                    scanner.next(); // Ignora el input inválido
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada. Por favor intente de nuevo");
                scanner.nextLine(); // Limpia el scanner
            }
        }
    }

    // Método que muestra las instrucciones del juego
    private void mostrarInstrucciones() {
        System.out.print("1\n "); 
    }
}