package bitoperatoren;

public class BitOp002 {

  static void main() {
    // 0001 0110   (dezimal: 22)
    byte value = 22;

    /*
     x << n
     Alle Bits werden n Positionen nach links geschoben
     Rechts kommen Nullen rein
     Zahlenwert verdoppelt sich pro Verschiebung
     */
   byte shifted = (byte)(value << 1);
   // java bit shift muss ge-casted werden da int das ergebnis der bitshift oopeation ist
   /*
      ACHTUNG::
      Die Bit-Shift-Operatoren (<<, >>, >>>)
      in Java führen eine automatische Typumwandlung (Promotion) der Operanden zu einem int oder long durch.
    */

   /*
     0001 0110   (22)
     1 nach links
     0010 1100   (44)
    */
    System.out.printf("byte shifted: %s \n", shifted);

    // das ergebnis ist 44.
    // der wert jeder positon wird also um den faktor 2 erhöht
    // MERKE! :
    // Jeder Links-shift um 1 entspricht einer Multiplikation mit 2.
    //x << n entspricht x * 2^n.

    /*
        Warum benutzen wir << zum Bitmasken-Bauen?
        Weil wir damit ein einzelnes Bit an eine exakt definierte Position setzen:
        1 << 3   → 0000 1000  (Bit Nummer 3 an)
        1 << 0   → 0000 0001  (Bit 0)
        1 << 8   → 0001 0000 0000
     */

    // x >> n  bits werden nach rechts verschoben
    // Die Operation >> 1 ist eine sehr effiziente Methode, um eine Ganzzahl in Java durch 2 zu teilen (bei positiven Zahlen).
    shifted = (byte) (shifted >> 1);
    System.out.printf("byte shifted right: %s \n", shifted);

  }

}



