
## HashCode, Equals und Hash-basiertes Speichern

```java
class Hund {
    private final String name;

    public Hund(String name) { this.name = name; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Hund h)) return false;
        return Objects.equals(name, h.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Hund[" + name + "]";
    }
}

public class Demo {
    public static void main(String[] args) {
        Set<Hund> set = new HashSet<>();
        set.add(new Hund("Bello"));
        set.add(new Hund("Bello")); // gleiche Logik, kein Duplikat
        System.out.println(set);
    }
}
````
#### 1 Beide Objekte werden zunächst erzeugt
- new Hund("Bello") ruft zweimal den Konstruktor auf → zwei verschiedene Speicheradressen (also zwei Instanzen auf dem Heap).
- Der zweite Hund wird nicht automatisch erkannt, sondern erst beim Einfügen in die Collection verglichen.

#### 2 HashSet prüft:
- Berechnet hashCode() → findet Bucket anhand des Hashwertes.
- Ruft für Objekte im gleichen Bucket equals() auf, um inhaltliche Gleichheit zu prüfen.
- Da equals() bei gleichem Namen "Bello" → true ergibt,
- erkennt HashSet: „dieses Objekt gibt es logisch schon“.

#### Das zweite Objekt wird nicht eingefügalso kein zweiter Eintrag in der internen Hash-Struktur.

#### 3 Was passiert mit dem Objekt im Speicher?
- Der zweite Hund wurde nie im Set gespeichert (keine Referenz aufbewahrt).
- Nach Beendigung der add()-Methode gibt es keine aktive Referenz mehr auf dieses Objekt.
- Damit wird es vom Garbage Collector freigegeben, sobald die nächste GC-Runde läuft.

* Hash-basierte Datenstrukturen (`HashMap`, `HashSet`, `ConcurrentHashMap`)
  verwenden `hashCode()` zum **Lokalisieren** und `equals()` zum **Vergleichen** von Objekten.
* Regeln:

    * Wenn `equals()` true → `hashCode()` muss gleich sein.
    * Wenn `equals()` false → `hashCode()` darf gleich sein (Kollision möglich, aber ineffizient).
* Verletzung dieser Regeln führt zu **„Geistereinträgen“** oder doppelten Objekten im Set.

::: notes

* Trainer: Live-Demo mit `HashSet` – einmal mit und einmal ohne korrekt überschriebenem `equals()`/`hashCode()`.
* Zeige in IDE: Breakpoint in `hashCode()` → Aufruf beim Hinzufügen in HashSet.
* Diskutiere: Warum sich `hashCode()` auf unveränderliche Felder beziehen sollte (Immutability!).
* Bonus: Beispiel mit `record` zeigt automatische, korrekte Implementierung.
  :::

```
