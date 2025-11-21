package enumPackage;

import java.util.Scanner;

public class Wochentage1Demo {

    public static void main(String[] args) {

        Wochentage1 wTag = Wochentage1.MONTAG;

        System.out.println("Der Tag ist " + wTag.toString());

        // aus einer zeichenfolge den Tag ermitteln
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcher Tag?");
        Wochentage1 wTag2 = Wochentage1.valueOf(scanner.nextLine().toUpperCase());
        System.out.printf("Der Ordinalwert von %s ist %d\n", wTag2, wTag2.ordinal());

        // Enums dürfen im switch verwendet werden
        switch (wTag2) {
            case MITTWOCH:
            case DONNERSTAG:
                System.out.println("Wir haben Java-Kurs!");
                break;

            default:
                System.out.println("Wir haben Zeit für etwas anderes");
                break;
        }

        if (wTag2.istWochende()) {
            System.out.printf("Am %s ist frei %n", wTag2);
        } else {
            System.out.println("Es ist ein Arbeitstag");
        }
        // Alle Wochentage anzeigen
        Wochentage1[] alleTage = Wochentage1.values();
        for (Wochentage1 tag : alleTage) {
            System.out.printf("%s (ordinalWert=%d) %n",
                    // ordinal() liefert den Index der Instanz in der Klasse
                    tag, tag.ordinal());
        }

        scanner.close();
    }

}
