package zweidimensional;

import java.util.Random;

public class SymmetrischesArray {
    public static void main(String[] args) {
        // Array mit 3x4 Werten erzeugen
        int[][] zellen = new int[3][4];
        Random zufall = new Random();
        // zufällige Werte eintragen
        // äußere Schleife: für jede "Zeile" laufen lassen
        for (int i = 0; i < zellen.length; i++) {
            // innere Schleife: für jede "Spalte" laufen lassen
            for (int j = 0; j < zellen[i].length; j++) {
                int wert = zufall.nextInt(100) + (i + 1) * 100;
                // Wert in der Zelle eintragen
                zellen[i][j] = wert;
                System.out.printf("zellen[%d][%d]=%d     ", i, j, wert);
            }
            System.out.println();
        }

        // Iteration mit for-each:
        System.out.println("Alle Werte:");
        // äußere Schleife iteriert über die Sub-Arrays (int[])
        for (int[] zeile : zellen) {
            // innere Schleife iteriert über die Werte im jeweiligen Sub-Array
            for (int wert : zeile) {
                System.out.printf("%d ", wert);
            }
            // nach jeder "Zeile" ein newline
            System.out.println();
        }
    }
}
