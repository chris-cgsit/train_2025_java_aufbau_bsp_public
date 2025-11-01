# Java 25: Collections – Baumübersicht mit Kurzbeschreibung & Einsatzgebieten

> Fokus: **`java.util`** und **`java.util.concurrent`** Kernstrukturen.
> Hinweis: Seit Java 21 gibt es **`SequencedCollection`/`SequencedSet`/`SequencedMap`** (Vor/Zurück-Operationen auf geordneten Collections). In Java 25 implementieren u. a. `List`, `Deque`, `LinkedHashSet` diese Interfaces.

---

## `Collection<E>`

Allgemeine Schnittstelle für Container von Elementen (Hinzufügen, Entfernen, Iteration, Streams).

```
Collection
├─ List
├─ Set
└─ Queue (→ Deque)
```

---

## `List<E>` – geordnet, indexbasiert, Duplikate erlaubt

* **`ArrayList`**
  *Dynamisches Array.* Sehr schnell beim zufälligen Lesen (`get(i)`), durchschnittlich gut beim Anhängen; Einfügen/Entfernen in der Mitte teuer.
  **Einsatz:** häufige Reads, Anhängen, allgemeine Listen.

* **`LinkedList` (auch `Deque`)**
  *Doppelt verkettete Liste.* Schnell beim Einfügen/Entfernen an Kopf/Ende, langsamer beim random access.
  **Einsatz:** Queue/Deque-Szenarien, häufiges Einfügen/Entfernen an Rändern.

* **`Vector` / `Stack` (veraltet/Legacy)**
  Synchronisierte Uralt-Varianten.
  **Einsatz:** heute meiden; nutze `ArrayList` bzw. `Deque` statt `Stack`.

* **`CopyOnWriteArrayList` (concurrent)**
  Kopiert intern bei jeder Mutation. Iteration ist snapshot-sicher und sehr schnell, Mutationen teuer.
  **Einsatz:** viele Leser, wenige Schreiber; Event-Listener-Listen.

* **Unveränderliche Listen**
  `List.of(...)` / `Collections.unmodifiableList(...)`.
  **Einsatz:** Read-only-APIs, defensive returns.

> **Sequenced:** `List` ist eine `SequencedCollection` → `getFirst()/getLast()`, `addFirst()/addLast()` (per Default-Methoden).

---

## `Set<E>` – keine Duplikate

* **`HashSet`**
  Hash-basiert, keine Ordnung. Sehr schnelle `add/contains/remove`.
  **Einsatz:** Mitgliedschaftstests, De-Duplizierung ohne Reihenfolge.

* **`LinkedHashSet`** *(SequencedSet)*
  Hash-basiert **mit Einfüge-Reihenfolge** (oder Access-Order bei LRU-Patterns).
  **Einsatz:** stabile Iterationsreihenfolge, einfache LRU-Grundlage.

* **`TreeSet`** *(Sorted/Navigable)*
  Balancierter Baum (Red-Black). Sortiert nach `Comparator`/`Comparable`.
  **Einsatz:** sortierte Mengen, Bereichsanfragen (`subSet`, `headSet`, `tailSet`).

* **`EnumSet`**
  Bit-Vektor für `enum`-Typen. Extrem speicher- und laufzeiteffizient.
  **Einsatz:** Flag-Sets, Rechtemengen, schnelle Mengenoperationen.

* **`CopyOnWriteArraySet` (concurrent)**
  Wie `CopyOnWriteArrayList`, aber als Set.
  **Einsatz:** viele Leser, seltene Mutationen.

* **`ConcurrentSkipListSet` (concurrent, sorted)**
  Skip-List, sortiert/navigierbar, lock-arme Concurrency.
  **Einsatz:** concurrent + sortierte Menge.

* **Unveränderliche Sets**
  `Set.of(...)` / `Collections.unmodifiableSet(...)`.

---

## `Queue<E>` – FIFO/Prio, optional Kapazität; `Deque<E>` – doppelseitige Queue

* **`ArrayDeque` (Deque)**
  Ringpuffer; sehr schnell für `addFirst/addLast/removeFirst/removeLast`.
  **Einsatz:** Stack/Queue-Ersatz, Deque-Operationen ohne Synchronisation.

* **`LinkedList` (Deque)**
  Deque-Funktionalität mit verketteter Liste.
  **Einsatz:** wenn Deque + häufiges Einfügen an Rändern benötigt wird.

* **`PriorityQueue`**
  Heap-basiert, liefert stets das „kleinste“ (oder größte) Element zuerst. Keine stabile Ordnung von Gleichrangigen.
  **Einsatz:** Scheduler, Top-K, Event-Verarbeitung nach Priorität.

### Concurrent Queues / Blocking Queues (`java.util.concurrent`)

* **`ConcurrentLinkedQueue` / `ConcurrentLinkedDeque`**
  Lock-freie, unbeschränkte (De)Queues.
  **Einsatz:** hochgradig parallele Producer/Consumer ohne Backpressure.

* **`ArrayBlockingQueue` / `LinkedBlockingQueue`** *(BlockingQueue)*
  Kapazitätsbegrenzt (`Array` fix; `Linked` optional), blockiert Produzenten/Konsumenten bei Voll/Leer.
  **Einsatz:** Thread-Pools, Work-Queues mit Backpressure.

* **`PriorityBlockingQueue`**
  Blocking-Variante mit Prioritäten.
  **Einsatz:** priorisierte Background-Jobs.

* **`SynchronousQueue`**
  Keine Pufferung; „Hand-off“ direkt Produzent↔Konsument.
  **Einsatz:** Übergabe-Punkte, Executors (`newCachedThreadPool` intern).

* **`DelayQueue`** *(`Delayed`-Elemente)*
  Elemente werden erst nach Ablauf ihrer Verzögerung abrufbar.
  **Einsatz:** zeitversetzte Tasks, Retry-Mechanismen.

* **`LinkedTransferQueue`**
  Unterstützt direkte Übergaben (`tryTransfer/transfer`).
  **Einsatz:** hochskalierende Pipelines mit optionalem Hand-off.

> **Sequenced:** `Deque` ist `SequencedCollection` → Operationen am Anfang/Ende als First/Last-Semantik.

---

## Abgeleitete/Marker-Interfaces

* **`SortedSet` / `NavigableSet`**: sortierte/navigierbare Sets (`TreeSet`, `ConcurrentSkipListSet`).
* **`SequencedCollection` / `SequencedSet`** (seit Java 21+): garantierte Reihenfolge + First/Last-APIs (u. a. `LinkedHashSet`, `List`, `Deque`).

---

## Abstrakte Basisklassen (für eigene Implementierungen nützlich)

* **`AbstractCollection`**, **`AbstractList`**, **`AbstractSet`**, **`AbstractQueue`**:
  Skelett-Implementierungen, die viel Boilerplate abnehmen.

---

## Wahlhilfe (Daumenregeln)

* **Schnelle Mitgliedschaft, egal welche Reihenfolge:** `HashSet`
* **Mit Einfüge-Reihenfolge:** `LinkedHashSet` / `ArrayList`
* **Sortiert / Bereichsabfragen:** `TreeSet` (oder `ConcurrentSkipListSet` bei Concurrency)
* **Viele Reads, seltene Writes, sichere Iteration:** `CopyOnWriteArrayList/-Set`
* **FIFO-Queue ohne Blockierung:** `ArrayDeque`
* **Prio-Verarbeitung:** `PriorityQueue` / `PriorityBlockingQueue`
* **Producer/Consumer mit Backpressure:** `BlockingQueue`-Varianten
* **Lock-freie, hochskalierende Queues:** `ConcurrentLinkedQueue/-Deque`
* **Enums als Menge:** `EnumSet` (immer, wenn möglich)
* **Read-only API:** `List.of/Set.of` oder `Collections.unmodifiable*`

---

## Gleichheit & Ordnung (wichtig für korrektes Verhalten)

* **`Set`/`Map` (Hash-basiert):** implementiere **`equals` + `hashCode` konsistent**.
* **Sortierte Varianten (`TreeSet`, `PriorityQueue`):** liefere **`Comparator`** oder **`Comparable`**; Vergleich **muss konsistent zu equals** sein (oder bewusst anders, dann aber Dokumentation/Tests!).

---

Mit diesem Überblick kannst du für die meisten Szenarien schnell die passende Collection in Java 25 auswählen.
