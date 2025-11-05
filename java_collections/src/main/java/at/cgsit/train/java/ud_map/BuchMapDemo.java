package at.cgsit.train.java.ud_map;

import at.cgsit.train.java.objects.Buch;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstration der Verwendung einer Map<Integer, Buch> mit HashMap.
 */
public class BuchMapDemo {

    // Instanzvariable: Map mit Buch-ID als Key und Buch-Objekt als Value
    private final Map<Integer, Buch> buchMap = new HashMap<>();

    public static void main(String[] args) {
        BuchMapDemo demo = new BuchMapDemo();
        demo.runDemo();
    }

    public void runDemo() {
        // 1️: Einfügen von Büchern mit put()
        buchMap.put(1, new Buch(1, "Java Grundlagen"));
        buchMap.put(2, new Buch(2, "Datenstrukturen"));
        buchMap.put(3, new Buch(3, "Algorithmen Design"));
        buchMap.put(4, new Buch(4, "Java Collections Framework"));

        System.out.println("📚 Alle Bücher in der Map:");
        printMap();

        // 2️: Zugriff über Key (get)
        System.out.println("\nBuch mit Key 2: " + buchMap.get(2));

        // 3️: Überschreiben eines vorhandenen Eintrags
        System.out.println("\nEintrag mit Key 2 wird überschrieben:");
        buchMap.put(2, new Buch(2, "Neue Datenstrukturen (2. Auflage)"));
        printMap();

        // 4️: Prüfen, ob Key oder Value vorhanden ist
        System.out.println("\nEnthält Key 3? " + buchMap.containsKey(3));
        System.out.println("Enthält Buch mit Titel 'Java Grundlagen'? " +
                buchMap.containsValue(new Buch(1, "Java Grundlagen")));

        // 5️: Entfernen eines Eintrags
        buchMap.remove(3);
        System.out.println("\nNach Entfernen des Keys 3:");
        printMap();

        // 6️: Iteration über KeySet
        System.out.println("\nIteration über KeySet:");
        for (Integer key : buchMap.keySet()) {
            System.out.println("Key=" + key + " → " + buchMap.get(key));
        }

        // 7️: Iteration über Values
        System.out.println("\nIteration über Values:");
        for (Buch buch : buchMap.values()) {
            System.out.println("→ " + buch);
        }

        // 8️: Iteration über EntrySet
        System.out.println("\nIteration über EntrySet (Key/Value):");
        for (Map.Entry<Integer, Buch> entry : buchMap.entrySet()) {
            System.out.println("Key=" + entry.getKey() + " → " + entry.getValue());
        }

        // 9️: Map leeren
        buchMap.clear();
        System.out.println("\nMap geleert: Größe = " + buchMap.size());
    }

    private void printMap() {
        for (Map.Entry<Integer, Buch> entry : buchMap.entrySet()) {
            System.out.println("Key=" + entry.getKey() + " → " + entry.getValue());
        }
    }
}
