import Jama.Matrix;
// Documentacion: https://math.nist.gov/javanumerics/jama/doc/
import java.util.Random;
/**
 * Clase SistemaLineal.
 */
public class SistemaLineal {
  private Matrix matriz;
  private double[] constantes;
  
	// Getters y setters
  public Matrix getMatriz() {
    return matriz;
  }
  public void setMatriz(Matrix nuevaMatriz) {
    this.matriz = nuevaMatriz;
  }
  
  public double[] getConstantes() {
    return constantes;
  }
  public void setConstantes(double[] nuevasConstantes) {
    this.constantes = nuevasConstantes;
  }
  
	/**
	 * Genera un sistema válido (consistente).
	 */
  public void generarSistemaValido() {
		// Genera por lo menos un sistema una vez
    do {
			// Genera una matriz 3x3
      double[][] nuevaMatriz = new double[3][3];
			// Genera un randomizador para poder generar números aleatorios
      Random random = new Random();

			// Por cada fila
      for (int i = 0; i < 3; i++) {
				// Por cada columna
        for (int j = 0; j < 3; j++) {
					// En la entrada i,j, genera un número aleatorio en el rango [-10, 11[
          nuevaMatriz[i][j] = random.nextInt(21) - 10;
        }
				// Genera las constantes de cada ecuación
        constantes[i] = random.nextInt(21) - 10;
      }
      
			// Guarda los valores de las entradas de la matriz generada en este sistema lineal
      this.matriz = new Matrix(nuevaMatriz);
			// Mientras el determinante de la matriz sea 0 (el sistema es inconsitente), repita la generación de matriz
    } while (matriz.det() == 0);
  }
  
	/**
	 * Actualiza una entrada en la matriz del sistema.
	 * @param fila La fila de la entrada por modificar.
	 * @param col La columna de la entrada por modificar.
	 * @param nuevoValor El nuevo valor que se va a guardar en las coordenadas especificadas.
	 */
  public void actualizarEntrada(int fila, int col, double nuevoValor) {
		// Si se especifican coordenadas inválidas
    if (fila < 0 || fila >= 3 || col < 0 || col >=3) {
      throw new IllegalArgumentException("Posicion invalida");
    }
		// Si las coordenadas son válidas, guarda el nuevo valor en las coordenadas especificadas
    matriz.set(fila, col, nuevoValor);
  }

	/**
	 * Imprime el sistema de ecuaciones en la terminal.
	 */
	// !!!TEMPORAL!!! PUEDE MODIFICARSE CUANDO SE IMPLEMENTE LA GUI
  public void imprimirSistema() {
    System.out.printf("%.0fx + %.0fy + %.0fz = %.0f%n", matriz.get(0,0), matriz.get(0,1), matriz.get(0,2), constantes[0]);
    System.out.printf("%.0fx + %.0fy + %.0fz = %.0f%n", matriz.get(1,0), matriz.get(1,1), matriz.get(1,2), constantes[1]);
    System.out.printf("%.0fx + %.0fy + %.0fz = %.0f%n", matriz.get(2,0), matriz.get(2,1), matriz.get(2,2), constantes[2]);
  }

  // Constructor
  public SistemaLineal() {
    this.constantes = new double[3];
    generarSistemaValido();
  }
	// Constructor de copia
	public SistemaLineal(SistemaLineal sisOriginal) {
		this.matriz = new Matrix(sisOriginal.getMatriz().getArrayCopy());
		this.constantes = sisOriginal.getConstantes().clone();
	}
}