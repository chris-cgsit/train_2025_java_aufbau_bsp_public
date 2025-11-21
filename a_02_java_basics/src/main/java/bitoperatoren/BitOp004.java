package bitoperatoren;

public class BitOp004 {

  static void main() {

    int value = 16;
    printBits(value);

  }

  /**
   * zeigt alle 32 Bits des int-Werts
   * mit iteraton über alle bits im integer
   * wir benutzen die bitmaske aus dem beispiel davor
   *
   */
  static void printBits(int value) {
    System.out.println("\nBits:");

    for (int i = 31; i >= 0; i--) {

      boolean bit = (value & (1 << i)) != 0;

      System.out.print(bit ? "1" : "0");
      // hübsche Gruppierung % modulo operator liefert hier 0 wenn durch 8 teilbar. byte grenze
      if (i % 8 == 0)
        System.out.print(" ");
    }
    System.out.println("\n(int) value = " + value);
  }


}
