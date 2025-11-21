package bitoperatoren;

public class BitOp001 {

  static void main() {

    // ein int hat 32 bit und ein short 16 bit ein byte 8 bits
    // ein byte wäre also  0000 0000 bits
    // 0001 0110   (dezimal: 22)
    // bei einer zahl hat das erste bit von rechts nach links jeweils 2 hoch x bedeutung
    // beginnend mit 2^0 2 hoch null wäre 1, 2 hoch 1 wäre 2,
    // 2 hoch 2 wäre 4
    // so ergibt sich der wert jedes bits bei einer zahl von rechts nach links
    // 1,2,4,8,16,32,64 ...
    // wenn so ein bit gesetzt ist hat es den wert z.b. 16.
    // die summe aller werte ergibt die zahl.
    // ein bit ist gesetzt wenn es 1 ist sonst null.
    // <br>
    // 0001 0110   (dezimal: 22)
    // hier ergibt sich also
    // Die Berechnung erfolgt wie folgt:
    // (0 * 2^7) + (0 * 2^6) + (0 * 2^5) + (1 * 2^4) + (0 * 2^3) + (1 * 2^2) + (1 * 2^1) + (0 * 2^0)
    // Ausgeschrieben als Summe der Beiträge:
    // 0 (Bit 8) + 0 (Bit 7) + 0 (Bit 6) + 16 (Bit 5) + 0 (Bit 4) + 4 (Bit 3) + 2 (Bit 2) + 0 (Bit 1)
    // das ergibt: 16 + 4 + 2 + 0
    // Ergibt den Dezimalwert:
    // 22
    byte value = 22;


  }

}
