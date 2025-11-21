package fitnesscenter2;

public class FitnesscenterProgramm2 {
    public static void main(String[] args) {
        System.out.println("fitnesscenter2.Fitnesscenter2");
        // statt Erzeugung mit Default-Konstruktor plus setzen
        // jetzt direkt die die Parameter an den Konstruktor übergeben
        Wertkarte karte1 = new Wertkarte(1, "Maxi");
        karte1.anzeigen();
        // abbuchen bei Guthaben 0
        bucheAbFitness(karte1);
        bucheAuf(karte1, 150);
        bucheAuf(karte1, 160);
        bucheAuf(karte1, 100);
        bucheAbFitness(karte1);
        //bucheAuf(karte1, 20);

        System.out.println("Nummer: " + karte1.getKartenNummer());
        System.out.println("Guthaben: " + karte1.getGuthaben());
        System.out.println("Inhaber: " + karte1.getInhaber());

        // Inhabername ändern
        karte1.setInhaber("Moritz");
        karte1.anzeigen();
    }

    static void bucheAuf(Wertkarte karte, double betrag){
        try {
            System.out.println("Aufbuchung: ");
            System.out.print("Vorher: ");
            karte.anzeigen();
            karte.aufbuchen(betrag);

        }catch (IllegalArgumentException e){
            System.out.println("Fehler beim Aufbuchen: " + e.getMessage());
        }
        System.out.print("Nachher: ");
        karte.anzeigen();
        System.out.println();
    }
    static void bucheAbFitness(Wertkarte karte){
        try {
            System.out.println("Abbuchung: ");
            System.out.print("Vorher: ");
            karte.anzeigen();
            karte.abbuchenFitness2h();

        }catch (IllegalArgumentException e){
            System.out.println("Fehler beim Abbuchen: " + e.getMessage());
        }
        System.out.print("Nachher: ");
        karte.anzeigen();
        System.out.println();
    }
}
