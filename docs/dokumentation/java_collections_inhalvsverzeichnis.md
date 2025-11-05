
## 📘 **Inhaltsverzeichnis – Java Collection Framework (CGS IT)**

*(Stand: Java 25 LTS, aktualisiert für Schulung & Praxis)*

---

### 1️⃣ **Einführung in das Java Collections Framework**

* Was sind Collections?
* Vorteile und Ziel des JCF
* Pakete: `java.util`, `java.util.concurrent`, `java.util.stream`
* Überblick über Hauptschnittstellen

---

### 2️⃣ **Architektur & Interfaces**

* Hierarchie: `Collection`, `List`, `Set`, `Queue`, `Map`
* Unterschiede zwischen `Collection` und `Map`
* `Iterable` und das `Iterator`-Interface
* ➕ **Iterator-Folie:** `hasNext()`, `next()`, `remove()`

---

### 3️⃣ **Listen (List)**

* Eigenschaften: Reihenfolge, Duplikate erlaubt
* Implementierungen:

    * `ArrayList` – schneller Zugriff
    * `LinkedList` – schnelles Einfügen/Löschen
    * `Vector` (veraltet)
* Unveränderliche Listen (`List.of()`, `List.copyOf()`)
* Beispiele mit `forEach()` und Streams

---

### 4️⃣ **Mengen (Set)**

* Eigenschaften: keine Duplikate
* Implementierungen:

    * `HashSet`, `LinkedHashSet`, `TreeSet`, `EnumSet`
* Sortierung und Vergleich (`Comparable`, `Comparator`)
* ➕ **Baumdarstellung & Vergleich TreeSet**

---

### 5️⃣ **Zuordnungen (Map)**

* Key-Value-Prinzip
* Implementierungen:

    * `HashMap`, `LinkedHashMap`, `TreeMap`, `ConcurrentHashMap`
    * (Legacy: `Hashtable`)
* Unterschiede in `null`-Behandlung
* Methoden: `put`, `get`, `containsKey`, `entrySet()`
* ➕ **hashCode() / equals() richtig implementieren**

---

### 6️⃣ **Queues & Deques**

* FIFO-Prinzip (Queue)

    * Beispiel: `ArrayDeque`, `offer()`, `poll()`, `peek()`
* LIFO-Prinzip (Stack-Ersatz mit Deque)

    * Beispiel: `push()`, `pop()`, `peek()`
* Vergleich: Queue vs. Deque
* ➕ **Zusatz: BlockingQueue, ConcurrentLinkedQueue**

---

### 7️⃣ **Tree-basierte Collections**

* Internes Prinzip: Binärer Suchbaum
* Sortierung durch `Comparable` oder `Comparator`
* Diagramm: Binärer Suchbaum (CGS-Grafik in #749DC6)
* **Komplexität:** O(log n) – was bedeutet das?

---

### 8️⃣ **Eigenschaften der wichtigsten Implementierungen**

* Übersichtstabelle: Ordnung, Duplikate, Sortierung, `null`-Erlaubnis, Threadsafety
* ➕ Trainerhinweis zu `null`-Keys in Maps
* Legacy-Collections: `Vector`, `Stack`, `Hashtable`

---

### 9️⃣ **Generics & Typsicherheit**

* Vorteile von Generics im JCF
* Wildcards: `? extends`, `? super`
* Beispiele mit Methodenparametern und Listen

---

### 🔟 **Streams & funktionale Verarbeitung**

* `forEach`, `filter`, `map`, `count`, `collect`
* `Stream.toList()` (Java 16 +)
* Lazy Evaluation und Parallel Streams

---

### 11️⃣ **Threadsafe & Concurrent Collections**

* Überblick über `ConcurrentHashMap`, `CopyOnWriteArrayList`, `BlockingQueue`
* Vergleich mit `Collections.synchronizedXXX()`

---

### 12️⃣ **Unmodifiable & Immutable Collections**

* `List.of()`, `Set.copyOf()`, `Map.of()`
* Unterschied: *unmodifiable* vs. *immutable*
* Praktische Beispiele

---

### 13️⃣ **Typische Fehler und Pitfalls**

* `ConcurrentModificationException`
* `UnsupportedOperationException` bei unveränderlichen Listen
* Vergleich mit `==` statt `equals()`
* Fehlende `hashCode()`-Implementierung

---

### 14️⃣ **Moderne Features (Java 21 – 25)**

* `SequencedCollection` & `SequencedMap`
* Verwendung von `record` als Key
* Beispiel: `Map.of(new Tier("Hund", 3), "Wuff")`
* `Stream.toList()` und `List.reversed()`

---

### 15️⃣ **Zusammenfassung**

* Einheitliche API und Typsicherheit
* Wichtige Implementierungen im Überblick
* Moderne, sichere, performante Nutzung

