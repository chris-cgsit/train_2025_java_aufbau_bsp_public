package bitoperatoren;

import java.util.Scanner;

public class BitOp003 {

  static void main() {

    // Der Linksshift-Operator: (1 << i)
    // 1 ist im int 0000 0000 0000 0001
    // << 4 wäre dann 16 mit
    // 16 ist im int 0000 0000 0001 0000
    int maskBitPos = (1 << 4);

    int value = 16;
    System.out.println("value is " + value);

    // Der Bitweise UND-Operator: (value & mask)
    // Der UND-Operator (&) vergleicht die Bits der Variable: value und unserer Maske an jeder Position:
    // Das Ergebnisbit ist nur dann 1, wenn beide Eingabebits 1 sind.
    // In allen anderen Fällen (0 & 0, 0 & 1, 1 & 0) ist das Ergebnis 0.

    // (value & (1 << i)) != 0
    // Wenn das Bit gesetzt war (Fall A), war das Ergebnis ungleich Null (z.B. 4). Die Bedingung != 0 ist wahr.
    // Wenn das Bit nicht gesetzt war (Fall B), war das Ergebnis genau Null. Die Bedingung != 0 ist falsch.

    System.out.println("welche bit postion soll gelesen werden ? ");
    Scanner s =  new Scanner(System.in);
    String s1 = s.nextLine();
    int i = Integer.parseInt(s1);
    int welchesBit = i;

    boolean isBitSet = (value & (1 << welchesBit)) != 0;
    System.out.printf("value: %s check for bit: %s : bit is set ?: %s", value , welchesBit, isBitSet);

  }

}
