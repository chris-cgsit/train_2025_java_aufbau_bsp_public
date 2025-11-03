## **CGS_Java_Collections_Master.md**

# Java Collection Framework (CGS IT Training)

## 1. Einführung

- Das **Java Collections Framework (JCF)** ist eine einheitliche API zum Speichern, Verwalten und Verarbeiten von Gruppen von Objekten.
- Es ersetzt Arrays, wenn flexible, dynamische Datenstrukturen benötigt werden.
- Hauptziele:
  - Einheitliche Schnittstellen (`List`, `Set`, `Map`)
  - Generische, typsichere Klassen
  - Hohe Performance und flexible Implementierungen
  - Integration mit Streams API

**Wichtige Pakete:**
- `java.util`
- `java.util.concurrent`
- `java.util.stream`

### Trainerhinweis:
Frage ins Publikum: *Wann verwendet ihr noch Arrays?*  
→ Einstieg in den Unterschied zwischen fixen und dynamischen Datenstrukturen.

---

## 2. Architektur & Interfaces

Hierarchie des JCF:

```

Collection
├─ List
├─ Set
└─ Queue
Map (eigene Hierarchie)

````

- `Collection` ist die Wurzel der hierarchischen API.
- `Map` ist kein `Collection`, bildet aber eine eigene Struktur (Key/Value).

### Trainerhinweis:
Skizziere an der Tafel den Unterschied zwischen `Collection` und `Map`.  
Hinweis: `Iterable` ist das Superinterface aller Collections.

---

## 3. Listen (List)

Listen speichern Elemente in einer definierten Reihenfolge, ermöglichen Duplikate.

### Hauptimplementierungen:
- `ArrayList` – dynamisches Array, schnelle Random Access.
- `LinkedList` – doppelt verkettete Liste, effizient beim Einfügen/Löschen.

### Beispiele:

```java
List<String> namen = new ArrayList<>();
namen.add("Anna");
namen.add("Bob");
namen.add("Anna"); // erlaubt

for (String n : namen) {
    System.out.println(n);
}
````

### Moderne Initialisierung:

```java
List<String> namen = List.of("Anna", "Bob", "Chris"); // unveränderlich
```

### Trainerhinweis:

Zeige Unterschied zwischen `new ArrayList<>(List.of(...))` (kopierbar) und `List.of()` (unmodifiable).
Frage: *Was passiert, wenn man bei `List.of()` ein Element hinzufügen will?*

---

## 4. Mengen (Set)

Sets speichern **keine Duplikate**, Reihenfolge ist nicht garantiert.

### Hauptimplementierungen:

* `HashSet` – basiert auf HashCode/equals (schnell, keine Ordnung)
* `LinkedHashSet` – behält Einfügereihenfolge
* `TreeSet` – sortierte Werte (natürliche Ordnung oder Comparator)
* `EnumSet` – optimierte Variante für Enums

```java
Set<String> tiere = new HashSet<>();
tiere.add("Hund");
tiere.add("Katze");
tiere.add("Hund"); // kein zweiter Eintrag

System.out.println(tiere);
```

### Trainerhinweis:

Erkläre, warum `equals()` und `hashCode()` entscheidend für Sets sind.
Verweise auf Folie aus Kapitel „Object & HashCode“.

---

## 5. Maps

Maps speichern **Key–Value-Paare**.

### Hauptimplementierungen:

* `HashMap` – Standard, schnelle Hash-basierte Struktur
* `LinkedHashMap` – behält Einfügereihenfolge
* `TreeMap` – sortiert nach Schlüssel
* `ConcurrentHashMap` – threadsafe Variante

```java
Map<Integer, String> personen = new HashMap<>();
personen.put(1, "Alice");
personen.put(2, "Bob");
personen.put(1, "Charlie"); // überschreibt Wert

System.out.println(personen.get(1)); // Charlie
```

### Moderne Varianten:

```java
Map<Integer, String> map = Map.of(1, "A", 2, "B", 3, "C");
Map<Integer, String> kopie = Map.copyOf(map);
```

### Trainerhinweis:

Live-Demo: `HashMap` mit Key-Kollision (`equals()`/`hashCode()` erklären).
Diskussion: Warum ist `TreeMap` langsamer, aber sortiert?

---

## 6. Queue und Deque

* **Queue** = FIFO (First In First Out)
* **Deque** = Double Ended Queue (beidseitig)

### Implementierungen:

* `ArrayDeque` – schneller Stack/Queue-Ersatz
* `PriorityQueue` – sortiert nach Priorität (natürliche Ordnung oder Comparator)

```java
Queue<String> warteschlange = new ArrayDeque<>();
warteschlange.offer("A");
warteschlange.offer("B");
System.out.println(warteschlange.poll()); // A
```

### Trainerhinweis:

Erkläre Unterschied zu `Stack` (veraltet).
Tipp: Demo mit `PriorityQueue` und Comparator.

---

## 7. Iteration & Streams

```java
List<String> namen = List.of("Anna", "Bob", "Chris");

for (String n : namen) System.out.println(n);

namen.forEach(System.out::println); // moderne Syntax

long count = namen.stream()
                  .filter(n -> n.startsWith("A"))
                  .count();
```

* Ab Java 8: `forEach`, `stream()`, `parallelStream()`
* Ab Java 16: `Stream.toList()` liefert unveränderliche Liste
* Ab Java 25: erweiterte `SequencedCollection`-API mit `reversed()` u.a.

### Trainerhinweis:

Live-Vergleich: klassische Schleife vs. Stream.
Erkläre Lazy Evaluation im Stream.

---

## 8. Unmodifiable & Immutable Collections

```java
List<String> list = List.of("A", "B", "C"); // unveränderlich
List<String> copy = List.copyOf(list);
```

* **Unmodifiable** = nicht veränderbar, aber ggf. abhängig von Original.
* **Immutable** = komplett eigenständig und unveränderlich.
* Methoden: `List.of()`, `Map.of()`, `Set.copyOf()`

### Trainerhinweis:

Demo: `UnsupportedOperationException` beim `add()` in `List.of()`.
Diskussion: *Wann sind unveränderliche Collections sinnvoll?*

---

## 9. Generics im Collection Framework

```java
List<Integer> zahlen = new ArrayList<>();
zahlen.add(10);
zahlen.add(20);
```

* Generics machen Collections **typsicher**.
* Compiler prüft Typen bereits zur Compilezeit.
* Keine Casts bei Zugriffen nötig.

### Wildcards

```java
void drucke(List<? extends Number> liste) { ... }
```

* `? extends` → Lesen erlaubt, Schreiben nicht (Producer).
* `? super` → Schreiben erlaubt (Consumer).

### Trainerhinweis:

Erkläre **PECS** (Producer Extends, Consumer Super).
Zeige, warum `List<Object>` ≠ `List<String>`.

---

## 10. Comparator und Comparable

```java
record Person(String name, int alter) implements Comparable<Person> {
    @Override
    public int compareTo(Person p) {
        return this.alter - p.alter;
    }
}
```

* **Comparable** → natürliche Reihenfolge
* **Comparator** → benutzerdefinierte Sortierung

```java
List<Person> personen = new ArrayList<>();
personen.sort(Comparator.comparing(Person::name));
```

### Trainerhinweis:

Frage: *Wann verwende ich Comparator statt Comparable?*
Zeige `Comparator.thenComparing()` und `reversed()`.

---

## 11. Threadsafe und Concurrent Collections

### Beispiele:

* `ConcurrentHashMap`
* `CopyOnWriteArrayList`
* `BlockingQueue`, `ConcurrentLinkedQueue`

```java
Map<String, Integer> counter = new ConcurrentHashMap<>();
counter.put("A", 1);
counter.merge("A", 1, Integer::sum);
```

### Trainerhinweis:

Live-Demo mit zwei Threads.
Zeige Unterschied zu `Collections.synchronizedMap()`.

---

## 12. Typische Fehler und Pitfalls

* `ConcurrentModificationException` beim gleichzeitigen Ändern einer Collection während Iteration.
* `UnsupportedOperationException` bei `List.of()`-Listen.
* Verwendung von `==` statt `equals()`.
* Fehlende Implementierung von `hashCode()` und `equals()` in Keys.
* Autoboxing-Overhead in großen Strukturen.

### Trainerhinweis:

Frage: *Was passiert, wenn ich aus einer List während Iteration entferne?*
Erkläre Fail-Fast-Iterator und `Iterator.remove()`.

---

## 13. Moderne Features (Java 21 – 25)

* `Stream.toList()` → unmodifiable
* `SequencedCollection`, `SequencedMap` (API-Erweiterung Java 21)
* `record` als Key für Map möglich (immutable, korrektes equals/hashCode)
* Pattern Matching für `instanceof` in Streams
* `Collectors.filtering`, `Collectors.flatMapping`

```java
var namen = Stream.of("Anna", "Bob", "Alex")
                  .filter(n -> n.startsWith("A"))
                  .toList();
```

### Trainerhinweis:

Zeige Beispiel mit `record` als Key in einer Map.
Erkläre kurz Vorteil von Sequenced-APIs.

---

## 14. Zusammenfassung

* Einheitliche API für Datenstrukturen
* Collections sind Generics, typsicher und flexibel
* Moderne Syntax: `List.of()`, `Stream.toList()`, `record`
* Immutability und Thread-Safety als Designziele
* Achtung bei Autoboxing und Fail-Fast-Iteratoren

### Trainerhinweis:

Schließe mit Quizfragen:

1. Wann `HashSet` vs. `TreeSet`?
2. Warum ist `ConcurrentHashMap` schneller als Synchronisierung?
3. Was ist der Unterschied zwischen `List.copyOf()` und `new ArrayList<>(...)`?

```
