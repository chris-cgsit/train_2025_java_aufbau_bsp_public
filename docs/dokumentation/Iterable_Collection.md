# Aufbau und Vererbung von List, Collection und Iterable

Eine `List` in Java ist Teil einer klar aufgebauten Vererbungshierarchie.
Viele ihrer Methoden stammen gar nicht direkt aus der `List`, sondern werden von übergeordneten Interfaces geerbt.

---

## Hierarchie

```
Iterable<E>
   ↑
Collection<E>
   ↑
List<E>
```

---

## Iterable

Das Interface **Iterable** ist die grundlegende Basis für alle Collections.
Es definiert, dass eine Klasse über ihre Elemente iteriert werden kann, z. B. mit einer `for-each`-Schleife.

**Wichtige Methoden**

* `iterator()` – gibt einen Iterator zurück, um Elemente zu durchlaufen
* `forEach(Consumer<? super E> action)` – wendet eine Funktion auf alle Elemente an
* Unterstützt den **Enhanced for-Loop** (`for (Element e : collection) {...}`)

---

## Collection

**Collection** erweitert `Iterable` um allgemeine Methoden zum Hinzufügen, Entfernen und Abfragen von Elementen.
Alle gängigen Datentypen wie `List`, `Set` oder `Queue` sind Untertypen von `Collection`.

**Beispiele für Collection-Methoden**

| Methode                                 | Beschreibung                                      |
| --------------------------------------- | ------------------------------------------------- |
| `add(E e)`                              | Fügt ein Element hinzu                            |
| `addAll(Collection<? extends E> c)`     | Fügt alle Elemente einer anderen Collection hinzu |
| `remove(Object o)`                      | Entfernt ein Element                              |
| `removeIf(Predicate<? super E> filter)` | Entfernt Elemente nach Bedingung                  |
| `clear()`                               | Entfernt alle Elemente                            |
| `contains(Object o)`                    | Prüft, ob ein Element vorhanden ist               |
| `isEmpty()`                             | Prüft, ob die Collection leer ist                 |
| `size()`                                | Liefert die Anzahl der Elemente                   |
| `iterator()`                            | Gibt einen Iterator zurück                        |
| `forEach(Consumer<? super E> action)`   | Führt eine Aktion für jedes Element aus           |
| `stream()` / `parallelStream()`         | Erzeugt einen (parallelen) Stream                 |

Diese Methoden bilden die gemeinsame Basis für alle Sammlungen in Java.

---

## Objektgleichheit in Collections

Damit eine Collection Objekte korrekt vergleichen oder finden kann, muss Java wissen, wann zwei Objekte **gleich** sind.
Das wird über die Methoden `equals()` und `hashCode()` geregelt.

### Warum das wichtig ist

Viele Operationen wie `contains()`, `remove(Object o)` oder das Verhindern von Duplikaten basieren auf diesen Methoden.
Ohne eine eigene Implementierung prüft Java nur, ob zwei Referenzen auf dasselbe Objekt zeigen –
nicht, ob die Inhalte gleich sind.

Wenn eigene Klassen (z. B. `Person`, `Artikel`, `Adresse`) in einer Collection verwendet werden,
sollten `equals()` und `hashCode()` überschrieben werden, um eine **inhaltliche Gleichheit** zu definieren.

---

### Wann welche Methode benötigt wird

| Collection-Typ | benötigt `equals()` | benötigt `hashCode()` | Bemerkung                                                                       |
| -------------- | ------------------- | --------------------- | ------------------------------------------------------------------------------- |
| `List`         | ✅ ja                | ❌ nein                | Vergleicht Elemente mit `equals()` (z. B. bei `contains()` oder `remove()`)     |
| `Set`          | ✅ ja                | ✅ ja                  | Erfordert beide Methoden, um Duplikate zu erkennen (`HashSet`, `LinkedHashSet`) |
| `Map` (Keys)   | ✅ ja                | ✅ ja                  | Schlüsselvergleich basiert auf beiden Methoden                                  |
| `Queue`        | ✅ teilweise         | ❌                     | Bei Warteschlangen meist nicht relevant                                         |

---

### Beispielhafte Bedeutung

Wenn eine `List<Person>` den Befehl

```java
personen.contains(new Person("Chris", "Schaefer"));
```

verarbeiten soll, vergleicht sie jedes Element mit `equals()`.
Nur wenn diese Methode definiert, dass zwei Personen gleich sind (z. B. über E-Mail oder ID),
wird das gewünschte Objekt gefunden.

---

### Wichtig zu merken

* Für **Listen** reicht `equals()` aus, um Gleichheit zu prüfen.
* Für **Sets** und **Maps** müssen **beide Methoden** (`equals()` und `hashCode()`) konsistent überschrieben werden.
* Wenn `equals()` und `hashCode()` nicht zusammenpassen, können Objekte nicht mehr zuverlässig gefunden oder gelöscht werden.

---

**Merksatz:**

> Wenn Objekte in einer Collection verglichen oder gesucht werden sollen,
> müssen sie eine sinnvolle und konsistente Definition von `equals()` und `hashCode()` besitzen.

---

## List

**List** erweitert `Collection` um zusätzliche Funktionen, die auf **indizierte und geordnete Elemente** ausgelegt sind.
Sie erlaubt den Zugriff über Positionen und die gezielte Steuerung der Reihenfolge.

**Zusätzliche Methoden von List**

| Methode                                       | Beschreibung                                                  |
| --------------------------------------------- | ------------------------------------------------------------- |
| `get(int index)`                              | Gibt das Element an einer bestimmten Position zurück          |
| `set(int index, E element)`                   | Ersetzt ein Element an einer Position                         |
| `add(int index, E element)`                   | Fügt ein Element an einer bestimmten Position ein             |
| `remove(int index)`                           | Entfernt das Element an einer bestimmten Position             |
| `indexOf(Object o)` / `lastIndexOf(Object o)` | Liefert die Position eines Elements                           |
| `listIterator()`                              | Gibt einen Iterator zurück, der vor- und rückwärts gehen kann |
| `subList(int fromIndex, int toIndex)`         | Gibt eine Teilliste eines bestimmten Bereichs zurück          |

---

## Zusammenfassung

* **Iterable**: definiert die Möglichkeit, über Elemente zu iterieren (`for-each`, `iterator()`)
* **Collection**: bietet grundlegende Operationen wie `add`, `remove`, `contains`, `size`
* **List**: erweitert um positionsbasierte Zugriffe und erlaubt eine definierte Reihenfolge
* **Objektgleichheit**: sorgt dafür, dass `contains()`, `remove()` und `Set`-Vergleiche korrekt funktionieren

Oder kurz gesagt:

> Eine *List* ist eine spezialisierte *Collection*,
> und jede *Collection* ist etwas, über das man mit *Iterable* iterieren kann.
