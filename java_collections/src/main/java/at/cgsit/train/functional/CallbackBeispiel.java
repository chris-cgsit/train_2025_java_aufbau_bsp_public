package at.cgsit.train.functional;

import java.util.function.Consumer;

// Beispiel: Callback mit Lambda
public class CallbackBeispiel {

    public static void main(String[] args) {

        // Handler-Objekt, das mit dem Ergebnis etwas tun soll
        ErgebnisHandler handler = new ErgebnisHandler();

        // Service wird aufgerufen, Lambda übergibt die Callback-Logik
        DatenService service = new DatenService();
        service.ladeDaten(
            daten -> handler.verbucheErgebnis(daten)  // Lambda als Callback
        );

        // Alternative: Method Reference
        // service.ladeDaten(handler::verbucheErgebnis);
    }
}

// Service simuliert eine asynchrone oder verzögerte Aktion
class DatenService {

    public void ladeDaten(Consumer<String> callback) {
        System.out.println("Lade Daten...");
        try {
            Thread.sleep(1000); // simuliert Wartezeit
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Ergebnis der "Berechnung"
        String result = "Datenpaket-123";

        // Callback aufrufen → an den Handler weiterreichen
        callback.accept(result);
    }
}

// Handler verarbeitet das Ergebnis
class ErgebnisHandler {

    public void verbucheErgebnis(String daten) {
        System.out.println("Verarbeite Ergebnis: " + daten);
        System.out.println("→ Ergebnis erfolgreich verbucht!");
    }
}
