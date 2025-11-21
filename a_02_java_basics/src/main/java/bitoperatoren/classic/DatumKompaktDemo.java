package bitoperatoren.classic;

public class DatumKompaktDemo {

	public static void main(String[] args) {
		DatumKompakt datum = new DatumKompakt(15, 3, 2021);
		// toString wird implizit aufgerufen, wenn ein Objekt
		// an der Konsole angezeigt werden soll
		System.out.println(datum/*.toString()*/);
		
		datum = new DatumKompakt(29, 11, 2099);
		System.out.println(datum/*.toString()*/);
		
		// 1100011101111101

	}

}
