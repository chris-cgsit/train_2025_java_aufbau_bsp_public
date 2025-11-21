package at.cgsit.train.java.ud_hash_collision;

import at.cgsit.train.java.objects.Buch;

import java.util.HashSet;
import java.util.Set;

/**
 * Demonstration der Verwendung eines Set<Buch> mit HashSet.
 * Zeigt auch, dass mehrere verschiedene Objekte denselben hashCode haben dürfen.
 */
public class BuchSetDemo_HashKollision {

    public static void main(String[] args) {
        new BuchSetDemo_HashKollision().runDemo();
    }

    public void runDemo() {
        Set<Buch> set = new HashSet<>();

        Buch buch1 = new Buch(1, "Java Grundlagen");
        Buch buch2 = new Buch(2, "Python Grundlagen");
        Buch buch3 = new Buch(3, "Java Grundlagen – Erweiterung");

        set.add(buch1);
        set.add(buch2);
        set.add(buch3);

        System.out.println("📚 Set-Inhalt (verschiedene Objekte, evtl. gleicher Hash):");
        set.forEach(b -> System.out.printf("→ %-35s | hash=%d%n", b.getName(), b.hashCode()));

        System.out.println("\n💡 Beachte:");
        System.out.println("Zwei verschiedene Objekte dürfen denselben hashCode haben,");
        System.out.println("solange equals() unterschiedlich ist (also verschiedene Objekte).");
        System.out.println("Das Set prüft nach gleichem hashCode zusätzlich mit equals().");

        //  Beispielhafte Demonstration:
        System.out.println("\n📊 Hash-Kollision erzwingen (manuell manipuliert):");
        Set<BuchMitFixemHash> setMitKollision = new HashSet<>();
        setMitKollision.add(new BuchMitFixemHash(1, "A"));
        setMitKollision.add(new BuchMitFixemHash(2, "B"));
        setMitKollision.add(new BuchMitFixemHash(3, "C"));

        for (BuchMitFixemHash b : setMitKollision) {
            System.out.printf("→ %-2s | hash=%d%n", b.getName(), b.hashCode());
        }

        System.out.println("\n👉 Obwohl alle denselben Hashwert haben, sind sie im Set vorhanden,");
        System.out.println("weil equals() sie unterscheidet.");
    }

    /**
     * Hilfsklasse für die Demonstration von Hash-Kollisionen.
     * Alle Objekte liefern denselben hashCode, equals() unterscheidet sie aber.
     */
    static class BuchMitFixemHash {
        private final int id;
        private final String name;

        BuchMitFixemHash(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return 42; // absichtlich gleich für alle Objekte
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof BuchMitFixemHash other)) return false;
            return id == other.id && name.equals(other.name);
        }

        @Override
        public String toString() {
            return name;
        }

        public String getName() {
            return name;
        }
    }
}
