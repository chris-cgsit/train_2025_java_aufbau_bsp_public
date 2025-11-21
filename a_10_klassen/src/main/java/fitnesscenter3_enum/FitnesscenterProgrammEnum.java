package fitnesscenter3_enum;

public class FitnesscenterProgrammEnum {
    public static void main(String[] args) {
        System.out.println("fitnesscenter2.Fitnesscenter2");
        // statt Erzeugung mit Default-Konstruktor plus setzen
        // jetzt direkt die die Parameter an den Konstruktor übergeben
        Wertkarte karte1 = new Wertkarte(1, "Maxi");
        karte1.anzeigen();
        // abbuchen bei Guthaben 0
        bucheAb(karte1, Leistungen.FITNESS_2H);
        bucheAuf(karte1, 150);
        bucheAuf(karte1, 160);
        bucheAuf(karte1, 100);
        bucheAb(karte1, Leistungen.WELLNESS);
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

    static void bucheAb(Wertkarte karte, Leistungen leistung){
        try {
            System.out.println("Abbuchung: ");
            System.out.print("Vorher: ");
            karte.anzeigen();
            karte.abbuchen(leistung);

        }catch (IllegalArgumentException e){
            System.out.println("Fehler beim Abbuchen: " + e.getMessage());
        }
        System.out.print("Nachher: ");
        karte.anzeigen();
        System.out.println();
    }
}
