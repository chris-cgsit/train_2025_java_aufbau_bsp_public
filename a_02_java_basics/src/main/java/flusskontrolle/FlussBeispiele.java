package flusskontrolle;

import java.util.Scanner;

public class FlussBeispiele {

	public static void main(String[] args) {
		
		
		// if bsp
		int a = 15;
		int b = 7;
		
		if (a < b) {
			System.out.println("a kleiner b ist wahr");
			System.out.println("es ist wirklich kleiner!");
		}
		
		Scanner eingabe = new Scanner(System.in);
		System.out.print("Note bitte eingeben (1 - 5):");
		int note = eingabe.nextInt();
		
		String noteAlsText;
		// switch anweisung
		// klassische switch variante
//		switch (note) {
//		case 1:
//			noteAlsText = "Sehr gut";
//			break;
//		case 2:
//			noteAlsText = "Gut";
//			break;
//		case 3:
//			noteAlsText = "Befriedigend";
//			break;
//		case 4:
//			noteAlsText = "Genügend";
//			break;
//		case 5:
//			noteAlsText = "Nicht genügend";
//			break;
//		default:
//			noteAlsText = "unbekannt";
//			break;
//		}


		// switch anweisung
//		// kompakte neue variante ab Java 14
//		switch (note) {
//		case 1 -> noteAlsText = "Sehr gut";
//		case 2 -> noteAlsText = "Gut";
//		case 3 -> noteAlsText = "Befriedigend";
//		case 4 -> noteAlsText = "Genügend";
//		case 5 -> noteAlsText = "Nicht genügend";
//		default -> noteAlsText = "unbekannt";
//		}

		// switch ausdruck. Achtung! benoetigt zum Schluss ein ;
		// kompakte neue variante ab Java 14
		noteAlsText = switch (note) {
		case 1 -> "Sehr gut";
		case 2 -> "Gut";
		case 3 -> "Befriedigend";
		case 4 -> "Genügend";
		case 5 -> "Nicht genügend";
		default -> "unbekannt";
		}; // HIER ; notwendig!

		System.out.println(noteAlsText);
		
		int [] numbers = new int [10];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = (int)(Math.random() * 100.0);
			
			if (i == 3) {
				continue;
			}
			
			System.out.println(i + ": " + numbers[i]);
		}
		
		System.out.println("Ausgabe:");
		for (int element : numbers) {
			System.out.println(element);
			element = 7; // keinen einfluss auf das element im array!!!!
		}
		
		xschleife: // bezeichnung fuer die schleife
		for (int x = 0; x < 3; ++x) {
			
			yschleife: // bezeichnung fuer die innere schleife
			for (int y = 0; y < 4; ++y) {
				System.out.println(x + " * " + y + " = " + (x * y));
				if (y == 2) {
					// mit hilfe von bezeichnungen kann man
					// mehrere ebenen raus springen
					break xschleife;
				}
			}
		}
		
	}

}
