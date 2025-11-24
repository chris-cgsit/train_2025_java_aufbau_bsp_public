
# Iterations Varianten über eine Java Liste 

## Iteration über eine `List<Person>` in Java
Dieses Dokument zeigt **alle gebräuchlichen Möglichkeiten**, 
über eine `List<Person>` zu iterieren, 
um Elemente zu **lesen**, **suchen**, **bearbeiten** oder **löschen**.

---

## Beispielbasis

```java
private final List<Person> personen = new ArrayList<>();
````

---

## 1️ Klassische for-Schleife (indexbasiert)

Diese Variante gibt dir volle Kontrolle über den Index und erlaubt sicheres Entfernen während der Iteration.

```java
for (int i = 0; i < personen.size(); i++) {
    Person p = personen.get(i);
    // Verarbeitung

    if (p.shouldBeDeleted()) {
        personen.remove(i);
        i--; // wichtig bei Remove aber so zu fehleranfällig!
    }
}
```

**Vorteile**

* Entfernen während Iteration möglich
* Zugriff über Index

**Nachteile**

* Weniger elegant
* Manuelle Indexverwaltung nötig

---

## 2️ Enhanced for-Loop (for-each)

Die for-each-Schleife ist die einfachste und lesbarste Art, über eine Liste zu iterieren.

```java
for (Person p : personen) {
    System.out.println(p.getName());
}
```

**Vorteile**

* Sehr lesbar
* Kurz und prägnant

**Nachteile**

* Kein Entfernen oder Hinzufügen möglich (`ConcurrentModificationException`)

### Erklärung zum Iterator in der Enhanced for Loop:

Warum der Enhanced for-Loop (for-each) beim Entfernen eine Exception wirft

- Der for-each-Loop verwendet intern automatisch einen Iterator, auch wenn man ihn im Code nicht sieht.
Wenn man jedoch direkt personen.remove(p) aufruft, wird die Liste außerhalb des Iterators verändert.

- Der versteckte Iterator läuft intern über die Liste.
- Du rufst aber nicht iterator.remove() auf, sondern personen.remove() direkt.
- Dadurch ändert sich die interne Struktur der Liste (z. B. Größe, Speicher, modCount in ArrayList).
- Beim nächsten Zugriff merkt der Iterator, dass sich die Liste außerhalb seiner Kontrolle verändert hat,
und wirft eine ConcurrentModificationException.

Kurz gesagt:
- Der for-each-Loop ist nur zum Lesen gedacht —
- nicht zum Entfernen oder Ändern von Elementen.


---

## 3 Iterator

Mit einem `Iterator` kann man sicher Elemente entfernen, während man durch die Liste läuft.

```java
Iterator<Person> it = personen.iterator();
while (it.hasNext()) {
    Person p = it.next();

    if (p.shouldBeDeleted()) {
        it.remove(); // korrektes Entfernen
    }
}
```

**Vorteile**

* Sicheres Entfernen während Iteration
* Einfacher als manuelles Indexmanagement

**Nachteile**

* Etwas mehr Schreibaufwand

---

## 4 ListIterator (bidirektional + add)

Der `ListIterator` erlaubt zusätzlich das **Rückwärtslaufen** und **Hinzufügen von Elementen** während der Iteration.

```java
ListIterator<Person> it = personen.listIterator();
while (it.hasNext()) {
    Person p = it.next();

    if (p.shouldBeDeleted()) {
        it.remove();
    }

    if (p.getName().equals("Chris")) {
        it.add(new Person("New User")); // Hinzufügen während der Iteration
    }
}
```

**Vorteile**

* ✅ Entfernen **und** Hinzufügen möglich
* ✅ Bidirektionales Traversieren

**Nachteile**

* ⚠️ Komplexer als einfacher Iterator

---

## 5 Stream API (Java 8+)

Streams ermöglichen eine funktionale Schreibweise für das Durchlaufen von Listen.

### Lesen und Filtern

```java
personen.stream()
    .filter(p -> p.getAge() > 30)
    .forEach(System.out::println);
```

### Löschen (indirekt)

```java
personen.removeIf(p -> p.getAge() > 65);
```

**Vorteile**

* ✅ Modern, deklarativ und sehr lesbar
* ✅ Ideal für Filterung und Transformation

**Nachteile**

* ❌ Kein direktes Entfernen im Stream selbst

---

## 6 removeIf (modern & elegant löschen)

Ein einzelner Aufruf, um alle Elemente zu entfernen, die ein Kriterium erfüllen.

```java
personen.removeIf(p -> p.shouldBeDeleted());
```

**Vorteile**

* ✅ Sehr kurz und lesbar
* ✅ Kein Risiko einer `ConcurrentModificationException`
* ✅ Empfohlene Methode zum Löschen

**Nachteile**

* ⚠️ Kein Zugriff auf Index während der Iteration

---

## 7 forEach (Iterable Default-Methode)

Lambda-basierte Variante für einfache Verarbeitungsschritte.

```java
personen.forEach(p -> System.out.println(p.getName()));
```

**Vorteile**

* ✅ Kürzeste Schreibweise
* ✅ Modern (seit Java 8)

**Nachteile**

* ❌ Kein Entfernen oder Hinzufügen möglich

---

## 8️ Parallel Stream

Automatische Parallelisierung der Verarbeitung.

```java
personen.parallelStream()
    .forEach(p -> process(p));
```

**Vorteile**

* ✅ Parallelität ohne Thread-Handling
* ✅ Ideal für CPU-intensive Aufgaben

**Nachteile**

* ⚠️ Nur thread-safe Operationen erlaubt
* ⚠️ Keine Änderungen an der Liste währenddessen

---

## 9️ while-Schleife (klassisch)

Alternative zur for-Schleife mit voller Kontrolle.

```java
int index = 0;
while (index < personen.size()) {
    Person p = personen.get(index);
    index++;
}
```

**Vorteile**

* ✅ Kontrollierbare Iteration

**Nachteile**

* ⚠️ Veraltet für normale Listenverarbeitung

---

## 10 Spliterator (fortgeschritten)

Der `Spliterator` wird hauptsächlich intern von Streams genutzt.

```java
Spliterator<Person> s = personen.spliterator();
s.forEachRemaining(p -> System.out.println(p.getName()));
```

**Vorteile**

* ✅ Für parallele oder spezialisierte Verarbeitung

**Nachteile**

* ⚙️ Selten direkt im Code nötig

---

## Übersichtstabelle über alle Lösungsvarianten

| Methode            | Löschen erlaubt | Beschreibung                          |
| ------------------ | --------------- | ------------------------------------- |
| for-Loop           | ✅               | Klassiker, volle Kontrolle über Index |
| for-each           | ❌               | Einfach, aber nicht zum Entfernen     |
| Iterator           | ✅               | Sicheres Entfernen                    |
| ListIterator       | ✅               | Entfernen + Hinzufügen möglich        |
| Stream             | ❌               | Nur lesen/verarbeiten                 |
| removeIf()         | ✅✅✅             | Modern und effizient zum Löschen      |
| forEach() (Lambda) | ❌               | Für Ausgabe/Verarbeitung              |
| parallelStream()   | ⚠️              | Nur thread-safe Operationen           |
| while()            | ✅               | Alternative zur for-Schleife          |
| Spliterator        | ⚙️              | Für komplexe oder parallele Szenarien |

---

## Empfehlungen und Tipps

| Anwendungsfall               | Empfohlene Methode                    |
| ---------------------------- | ------------------------------------- |
| Nur Lesen / Ausgabe          | `for-each`, `forEach`, `Stream`       |
| Suchen nach Element          | `Stream.filter().findFirst()`         |
| Löschen während Iteration    | `Iterator.remove()` oder `removeIf()` |
| Hinzufügen während Iteration | `ListIterator.add()`                  |
| Parallelverarbeitung         | `parallelStream()` (thread-safe)      |

---

## 🧠 Fazit

* Für **Lesen/Verarbeiten**: `forEach()` oder `Stream`
* Für **sicheres Entfernen**: `Iterator.remove()` oder `removeIf()`
* Für **Ergänzen während Iteration**: `ListIterator.add()`
* Für **moderne Schreibweise**: bevorzugt `removeIf()` und Streams

---

© 2018 bis 2025 CGS IT Solutions GmbH
*Java Trainingsmaterial / List Iteration Guide*

```

