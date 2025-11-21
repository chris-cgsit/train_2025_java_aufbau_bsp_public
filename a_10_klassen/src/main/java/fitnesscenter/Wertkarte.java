package fitnesscenter;

public class Wertkarte {
    // Attribute (=Feld/Membervariable) für Nr, Inhaber usw.
    // Attriubte definieren die Daten eines Objekts
    private int nr;
    private String inhaber;
    private double guthaben;

    // Methoden definieren die Fähigkeiten eines Objekts
    // public Methoden dürfen von überall verwendet werden und
    // stellen die Schnittstelle der Klasse dar
    public void setzen(int nr, String inhaber){
        // die Werte ins Objekt übernehmen
        // this meint das aktuelle Objekt
        this.nr = nr ;
        this.inhaber = inhaber;
    }
    public void anzeigen(){
        System.out.printf("Wertkarte Nr=%d, Inhaber=%s, Guthaben=%.2f EUR\n",
                nr, inhaber, guthaben);
    }

    public void aufbuchen(double betrag) {
        if (guthaben + betrag > 300) {
            //System.out.println("Aufbuchen: Betrag ist zu hoch");
            // statt Ausgabe: Fehler auslösen
            // damit wird die normale Ausführung unterbrochen und am Call Stack
            // ein passender catch-Block gesucht. Wenn es einen gibt, geht die
            // Ausführung dort weiter. Wenn nicht wird das Programm beendet.
            throw new IllegalArgumentException("Betrag für Aufbuchung zu hoch");
        }
        // wenn wir hier herkommen, ist der Betrag OK und aufgebucht werden
        guthaben += betrag;

    }

    public void abbuchenGymnastik() {
        abbuchen(7, "Gymnastik");
    }

    public void abbuchenFitness2h() {
        abbuchen(14, "Fitness2h");
    }

    // Private Methode darf nur von der Klasse selber verwendet werden
    private void abbuchen(double betrag, String typ) {
        if (guthaben - betrag < 0) {
            //System.out.println("Abbuchen " + typ + ": Guthaben zu klein");
            // statt Ausgabe: Fehler werfen
            throw new IllegalArgumentException(
                    "Guthaben zu klein für Abbuchung von " + typ);
        }
        guthaben -= betrag;
    }

}
