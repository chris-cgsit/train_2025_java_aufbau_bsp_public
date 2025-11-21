package dreiGewinntSwing;

public class TicTacToe {
	// Konstante für die Zeichen, die für die Spieler und für leere Zellen verwendet werden
	public static final char SPIELER_A = 'x', SPIELER_B = 'o', LEER = '_';
	// das Spielfeld
	final private char[][] spielfeld = new char[3][3];
	// Spieler der jeweils dran ist
	private char nextSpieler = SPIELER_A;
	// Ergebnis (wenn null ist das Spiel noch am Laufen)
	private String result;

	public TicTacToe() {
		// Spielfeld initialisieren
		for (int zeile = 0; zeile < spielfeld.length; zeile++) {
			for (int spalte = 0; spalte < spielfeld[zeile].length; spalte++) {
				spielfeld[zeile][spalte] = LEER;
			}
		}
	}

	public char getNextSpieler(){
		return nextSpieler;
	}

	public void anzeigen() {
		System.out.println("Aktueller Spielstand");
		System.out.println("  A B C");

		for (int zeile = 0; zeile < spielfeld.length; zeile++) {
			System.out.printf("%d ", zeile + 1);
			for (int spalte = 0; spalte < spielfeld[zeile].length; spalte++) {
				System.out.printf("%c ", spielfeld[zeile][spalte]);
			}
			System.out.println();
		}

		System.out.println();
	}

	/**
	 * einmal spielen: wenn das Feld nicht frei ist, wird eine Exception geworfen. Andernfalls wird
	 * das Feld besetzt und geprüft, ob es ein Ergebnis gibt. Außerdem wird der jeweils andere Spieler
	 * als nächster Spieler aktiviert
	 * @param zeile die Zeile die besetzt werden soll (Index beginnend bei 0)
	 * @param spalte die Spalte die besetzt werden soll (Index beginnend bei 0)
	 * @return
	 */
	public String spielen(int zeile, int spalte) {
		if(result != null){
			throw new IllegalStateException("Das Spiel ist schon beendet: " + result);
		}
		if (spielfeld[zeile][spalte] != LEER) {
			throw new IllegalStateException(
					String.format("Die Position %c%d ist nicht leer!", spalte + 'A', zeile + 1));
		}
		spielfeld[zeile][spalte] = nextSpieler;
		pruefen();
		nextSpieler = nextSpieler == SPIELER_A ? SPIELER_B : SPIELER_A;
		return result;
	}

	private void pruefen() {
		int count;
		boolean freieZelle = false;

		for (int i = 0; i < spielfeld.length; i++){
			for (int j = 0; j < spielfeld[i].length; j++) {
				if(spielfeld[i][j] == LEER){
					freieZelle = true;
					break;
				}
			}
		}
		if(!freieZelle){
			result = "Es gibt keine leeren Zellen mehr";
			return;
		}

		// prüfe Zeilen
		for (int i = 0; i < spielfeld.length; i++) {
			if(spielfeld[i][0] == LEER) {
				continue;
			}
			count = 1;

			for (int j = 1; j < spielfeld[i].length; j++) {
				if (spielfeld[i][0] == spielfeld[i][j]) {
					count++;
				}
			}
			if (count == 3) {
				result = String.format("Spieler %c gewinnt in Zeile %d", nextSpieler, (i + 1));
				return;
			}

		}
		for (int i = 0; i < spielfeld.length; i++) {
			if(spielfeld[0][i] == LEER) {
				continue;
			}
			count = 1;

			for (int j = 1; j < spielfeld[i].length; j++) {
				if (spielfeld[0][i] == spielfeld[j][i]) {
					count++;
				}
			}
			if (count == 3) {
				result = String.format("Spieler %c gewinnt in Spalte %c", nextSpieler, (i + 'A'));
				return;
			}

		}
		if ((spielfeld[0][0]!= LEER && spielfeld[0][0] == spielfeld[1][1] && spielfeld[0][0] == spielfeld[2][2]) ||
				(spielfeld[0][2] != LEER && spielfeld[0][2] == spielfeld[1][1] && spielfeld[1][1]== spielfeld[2][0])) {
			result = String.format("Spieler %c gewinnt in Diagonale", nextSpieler);
			return;
		}
	}

}
