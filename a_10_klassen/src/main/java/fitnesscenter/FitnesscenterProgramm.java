package fitnesscenter;

public class FitnesscenterProgramm {
    public static void main(String[] args) {
        // sout statt sysout
        // souf für printf
        System.out.println("Fitnesscenter");

        try {
            Wertkarte karte1 = new Wertkarte();
            karte1.setzen(1, "Maxi");
            karte1.anzeigen();
            // aufbuchen
            karte1.aufbuchen(150);
            karte1.anzeigen();
            // abbuchen einer Leistung
            karte1.abbuchenGymnastik();
            karte1.anzeigen();


            //zu viel aufbuchen
            karte1.aufbuchen(160);
            // wenn es zu viel war, geht die Ausführung im catch-Block weiter
            // und die Anzeige wird nicht ausgeführt
            karte1.anzeigen();
        } catch (IllegalArgumentException e) {
            // Fehler ausgabe
            System.out.println("Es ist ein Fehler passiert: " + e.getMessage());
        }
    }
}
