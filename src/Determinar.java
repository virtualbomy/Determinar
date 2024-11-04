// loop principal
// maneja los turnos de los jugadores
// inicializa el juego
// menu para iniciar juegos o salir
public class Determinar {
  private SistemaLineal sistema;
  
  public SistemaLineal getSistema() {
    return sistema;
  }
  public void setSistema(SistemaLineal nuevoSistema) {
    this.sistema = nuevoSistema;
  }
  
  // Constructor
  public Determinar() {
    this.sistema = new SistemaLineal();
  }
  public static void main(String[] args) {
    Determinar juego = new Determinar();

    juego.getSistema().getMatriz().print(3, 0);

    System.out.println("Constantes solucion:");
    for (int i = 0; i < 3; i++) {
      System.out.println("b" + (i + 1) + ": " + juego.getSistema().getConstantes()[i]);
    }
  }
}