package helloworld;

public class HelloWorld {

	public static void main(String[] args) {
		
		//<type> <namen/identifer> [= <init-wert>];
		boolean istSchoenesWetter = false;
		
		char einZeichen = 'W';
		
		short eineZahl = 1277;
		int num = 1234334;
		short test = 123;
		
		// bei expliziten cast ueberlegen ob sich das
		// mit dem wert ausgeht
		eineZahl = (short) num; // expliziter cast auf short
		
		// impliziter cast
		num = test;
		
		// ein literal fuer float muss ein f beinhalten
		// bei einer kommazahl
		float temperatur = 13.5f;
		
		double temp1 = 7.3, temp2;
		
		// impliziter cast von float auf double kein problem
		temp2 = temperatur;
		
		int nr1 = 5;
		int nr2 = 2;
		double ergebnis = (double)nr1 / (double)nr2;
		// L fuer long literal
		long grosseZahl = 123_462_783_468_732L;
		
		char zeichen2 = '\'';
		System.out.printf("erg: %8.6f\n", ergebnis);
		
		String einText = """
				asdfasdf das ist ein "wort" in hochkomma
				saDFJKSADF\
				ASDFSDAF""";
		System.out.println(einText);
		System.out.println("Hello' World " +
				istSchoenesWetter + " zahl: " + eineZahl);
		
		// variable per typinferenz
		var test1 = grosseZahl;
		var test2 = "test2 ist ein string";
		
		// kann einmal initialisiert werden und dann
		// nicht mehr veraendern.
		final int ANZAHL = 5;
		//ANZAHL++; // kann ich nicht veraendern
		
		System.out.println(test1 + " " + test2 + " " + ANZAHL);
	}

}
