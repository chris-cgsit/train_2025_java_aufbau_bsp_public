package at.cgsit.train.java.ud_set;

import at.cgsit.train.java.objects.Buch;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Demonstration der Verwendung eines Set<Buch> mit HashSet.
 *
 | Thema        | Erklärung                                                       |
 | ------------ | --------------------------------------------------------------- |
 | `Set`        | ungeordnete Sammlung, **keine Duplikate**                       |
 | `HashSet`    | nutzt `hashCode()` und `equals()` zum Erkennen gleicher Objekte |
 | `add()`      | fügt Element hinzu, falls es noch nicht existiert               |
 | `contains()` | prüft Existenz über `equals()`                                  |
 | `remove()`   | entfernt Element                                                |
 | `clear()`    | leert das Set                                                   |
 | Iteration    | über `for-each` oder `Iterator` möglich                         |
 *
 */
public class BuchSetDemo {

    // Instanzvariable: Set von Büchern
    private final Set<Buch> buchSet = new HashSet<>();

    public static void main(String[] args) {
        BuchSetDemo demo = new BuchSetDemo();
        demo.runDemo();
    }

    public void runDemo() {
        // 1️: Einfügen von Büchern
        buchSet.add(new Buch(1, "Java Grundlagen"));
        buchSet.add(new Buch(2, "Datenstrukturen"));
        buchSet.add(new Buch(3, "Algorithmen Design"));
        buchSet.add(new Buch(4, "Java Collections Framework"));

        System.out.println("Alle Bücher im Set:");
        printSet();

        // 2️: Versuch, ein Duplikat einzufügen
        System.out.println("\nVersuch, ein Duplikat hinzuzufügen:");
        boolean added = buchSet.add(new Buch(1, "Java Grundlagen"));
        System.out.println("Ergebnis: " + (added ? "hinzugefügt" : "bereits vorhanden"));

        printSet();

        // 3️: Prüfen, ob ein Buch enthalten ist
        Buch suchBuch = new Buch(3, "Algorithmen Design");
        System.out.println("\nEnthält Set Buch mit ID=3? " + buchSet.contains(suchBuch));

        // 4️: Entfernen eines Buchs
        buchSet.remove(suchBuch);
        System.out.println("\nNach Entfernen von 'Algorithmen Design':");
        printSet();

        // 5️: Iteration mit for-each
        System.out.println("\nIteration mit for-each:");
        for (Buch buch : buchSet) {
            System.out.println("→ " + buch);
        }

        // 6️: Iteration mit Iterator
        System.out.println("\nIteration mit Iterator:");
        Iterator<Buch> it = buchSet.iterator();
        while (it.hasNext()) {
            Buch buch = it.next();
            System.out.println("→ " + buch);
        }

        // 7️: Set leeren
        buchSet.clear();
        System.out.println("\nSet geleert: Größe = " + buchSet.size());
    }

    private void printSet() {
        for (Buch buch : buchSet) {
            System.out.println("→ " + buch);
        }
    }
}
