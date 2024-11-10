import java.util.ArrayList;
import java.util.List;
// puntuacion
// nombre de jugador?
// logica de repeticion y salto de turno
// historial de determinantes

public class Jugador {
    private String nombre;
    private double puntuacion;
    private boolean saltarSiguienteTurno;
    private List<Double> historialDeterminantes;
    // en caso de empate
    private double primerDeterminante;
    private double ultimoDeterminante;

    public Jugador(String nuevoNombre) {
        this.nombre = nuevoNombre;
        this.puntuacion = 0;
        this.saltarSiguienteTurno = false;
        this.historialDeterminantes = new ArrayList<>();
        this.primerDeterminante = 0;
        this.ultimoDeterminante = 0;
    }

    // Actualiza la puntuacion del jugador a partir de su determinante original y el determinante actual
    // - Si el determinante actual > 0: sumar abs(original - actual)
    // - Si el determinante actual < 0: sumar actual
    public void actualizarPuntuacion(double original, double actual) {
        // Si es el primer turno
        if (historialDeterminantes.isEmpty()) {
            primerDeterminante = ultimoDeterminante;
        }
        
        ultimoDeterminante = actual;
        
        // Actualiza la puntuacion segun las reglas
        if (actual > 0) {
            puntuacion += Math.abs(original - actual);
        } else {
            puntuacion += actual; // Suma el valor negativo
        }
        
        historialDeterminantes.add(actual);
    }

    // Revisa si el determinante actual es uno que jugo anteriormente
    public boolean yaUsoDeterminante(double determinante) {
        return historialDeterminantes.contains(determinante);
    }

    // Revisa si este determinante es el mismo que el original para repetir turno
    public boolean deberiaRepetirTurno(double original, double actual) {
        return original == actual;
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

    public double getPrimerDeterminante() {
        return primerDeterminante;
    }

    public double getUltimoDeterminante() {
        return ultimoDeterminante;
    }
}