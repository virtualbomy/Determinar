import java.util.ArrayList;
import java.util.List;
/**
 * Clase Jugador. Tiene toda la información necesaria y pertinente a un jugador individual.
 */
public class Jugador {
	private String nombre;
	private double puntuacion;
	private boolean saltarSiguienteTurno;
	// para verificar si el jugador ya jugó un determinante
	private List<Double> historialDeterminantes;
	// para verificar si el jugador está jugando el determinante original
	private SistemaLineal sistemaOriginal;
	private double determinanteOriginal;
	// el sistema a partir del que se realizan los cálculos de la puntuación
	private SistemaLineal sistema;
	// para determinar si el jugador ya eligió un sistema
	private boolean sistemaElegido;
	// en caso de empate
	private double primerDeterminante;
	private double ultimoDeterminante;

	/**
	 * Constructor parametrizado de la clase Jugador. 
	 * @param nuevoNombre El nuevo nombre que se la asignará al jugador.
	 */
	public Jugador(String nuevoNombre) {
		this.nombre = nuevoNombre;
		this.puntuacion = 0;
		this.saltarSiguienteTurno = false;
		this.historialDeterminantes = new ArrayList<>();
		this.primerDeterminante = 0;
		this.ultimoDeterminante = 0;
	}

	/**
	 * Actualiza la puntuación del jugador a partir de su determinante original y su determinante actual
	 * <p>-Si el determinante actual > 0: suma abs(original - actual)</p>
	 * <p>-Si el determinante actual < 0: suma actual</p>
	 */
	public void actualizarPuntuacion() {
		// Si es el primer turno
		if (historialDeterminantes.isEmpty()) {
			primerDeterminante = ultimoDeterminante;
		}

		// El determinante actual es último que se ha jugado
		double actual = sistema.getMatriz().det();
		ultimoDeterminante = actual;

		// Actualiza la puntuacion segun las reglas
		if (actual > 0) {
			// Suma el valor absoluto de la diferencia entre el determinante original y el determinante actual
			puntuacion += Math.abs(determinanteOriginal - actual);
		} else {
			// Suma el valor negativo del determinante actual
			puntuacion += actual;
		}

		historialDeterminantes.add(actual);
	}

	/**
	 * Verifica si el determinante que se está jugando ya ha sido jugado en el pasado.
	 * @return true si se ha jugado; false si no.
	 */
	public boolean yaUsoDeterminante() {
		return historialDeterminantes.contains(sistema.getMatriz().det());
	}

	/**
	 * Verifica si el determinante actual es el mismo que el original con tal de forzar al jugador a repetir turno.
	 * @return true si debe de repetir turno; false si no.
	 */
	public boolean deberiaRepetirTurno() {
		return determinanteOriginal == sistema.getMatriz().det();
	}

	/**
	 * Modifica la matriz del sistema lineal de este jugador.
	 * @param fila La fila de la entrada que se va a modificar.
	 * @param col La columna de la entrada que se va a modificar.
	 * @param newValor El nuevo valor que va a ir en las coordenadas especificadas.
	 */
	public void modificarMatriz(int fila, int col, double nuevoValor) {
		if (sistema == null) {
			throw new IllegalStateException("Sistema personal no inicializado.");
		}
		
		sistema.actualizarEntrada(fila, col, nuevoValor);
	}

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public boolean perdioTurno() {
		return saltarSiguienteTurno;
	}

	public void perderTurno(boolean perdida) {
		this.saltarSiguienteTurno = perdida;
	}

	public SistemaLineal getSistemaOriginal() {
		return sistemaOriginal;
	}
	
	public void setSistemaOriginal(SistemaLineal newSistema) {
		this.sistemaOriginal = newSistema;
	}

	public double getDetOriginal() {
		return determinanteOriginal;
	}
	
	public void setDetOriginal(double newDet) {
		this.determinanteOriginal = newDet;
	}
	
	public SistemaLineal getSistema() {
		return sistema;
	}
	
	public void setSistema(SistemaLineal newSistema) {
		this.sistema = newSistema;
	}
	
	public boolean getSistemaElegido() {
		return sistemaElegido;
	}
	
	public void setVotoRealizado(boolean newSistemaElegido) {
		sistemaElegido = newSistemaElegido;
	}

	public double getPrimerDeterminante() {
		return primerDeterminante;
	}

	public double getUltimoDeterminante() {
		return ultimoDeterminante;
	}
};