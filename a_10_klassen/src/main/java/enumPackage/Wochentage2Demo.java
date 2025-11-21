package enumPackage;

import java.util.Scanner;

public class Wochentage2Demo {

    public static void main(String[] args) {

        Wochentage2 wTag = Wochentage2.MONTAG;

        System.out.println("Der Tag ist " + wTag.toString());

        // aus einer zeichenfolge den Tag ermitteln
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcher Tag?");
        Wochentage2 wTag2 = Wochentage2.valueOf(scanner.nextLine().toUpperCase());
        System.out.printf("Der Ordinalwert von %s ist %d, die Tagesnummer ist %d\n",
                wTag2, wTag2.ordinal(), wTag2.getTagesNummer());


        // Enums dürfen im switch verwendet werden
        switch (wTag2) {
            case MITTWOCH, DONNERSTAG -> System.out.println("Wir haben Java-Kurs!");
            default -> System.out.println("Wir haben Zeit für etwas anderes");
        }
        if (wTag2.istWochende()) {
            System.out.printf("Am %s ist frei %n", wTag2);
        } else {
            System.out.println("Es ist ein Arbeitstag");
        }

        System.out.printf("Die Tagesnummer von %s ist %d %n",
                wTag2, wTag2.getTagesNummer());

        // Alle Wochentage anzeigen
        Wochentage2[] alleTage = Wochentage2.values();
        for (Wochentage2 tag : alleTage) {
            System.out.printf("%s (tagNr=%d, ordinalWert=%d) %n",
                    // ordinal() liefert den Index der Instanz in der Klasse
                    tag, tag.getTagesNummer(), tag.ordinal());
        }

        scanner.close();
    }

}
