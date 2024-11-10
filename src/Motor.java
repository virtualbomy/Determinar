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
        int eleccion=0;
        
        int i=0;
        while (votosSistema < 3) {
            System.out.println("Sistema de ecuaciones " + (i+1));
            
            // Generar un nuevo sistema de ecuaciones en cada ciclo
            sistema.generarSistemaValido();
            
            // Imprimir el sistema de ecuaciones
            sistema.imprimirSistema();
            System.out.println(" ");
            System.out.print("Jugador 1: Desea Jugar con este sistema?\n 1.Si\n 2.No\n Opcion: ");
            eleccion = scanner.nextInt();
            if (eleccion == 1) {
                votosSistema++;
            }
            System.out.println(" ");

            System.out.print("Jugador 2: Desea Jugar con este sistema?\n 1.Si\n 2.No\n Opcion: ");
            eleccion = scanner.nextInt();
            if (eleccion == 1) {
                votosSistema++;
            }
            System.out.println(" ");

            System.out.print("Jugador 3: Desea Jugar con este sistema?\n 1.Si\n 2.No\n Opcion: ");
            eleccion = scanner.nextInt();
            if (eleccion == 1) {
                votosSistema++;
            }
            System.out.println(" ");
            i++;
        }
   }
}