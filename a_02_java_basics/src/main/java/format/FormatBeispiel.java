package format;

import java.util.Locale;
import java.util.Scanner;

public class FormatBeispiel {

	public static void main(String[] args) {
		
		int stunden = 13;
		int minuten = 4;
		
		// flags:
		// - fuer linksbuendig
		// + immer vorzeichen dazuschreiben
		// 0 ... mit fuehrenden Nullen auffuellen
		// ' ' leerzeichen fuer vorzeichen-platzhalter
		// ,  Komma fuer 1000er Punkte
		System.out.printf("%02d:%02d\n", stunden, minuten);
		
		String uhrzeit = String.format("%02d:%02d",
				stunden, minuten);
		
		System.out.println("die Uhrzeit ist " + uhrzeit);
		
		long zahl1 = -12342343;
		double temperatur = -10.5789;
		String text = "hello world";
		
		System.out.printf("* Temp: %7.1f '%-20s' %1$.3f*\n",
				temperatur, text);
		// english verwenden --> zb . statt , bei gleitkomma
		System.out.printf(Locale.ENGLISH, "%f\n", temperatur);
		
		System.out.printf("zahl: % ,d", zahl1);
		
		
		Scanner eingabe = new Scanner(System.in);
		
		// liesst eine ganze zeile ein
		// nextLine() nicht mit
		// nextInt(), next(), nextDouble(), etc. mischen.
		// Entweder nur nextLine() oder
		// nur die anderen Methoden.
		//String line = eingabe.nextLine();
		
		System.out.println("Bitte eine Zahl eingeben:");
		int number = eingabe.nextInt();
		
		System.out.println("Bitte ein beliebiges Wort eingeben:");
		String einWort = eingabe.next();
		
		System.out.println("Folgendes wurde eingegeben: " +
				number + " " + einWort);
		
		// achtung wenn System.in einmal geschlossen ist
		// kann nicht mehr davon gelesen werden!
		eingabe.close();
		
		// eingabe2 kann von System.in nicht mehr lesen
		//Scanner eingabe2 = new Scanner(System.in);
		//int x = eingabe2.nextInt();
		//System.out.println("Eingabe war " + x);
	}
}
