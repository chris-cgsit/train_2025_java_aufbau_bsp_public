package at.cgsit.train.functional_zwei;

import at.cgsit.train.java.objects.Buch;

public class BuchFilterFktZweiMain {

  static void main() {

    // Verwendung (anonyme Klasse – alte Schreibweise)
    BuchVergleichInterface vergleichAlt = new BuchVergleichInterface() {
      @Override
      public boolean isSame(Buch b1, Buch b2) {
        return b1.getName().equals(b2.getName());
      }
    };

    // lambda mit impl klammern
    BuchVergleichInterface vergleich2 = (b1, b2) -> {
      System.out.println(b1.getName() + ", " + b2);
      return b1.getName().equals(b2.getName());
    };

    // Lambda
    BuchVergleichInterface vergleich = (b1, b2) -> b1.getName().equals(b2.getName());

    // Functional Interface = genau eine abstrakte Methode
    // zwei Parametern → (a, b) -> …
    // gut für Vergleiche wie ::  Sind die zwei Objekte gleich?

  }

}
