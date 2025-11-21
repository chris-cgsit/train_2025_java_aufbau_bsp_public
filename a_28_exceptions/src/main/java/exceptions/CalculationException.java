package exceptions;

// diese Klasse ist eine unchecked Exception weil sie von RuntimeException ableitet
// -> kann ohne Deklaration geworfen werden
public class CalculationException extends RuntimeException{
    // Konstruktor mit Angabe des Fehlertextes
    public CalculationException(String msg){
        // den Fehlertext an die Basisklasse weitergeben
        super(msg);
    }

    // Konstruktor mit Angabe des Fehlertextes und des auslösenden Fehlers
    public CalculationException(String msg, Exception cause){
        // den Fehlertext und den auslösenden Fehler an die Basisklasse weitergeben
        super(msg, cause);
    }

}
