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
            System.out.print("Determinar\n1. Iniciar juego\n2. Instrucciones del juego\n3. Salir\nOpcion: "); // Muestra el menú
            int opcion = scanner.nextInt(); // Lee la opción seleccionada por el usuario

            switch (opcion) {
                case 1:
                    motor.iniciarJuego(); // Llama a iniciarJuego() de Motor
                    break;
                case 2:
                    mostrarInstrucciones(); // Muestra las instrucciones
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Digite una opcion válida");
            }
        }
    }

    // Método que muestra las instrucciones del juego
    private void mostrarInstrucciones() {
        System.out.print("1\n "); 
    }
}