package at.cgsit.train.java.ud_list;

import at.cgsit.train.java.objects.Buch;

import java.util.*;

/**
 * Demonstration der Verwendung einer List<Buch> mit ArrayList.
 * | Thema              | Erklärung                                                      |
 * | ------------------ | -------------------------------------------------------------- |
 * | `List`             | geordnete Sammlung, erlaubt Duplikate                          |
 * | `ArrayList`        | dynamisch wachsende Liste, schneller Zugriff per Index         |
 * | `add()`            | fügt ein Element am Ende hinzu                                 |
 * | `get(index)`       | liefert Element an Position                                    |
 * | `remove()`         | entfernt Element (nutzt `equals()` zur Identifikation)         |
 * | `sort(Comparator)` | sortiert Elemente nach beliebigem Kriterium                    |
 * | `clear()`          | löscht alle Elemente                                           |
 * | Iteration          | möglich mit `for`, `foreach`, `Iterator`, `forEach()` (Lambda) |
 *
 */
public class BuchListDemo {

    // Instanzvariable: Liste von Büchern
    private final List<Buch> buecher = new ArrayList<>();

    public static void main(String[] args) {
        // wir arbeiten objekt orientiert also erstellen wir uns selbst und rufen uns auf
        BuchListDemo demo = new BuchListDemo();
        demo.runDemo();
    }

    public void runDemo() {
        // 1: Einfügen von Büchern mittels add
        // fügt in der liste immer hinten dran ..
        buecher.add(new Buch(1, "Java Grundlagen"));
        buecher.add(new Buch(2, "Datenstrukturen"));
        buecher.add(new Buch(3, "Algorithmen Design"));
        buecher.add(new Buch(4, "Java Collections Framework"));

        System.out.println("Alle Bücher:");
        printList();

        // 2️: Zugriff über Index Position
        {
          Buch buch_Pos2 = buecher.get(2);
          System.out.println("\nBuch an Index 2: " + buch_Pos2);
        }

        // 3️: Suchen (lineare Suche)
        System.out.println("\nSuche nach 'Datenstrukturen':");
        for (Buch buch : buecher) {
            if (buch.getName().equalsIgnoreCase("Datenstrukturen")) {
                System.out.println("Gefunden: " + buch);
            }
        }

        // 4️: Sortieren mit Comparator (nach Name)
        System.out.println("\nSortiert nach Name:");
        Comparator<Buch> comparing = Comparator.comparing(Buch::getName);
        buecher.sort(comparing);
        printList();

        // 5: Sortieren nach ID (absteigend)
        System.out.println("\nSortiert nach ID (absteigend):");
        buecher.sort(Comparator.comparing(Buch::getId).reversed());
        printList();

        // 6: Löschen eines Elements
        Buch zuEntfernen = new Buch(2, "Datenstrukturen");
        buecher.remove(zuEntfernen); // funktioniert nur , wenn equals() implementiert ist
        // ansonsten kann auch die position x removed werden
        // buecher.remove(2);
        System.out.println("\nNach Entfernen von ID=2:");
        printList();

        // 7: Iteration mit Iterator
        System.out.println("\nIteration mit Iterator:");
        Iterator<Buch> it = buecher.iterator();
        while (it.hasNext()) {
            Buch buch = it.next();
            System.out.println("→ " + buch);
        }

        // 8: Liste leeren: clear löscht alle elemente
        buecher.clear();
        System.out.println("\nListe geleert: Größe = " + buecher.size());
    }

    private void printList() {
        // use "enhanced for-loop" oder "vereinfachte for-Schleife"
        // Seit Java 5 (JDK 1.5).
        for (Buch buch : buecher) {
            System.out.println("→ " + buch);
        }
    }
}
