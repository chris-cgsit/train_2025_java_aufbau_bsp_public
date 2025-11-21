package at.cgsit.java.arrays.mit.lotto;

import java.util.Arrays;
import java.util.Random;

public class Lottotipp {
    // statt einzelner Werte
    // private int zahl1, zahl2; // ...
    // ein Array für die 6 Lottozahlen erstellen
    private int[] tippZahlen = new int[6];



    public int[] getTippZahlen() {
        // nicht das Attribut (die Referenz) selber zurückliefern,
        // return tippZahlen;
        // sondern eine Kopie
        return Arrays.copyOf(tippZahlen, tippZahlen.length);
    }

    public void quickTipp() {
        Random random = new Random();
        // Achtung: die Initialisierung ist nicht richtig! Zahlen können mehrfach vorkommen
        for (int i = 0; i < tippZahlen.length; i++) {
            int wert = random.nextInt(45) + 1;
            // TODO: Zahl nur eintragen, wenn sie noch nicht vorkommt
            tippZahlen[i] = wert;
        }

        Arrays.sort(tippZahlen);
    }

    public void manuellerTipp() {
        // TODO: 6 unterschiedliche Zahlen vom Benutzer einlesen
        System.out.println("Manueller Tipp ist noch nicht implementiert");
    }

    public void anzeigen() {
        for (int zahl : tippZahlen) {
            System.out.print(zahl + " ");
        }
        System.out.println();
    }

}
