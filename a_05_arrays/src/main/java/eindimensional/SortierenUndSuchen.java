package eindimensional;

import inputHilfe.InputUtil;

import java.util.Arrays;
import java.util.Random;

public class SortierenUndSuchen {
    public static void main(String[] args) {
        // Array-Objekt mit 12 Werten erzeugen
        int[] werte = new int[12];


        // Zufallszahlen eintragen
        // Generator für Zufallszahlen
        Random zufall = new Random();
        for (int i = 0; i < werte.length; i++) {
            int zufallsWert = zufall.nextInt(50); // Wert zwischen 0 und 49
            werte[i] = zufallsWert + 20; // Werte zwischen 20 und 69
        }

        System.out.println("Werte unsortiert:");
        for (int wert : werte) {
            System.out.print(wert + " ");
        }
        System.out.println();

        System.out.println("Welche Zahl suchen?");
        int zahl = InputUtil.readInt();
        // Binärsuche verwenden um die Zahl zu suchen
        // Achtung: die Suche im unsortierten Array liefert möglicherweise
        // falsch-negative Ergebnisse
        int index = Arrays.binarySearch(werte, zahl);
        if (index < 0) {
            System.out.println("Wert nicht gefunden");
        } else {
            System.out.printf("Wert steht am Index %d\n", index);
        }

        // das Array sortieren
        Arrays.sort(werte);
        System.out.println("Werte sortiert:");
        System.out.println(Arrays.toString(werte));

        // Binärsuche im sortierten Array
        index = Arrays.binarySearch(werte, zahl);
        if (index < 0) {
            System.out.println("Wert nicht gefunden");
        } else {
            System.out.printf("Wert steht am Index %d\n", index);
        }

        System.out.println("toString:");
        // Arrays.toString(...) liefert den Inhalt des Arrays als Zeichenfolge
        System.out.println(Arrays.toString(werte));
        // toString des Array-Objekts: liefert die Adresse, nicht den Inhalt
        System.out.println(werte/*.toString()*/);

    }
}
