package operatoren;

public class OperatorenBeispiel {

	public static void main(String[] args) {
		System.out.println("Operatoren");

		int a1 = 7;
		int a2 = 7;
		int a3 = 7;
		int a4 = 7;
		
		a1 = a1 + 1;
		a1++;
		a1 += 1; // kurze schreibweise fuer a1 = a1 + 1;
		++a1;
		
		// mehrfachzuweisung
		int b2 = a2 += 1;
		System.out.println("wert von b2: " + b2);

		int b3 = ++a3;
		System.out.println("wert von b3: " + b3);
		
		int b4 = a4++;
		System.out.println("wert von b4: " + b4);
	}
}
