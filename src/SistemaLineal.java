import Jama.Matrix;
// Documentacion: https://math.nist.gov/javanumerics/jama/doc/
import java.util.Random;
// genera matrices 3x3 random con soluciones unicas
// calcula determinantes
// operaciones matriciales
// guarda y valida cambios matriciales
public class SistemaLineal {
  private Matrix matriz;
  private double[] constantes;
  
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
  
  private void generarSistemaValido() {
    do {
      double[][] nuevaMatriz = new double[3][3];
      Random random = new Random();

      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          nuevaMatriz[i][j] = random.nextInt(21) - 10;
        }
        constantes[i] = random.nextInt(21) - 10;
      }
      
      this.matriz = new Matrix(nuevaMatriz);
    } while (matriz.det() == 0);
  }

  // Constructor
  public SistemaLineal() {
    this.constantes = new double[3];
    generarSistemaValido();
  }
}