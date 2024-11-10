// conteo de turno
// guarda determinante original
// maneja condiciones de victoria
// maneja las reglas del juego
import java.util.Scanner;

public class Motor {
    private Scanner scanner;
    private SistemaLineal sistema; 

    public Motor() {
        // Instanciar la clase que maneja la lógica del juego
       this.scanner = new Scanner(System.in); //Lee la entrada del usuario
       this.sistema = new SistemaLineal();
   }

    public void iniciarJuego() {
        int votosSistema = 0;
        int eleccion = 0;
        int i = 0;
        
        while (votosSistema < 3) {
            System.out.println("Sistema de ecuaciones " + (i+1));
            
            // Generar un nuevo sistema de ecuaciones en cada ciclo
            sistema.generarSistemaValido();
            
            // Imprimir el sistema de ecuaciones
            sistema.imprimirSistema();
            System.out.println(" ");
            
            // Función para obtener voto de cada jugador
            votosSistema = obtenerVotos(votosSistema);
            
            System.out.println(" ");
            i++;
        }
    }

    private int obtenerVotos(int votosSistema) {
        for (int jugador = 1; jugador <= 3; jugador++) {
            boolean entradaValida = false;
            
            while (!entradaValida) {
                System.out.printf("Jugador %d: Desea Jugar con este sistema?\n 1.Si\n 2.No\n Opcion: ", jugador);
                
                try {
                    if (scanner.hasNextInt()) {
                        int eleccion = scanner.nextInt();
                        
                        if (eleccion == 1 || eleccion == 2) {
                            if (eleccion == 1) {
                                votosSistema++;
                            }
                            entradaValida = true;
                            System.out.println(" ");
                        } else {
                            System.out.println("Por favor ingrese 1 o 2");
                        }
                    } else {
                        System.out.println("Por favor ingrese un número válido (1 o 2)");
                        scanner.next(); // Clear invalid input
                    }
                } catch (Exception e) {
                    System.out.println("Error en la entrada. Por favor intente de nuevo");
                    scanner.nextLine(); // Clear the scanner buffer
                }
            }
        }
        return votosSistema;
    }
}