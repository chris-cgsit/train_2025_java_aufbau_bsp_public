package exceptions2;

// diese Klasse ist eine checked Exception weil sie direkt von Exception ableitet
// -> beim Werfen gilt das catch-or-specify-Prinzip
public class CalculationException extends Exception{
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
