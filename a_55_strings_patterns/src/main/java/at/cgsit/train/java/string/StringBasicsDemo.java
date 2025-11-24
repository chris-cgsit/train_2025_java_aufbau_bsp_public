package at.cgsit.train.java.string;

import java.time.LocalDateTime;


/**
 * The {@code StringBasicsDemo} class demonstrates the fundamental concepts and operations
 * related to strings and their manipulation in Java. It covers string literals,
 * immutability, concatenation, usage of {@code StringBuilder}, and string formatting.
 */
public class StringBasicsDemo {

    public static void main(String[] args) {
        literalsAndImmutability();
        concatenationExamples();
        stringBuilderExample();
        formattingExamples();
    }

    private static void literalsAndImmutability() {
        System.out.println("=== Literals & Immutability ===");
        String s1 = "Hallo";
        String s2 = s1;

        s1 = s1 + " Welt"; // erzeugt ein NEUES String-Objekt

        System.out.println("s1 = " + s1); // Hallo Welt
        System.out.println("s2 = " + s2); // Hallo
        System.out.println("s1 == s2 ? " + (s1 == s2));
        System.out.println();
    }

    private static void concatenationExamples() {
        System.out.println("=== String-Konkatenation mit + ===");
        String name = "Chris";
        int age = 42;

        // Compiler kann ab java 17 verwendet intern bereits Stringbuilder
        // und ist effizienter als zuvor wo hier multiple objekte erzeugt wurden.
        // wer noch in alten projekten arbeitet sollte immer StringBuilder direkt verwenden
        String msg = "Name: " + name + ", Alter: " + age;

        System.out.println(msg);
        System.out.println();
    }

  /**
   * Demonstrates the usage of {@code StringBuilder} for efficient string manipulation
   * in iterative processes, such as loops.
   *
   * - Improved perfomance mit string builder in loops
   *   object creation overhead.
   * - Dynamic appending of strings into a single {@code StringBuilder} instance.
   *
   * The method outputs the constructed log message to the console.
   *
   * StringBuffer ist synchronisiert → Thread-sicher, aber langsamer.
   * StringBuilder ist nicht synchronisiert → für Single-Thread praktisch immer die bessere Wahl.
   *   Moderne Java-APIs und Beispiele verwenden fast immer StringBuilder.
   */
  private static void stringBuilderExample() {
        System.out.println("=== StringBuilder Beispiel (performant in Schleifen) ===");
        // → für Single-Thread praktisch immer die bessere Wahl.
        StringBuilder sb = new StringBuilder();

        // In Schleifen viele Konkatenationen: lieber StringBuilder oder Streams/String.join verwenden:
        sb.append("Log-Ausgabe:\n");
        for (int i = 0; i < 5; i++) {
            sb.append("  - Zeile ").append(i)
              .append(" um ").append(LocalDateTime.now())
              .append('\n');
        }

        String log = sb.toString();
        System.out.println(log);
    }

    private static void formattingExamples() {
        System.out.println("=== String.format & printf ===");
        String name = "Laptop";
        double price = 1499.99;

        // String.format – erzeugt String
        String formatted = String.format("Produkt: %-10s | Preis: %8.2f EUR", name, price);
        System.out.println("String.format: " + formatted);

        // printf – direkt auf System.out
        System.out.printf("printf:       Produkt: %-10s | Preis: %8.2f EUR%n", name, price);

        // Format mit Datumsangaben
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("Zeit: %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS%n", now);

        System.out.println();
    }
}
