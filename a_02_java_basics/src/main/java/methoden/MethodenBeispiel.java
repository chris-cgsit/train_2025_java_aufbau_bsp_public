package methoden;

public class MethodenBeispiel {

	
	
	static void ausgabe(int startWert, int endWert) {
		
		for (int i = startWert; i <= endWert; i++) {
			System.out.println("Wert: " + i);
		}
	}
	
	static void ausgabeRecursive(int startWert, int endWert) {
		System.out.println(startWert);
		if (startWert >= endWert) {
			// abbruchbedingung ist erfuellt
			return;
		}
		ausgabeRecursive(startWert + 1, endWert);
	}
	
	
	static double mult(double a, double b, double c) {
		System.out.println("mult() mit 3 parameter");
		double ergebnis = a * b * c;
		a = 7; // hat das eine auswirkung???
		// ja aber NUR fuer a innerhalb der methode!
		// keine auswirkung auf das urspünglich übergebene argument!!!
		return ergebnis;
	}
	
	static double mult(double a, double b) {
		System.out.println("mult() mit 2 parameter - double Version");
		return a * b;
	}
	
	static double mult(int a, int b) {
		System.out.println("mult() mit 2 parametern - int Version");
		return a * b;
	}
	
	public static void main(String[] args) {

		ausgabeRecursive(77, 100);
		
		double result;

		double input = 3.0;
		result = mult(input, 4, 98);
		System.out.println("input ist " + input);
		
		double erg1 = mult(4.3, 2.7); // double variante
		//double erg2 = mult(4.3); // geht nicht, da keine ueberlade version
		double erg3 = mult(5, 7); // int variante
		
		// 7 wird implizit auf double gecastet
		double erg4 = mult(5.0, 7); // double variante
		
		double erg5 = mult((int)5.0, 7); // int variante
		
		
		// ergebnis wird hier nicht weiter verwendet --> verworfen
		mult(3.5, 2.3, 7.0);
		
		
		//int x;
		// funktioniert natuerlich NICHT! liefert keinen wert zurueck
		//x = ausgabe(10, 15);
		
		ausgabe(4, 8);
		
		int start = 7;
		int end = 13;
		ausgabe(start, end);
	}
}
