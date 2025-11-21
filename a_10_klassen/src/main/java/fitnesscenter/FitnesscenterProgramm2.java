package fitnesscenter;

public class FitnesscenterProgramm2 {
    public static void main(String[] args) {
        System.out.println("Fitnesscenter2");

        Wertkarte karte1 = new Wertkarte();
        karte1.setzen(1, "Maxi");
        karte1.anzeigen();
        // abbuchen bei Guthaben 0
        bucheAbFitness(karte1);
        bucheAuf(karte1, 150);
        bucheAuf(karte1, 160);
        bucheAuf(karte1, 100);
        bucheAbFitness(karte1);
        //bucheAuf(karte1, 20);
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
