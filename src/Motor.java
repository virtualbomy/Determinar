import java.util.Scanner;
/**
 * Clase Motor.
 */
public class Motor {
	private Scanner scanner;
	private Jugador[] jugadores;
	private int rondaActual;

	/**
	 * Constructor.
	 */
	public Motor() {
		// !!!TEMPORAL!!! para leer el input del usuario en la terminal; puede que no sea necesario en una GUI
		this.scanner = new Scanner(System.in); 
		// Los tres jugadores
		this.jugadores = new Jugador[3];
		// Le asigna el nombre correcto a los jugadores
		for (int i = 0; i < 3; i++) {
			jugadores[i] = new Jugador ("Jugador " + (i + 1));
		}
		this.rondaActual = 0;
	}

	/**
	 * Inicializa el sistema de cada jugador y luego ejecuta la lógica del juego.
	 */
	public void iniciarJuego() {
		inicializarSistema();
		jugarPartida();
	}
	
	/**
	 * Inicializa el sistema de cada jugador. Se asegura de seguir generando sistemas hasta que todo jugador haya elegido un sistema.
	 */
	private void inicializarSistema() {
		int votosSistema = 0;
		int i = 0;
		SistemaLineal sistema = new SistemaLineal();

		// Mientras falte algún jugador por elegir
		while (votosSistema < 3) {
			// !!!TEMPORAL!!! cambiar por GUI
			System.out.println("Sistema de ecuaciones " + (i + 1));
			// Genera un sistema válido (consistente)
			sistema.generarSistemaValido();
			// Imprime el sistema generado  
			sistema.imprimirSistema();
			// Le pregunta a cada jugador si desea usar ese sistema en su partida; devuelve el número de votos hasta el momento
			votosSistema = obtenerVotos(votosSistema, sistema);
			i++;
		}
	}

	/**
	 * Obtiene los votos de cada jugador para que decidan si quieren usar un sistema en su partida.
	 * @param votosSistema Los votos hasta el momento.
	 * @param sistema El sistema generado.
	 * @return El número de votos actualizado.
	 */
	private int obtenerVotos(int votosSistema, SistemaLineal sistema) {
		// Por cada jugador
		for (Jugador jugador : jugadores) {
			// Si ya eligió sistema, sálteselo
			if (jugador.getSistemaElegido()) {
				continue;
			}
			// Asume que la entrada es inválida
			boolean entradaValida = false;

			// Mientras el jugador no escoja una opción válida
			// !!!TEMPORAL!!! Esto debe ser implementado en la GUI
			while (!entradaValida) {
				System.out.printf("%s: ¿Desea Jugar con este sistema?\n 1.Sí\n 2.No\n Opción: ", jugador.getNombre());

				try {
					// !!!TEMPORAL!!! Obtenga el próximo entero
					if (scanner.hasNextInt()) {
						int eleccion = scanner.nextInt();

						// !!!TEMPORAL!!! Si la elección del jugador es válida
						if (eleccion == 1 || eleccion == 2) {
							// !!!TEMPORAL!!! Si elige que sí
							if (eleccion == 1) {
								// Guarda que ya votó por un sistema
								jugador.setVotoRealizado(true);
								// Guarda el sistema como su sistema original
								jugador.setSistemaOriginal(new SistemaLineal(sistema));
								// Guarda el determinante de su sistema original
								jugador.setDetOriginal(sistema.getMatriz().det());
								// Guarda una copia del sistema original para poder jugar
								jugador.setSistema(new SistemaLineal(sistema));
								// Aumenta el número de jugadores que han votado por un sistema
								votosSistema++;
							}
							// Valida la entrada
							entradaValida = true;
							System.out.println(" ");
							// Se sale del while
							break;
						} else {
							// Si el jugador ingresa un número inválido
							System.out.println("Por favor ingrese 1 o 2");
						}
					} else {
						// Si el jugador ingresa algo no numérico
						// ESTO EN LA GUI NO ES NECESARIO PORQUE EN TEORÍA DEBERÍA DE HACERSE CON BOTONES
						System.out.println("Por favor ingrese un número válido (1 o 2)");
						scanner.next(); 
					}
				} catch (Exception e) {
					// AL IGUAL QUE LO DE ARRIBA, ESTO SOBRA EN LA GUI
					System.out.println("Error en la entrada. Por favor intente de nuevo");
					scanner.nextLine(); 
				}
			}
		}
		// Devuelve el número de jugadores que votaron por un sistema
		return votosSistema;
	}
	
	/**
	 * Loop principal del juego.
	 */
	private void jugarPartida() {
		// Mientras no se hayan jugado las cinco rondas
		while (rondaActual < 5) {
			// !!!GUI!!!
			System.out.println("\n=== RONDA " + (rondaActual + 1) + " ===");

			// Por cada jugador
			for (Jugador jugador : jugadores) {
				// Si el jugador perdió su turno
				if (jugador.perdioTurno()) {
					// !!!GUI!!!
					System.out.println(jugador.getNombre() + " pierde este turno.");
					// Evita que el jugador pierda el siguiente turno 
					jugador.perderTurno(false);
					// Siga con el próximo jugador
					continue;
				}
				realizarTurno(jugador);
				// Si el determinante obtenido por el jugador actual es 21 
				if (Math.abs(jugador.getSistema().getMatriz().det()) == 21) {
					// !!!GUI!!!
					System.out.println(jugador.getNombre() + " logró un determinante de 21. ¡Gana inmediatamente!");
					// Jugador actual gana inmediatamente; terminar
					return;
				}
			}
			// Avanza a la siguiente ronda
			rondaActual++;
		}
		determinarGanador();
	}

	/**
	 * Realiza el turno de un jugador.
	 * @param jugador El jugador que realiza su turno.
	 */
	private void realizarTurno(Jugador jugador) {
		// !!!GUI!!!
		System.out.println(jugador.getNombre() + ", su turno.");
		System.out.println("Su matriz original:");
		jugador.getSistemaOriginal().imprimirSistema();
		System.out.println("Su matriz actual:");
		jugador.getSistema().imprimirSistema();

		// Obtiene las coordenadas de la entrada que quiere modificar, así como el nuevo valor
		// LAS COORDENADAS SE PUEDEN OBTENER DESDE LA GUI SI SE IMPLEMENTAN BOTONES PARA ESCOGERLAS CON UN CLICK
		int[] coordenadas = solicitarCoordenadas();
		// Esto sí tiene que ir en una ventanita de input
		double nuevoValor = solicitarNuevoValor();
		
		// Modifica la matriz del sistema del jugador
		jugador.modificarMatriz(coordenadas[0], coordenadas[1], nuevoValor);
		
		// Si el determinante jugado en este turno ya se jugó antes por este jugador, este jugador pierde su próximo turno
		if (jugador.yaUsoDeterminante()) {
			System.out.println("¡Determinante repetido! Pierde el próximo turno.");
			jugador.perderTurno(true);
		}
		
		// Si el jugador juega un determinante igual al determinante de la matriz del sistema original, repite su turno
		if (jugador.deberiaRepetirTurno()) {
			System.out.println("El determinante nuevo es igual al original. Repite turno.");
			realizarTurno(jugador);
			return;
		}
		
		jugador.actualizarPuntuacion();
		// !!!GUI!!!
		System.out.println("Su nueva puntuación es " + jugador.getPuntuacion());
	}
	
	/**
	 * Obtiene las coordenadas en las que se debe de realizar la modificación de una matriz.
	 * <p>ESTO SE PUEDE IMPLEMENTAR CON BOTONES EN LA GUI</p>
	 * @return un arreglo de dos elementos con las coordenadas i,j de la entrada por modificar.
	 */
	private int[] solicitarCoordenadas() {
		int[] coords = new int[2];
		while (true) {
			try {
				// !!GUI!!
				System.out.println("Ingrese fila (0 a 2): ");
				coords[0] = scanner.nextInt();
				System.out.println("Ingrese columna (0 a 2): ");
				coords[1] = scanner.nextInt();
				
				// SI LAS COORDENADAS SON VÁLIDAS (NO SE SALEN DEL 3x3)
				if (coords[0] >= 0 && coords[0] < 3 && coords[1] >= 0 && coords[1] < 3) {
					return coords;
				}
				System.out.println("Coordenadas inválidas. Intente de nuevo.");
			} catch (Exception e) {
				System.out.println("Entrada inválida. Use números entre 0 y 2.");
				scanner.nextLine();
			}
		}
	}
	
	/**
	 * Obtiene el nuevo valor que se ha de guardar en una entrada de la matriz de un sistema.
	 * @return
	 */
	private double solicitarNuevoValor() {
		while (true) {
			try {
				// !!GUI!!
				System.out.println("Ingrese nuevo valor para la entrada elegida.");
				return scanner.nextDouble();
			} catch (Exception e) {
				System.out.println("Valor inválido. Intente de nuevo.");
				scanner.nextLine();
			}
		}
	}
	
	/**
	 * Determina el ganador del juego.
	 * <p></p>
	 */
	private void determinarGanador() {
		Jugador ganador = null;
		// para buscar al jugador con la puntuación más alta
		double maxPuntuacion = Double.NEGATIVE_INFINITY;
		double maxDeterminante = Double.NEGATIVE_INFINITY;

		// por cada jugador
		for (Jugador jugador : jugadores) {
			// si su puntuación es mayor que la máxima hasta el momento, o bien,
			// si la puntuación máxima pertenece a este jugador y el último determinante del ganador es igual al máximo
			if (jugador.getPuntuacion() > maxPuntuacion || (jugador.getPuntuacion() == maxPuntuacion && jugador.getUltimoDeterminante() > maxDeterminante)) {
				// entonces este jugador es el ganador
				ganador = jugador;
				// la puntuación máxima es la de este jugador
				maxPuntuacion = jugador.getPuntuacion();
				// el determinante máximo es el de este jugador
				maxDeterminante = jugador.getUltimoDeterminante();
			}
		}

		// !!!GUI!!!
		System.out.println("\n--- Resultados Finales ---");
		for (Jugador jugador : jugadores) {
			System.out.println(jugador.getNombre() + ": " + jugador.getPuntuacion() + " puntos");
		}

		// Si hay un ganador, lo anuncia (!!!GUI!!!)
		if (ganador != null) {
			System.out.println("\n¡" + ganador.getNombre() + " gana la partida!");
		} else {
			System.out.println("\n¡Empate!");
		}
	}
};