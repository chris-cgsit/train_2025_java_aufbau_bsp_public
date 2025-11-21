package at.cgsit.java.arrays.mit.eindimensional;

import java.util.Random;

public class Niederschlaege {

	public static void main(String[] args) {
		// Array-Objekt mit 12 Werten erzeugen
		int[] werte = new int[12];
		

		// Zufallszahlen eintragen
		// Generator für Zufallszahlen
		Random zufall = new Random();
		for (int i = 0; i < werte.length; i++) {
			int zufallsWert = zufall.nextInt(50); // Wert zwischen 0 und 49
			werte[i] = zufallsWert + 20 ; // Werte zwischen 20 und 69 
		}
		System.out.println("Niederschlagsmengen in mm (zufällig ermittelt):");
		int summe = 0;
		// for each wert (vom Typ double) im Array Werte
		// for each wert in werte
		for (int wert : werte) {
			System.out.print(wert + " ");
			// aufsummieren
			summe += wert;
		}
		
		double durschnitt = summe / (double) werte.length;
		System.out.println("Durchschnitt: " + durschnitt);		

		// Monat mit der niedrigsten und mit der höchsten Niederschlagsmenge finden
		int iMin = 0, iMax = 0;

		for (int i = 1; i < werte.length; i++) {
			// wenn der aktuelle Wert kleiner ist als das bisherige Minimum
			if (werte[i] < werte[iMin]) {
				// den Index merken
				iMin = i;
			}

			// wenn der aktuelle Wert größer ist als das bisherige Maximum
			if (werte[i] > werte[iMax]) {
				// den Index merken
				iMax = i;
			}
		}
		
		System.out.printf("Das Minimum ist im Monat %d: %d \n", iMin + 1, werte[iMin]);
		System.out.printf("Das Maximum ist im Monat %d: %d \n", iMax + 1, werte[iMax]);

		
		String[] monatsNamen = /*new String[]*/ {
				"Jänner", "Februar", "März", "April", "Mai", "Juni", "Juli",
				"August", "September", "Oktober", "November", "Dezember"
		};
		
		System.out.printf("Das Minimum ist im %s: %d \n", monatsNamen[iMin], werte[iMin]);
		System.out.printf("Das Maximum ist im %s: %d \n", monatsNamen[iMax], werte[iMax]);
	}

}
