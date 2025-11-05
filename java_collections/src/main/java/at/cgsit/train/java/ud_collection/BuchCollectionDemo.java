package at.cgsit.train.java.ud_collection;

import at.cgsit.train.java.objects.Buch;

import java.util.*;

/**
 * Demonstration der Collection-Schnittstelle mit unterschiedlichen Implementierungen.
 * Zeigt, dass List, Set etc. gemeinsame Methoden haben, sich aber unterschiedlich verhalten.
 */
public class BuchCollectionDemo {

    public static void main(String[] args) {
        BuchCollectionDemo demo = new BuchCollectionDemo();

        System.out.println("=== Beispiel mit ArrayList ===");
        demo.runDemo(new ArrayList<>());

        System.out.println("\n=== Beispiel mit HashSet ===");
        demo.runDemo(new HashSet<>());

        System.out.println("\n=== Beispiel mit TreeSet (sortiert nach Name) ===");
        demo.runDemo(new TreeSet<>(Comparator.comparing(Buch::getName)));
    }

    /**
     * Führt die gleichen Operationen auf einer beliebigen Collection durch.
     */
    public void runDemo(Collection<Buch> collection) {
        // 1️⃣ Einfügen
        collection.add(new Buch(1, "Java Grundlagen"));
        collection.add(new Buch(2, "Datenstrukturen"));
        collection.add(new Buch(3, "Algorithmen Design"));
        collection.add(new Buch(4, "Java Collections Framework"));

        // Duplikat hinzufügen
        collection.add(new Buch(1, "Java Grundlagen"));

        System.out.println("📚 Inhalt nach add():");
        printCollection(collection);

        // 2️⃣ contains() → funktioniert bei allen Collections
        Buch suchBuch = new Buch(2, "Datenstrukturen");
        System.out.println("\nEnthält 'Datenstrukturen'? " + collection.contains(suchBuch));

        // 3️⃣ remove() → funktioniert bei allen Collections
        collection.remove(suchBuch);
        System.out.println("\nNach remove('Datenstrukturen'):");
        printCollection(collection);

        // 4️⃣ Iteration (gemeinsames Interface)
        System.out.println("\nIteration über forEach():");
        collection.forEach(b -> System.out.println("→ " + b));

        // 5️⃣ Größe & clear()
        System.out.println("\nGröße: " + collection.size());
        collection.clear();
        System.out.println("Nach clear(): Größe = " + collection.size());
    }

    private void printCollection(Collection<Buch> c) {
        for (Buch buch : c) {
            System.out.println("→ " + buch);
        }
    }
}
