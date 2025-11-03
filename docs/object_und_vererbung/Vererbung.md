# 🧩 Java – Vererbung
*CGS IT Trainingsfolien (aktualisiert für Java 25 LTS)*

---

## Folie: Grundlagen der Vererbung

**Inhalt:**
- Vererbung = Spezialisierung einer Klasse durch eine andere.
- Subklasse („abgeleitete Klasse“) erbt Attribute und Methoden der Superklasse.
- Ziel: Code-Wiederverwendung, Erweiterbarkeit, Polymorphismus.

💡 Begriffspaare:

| Deutsch | Englisch |
|----------|-----------|
| Basisklasse | Superclass |
| abgeleitete Klasse | Subclass |
| Vererbung | Inheritance |

**Notizen (Trainer):**
- Einstieg mit Frage: „Warum vererben wir überhaupt?“
- Diskutiere Alternativen (Komposition, Interfaces).
- Tipp: Verwende Alltagsbeispiele („Tier“, „Hund“, „Dackel“) zur Intuition.

---

## Folie: Beispiel – Basisklasse und Subklasse

**Inhalt:**
```java
public class Hund {
    public void bellen() { System.out.println("Wuff!"); }
}

public class Dackel extends Hund {
    public void buddeln() { System.out.println("Ich grabe ein Loch!"); }
}

Dackel waldi = new Dackel();
waldi.bellen();   // geerbte Methode
waldi.buddeln();  // eigene Methode
```
➡️ `Dackel` erbt automatisch alle `public` und `protected` Methoden der Klasse `Hund`.  
➡️ `private` Elemente werden nicht vererbt.

⚠️ **Einfachvererbung:**  
Java erlaubt nur **eine** Superklasse.

**Notizen (Trainer):**
- Live zeigen: `waldi.bellen()` ruft Methode aus Basisklasse auf.
- Frage: „Was passiert, wenn Hund auch `buddeln()` hätte?“
- Zeige: `private` wird nicht vererbt → kein Zugriff.
- Diskutiere: Warum keine Mehrfachvererbung (Diamantproblem)?

---

## Folie: Konstruktor-Reihenfolge und `super`

**Inhalt:**
```java
public class Hund {
    public Hund() { System.out.println("Hund-Konstruktor"); }
}

public class Dackel extends Hund {
    public Dackel() { System.out.println("Dackel-Konstruktor"); }
}

new Dackel();
```
🧩 Ausgabe:
```
Hund-Konstruktor
Dackel-Konstruktor
```
➡️ Beim Erzeugen eines Subobjekts wird **zuerst der Konstruktor der Superklasse** ausgeführt.  
➡️ `super()` wird implizit eingefügt, wenn kein expliziter Aufruf vorhanden ist.

**Notizen (Trainer):**
- Frage: „Warum startet immer die Superklasse zuerst?“
- Antwort: Grundinitialisierung der Basiskomponenten.
- Zeige im Code, was passiert, wenn `super()` entfällt.
- Hinweis: Ab **Java 25** (JEP 513) ist etwas mehr Logik *vor* dem `super()`-Aufruf erlaubt (flexible Konstruktoren).

---

## Folie: Expliziter `super`-Aufruf

**Inhalt:**
```java
public class Hund {
    private final int gewicht;
    public Hund(int gewicht) {
        this.gewicht = gewicht;
        System.out.println("Hund mit Gewicht " + gewicht + " kg");
    }
}

public class Dackel extends Hund {
    public Dackel(int gewicht) {
        super(gewicht); // explizit
        System.out.println("Dackel-Konstruktor abgeschlossen");
    }
}

new Dackel(14);
```
🧩 Ausgabe:
```
Hund mit Gewicht 14 kg
Dackel-Konstruktor abgeschlossen
```
**Notizen (Trainer):**
- Zeige Compilerfehler, wenn `super(gewicht)` fehlt.
- Diskutiere: Warum *erste Anweisung* im Konstruktor?
- In Java 25 kann man einfache Vorinitialisierungen vor `super()` machen – erwähnen, aber alte Regel bleibt gültig.

---

## Folie: `this` und `super`

**Inhalt:**
```java
public class Hund {
    public void bellen() { System.out.println("Wuff!"); }
}

public class Dackel extends Hund {
    @Override
    public void bellen() {
        super.bellen();  // ruft Superklasse
        System.out.println("Wuff-wuff, ich bin ein Dackel!");
    }
}

new Dackel().bellen();
```
🧩 Ausgabe:
```
Wuff!
Wuff-wuff, ich bin ein Dackel!
```
💡 `this()` ruft anderen Konstruktor **derselben Klasse** auf.  
💡 `super()` ruft Konstruktor **der Superklasse** auf.

**Notizen (Trainer):**
- Beispiel live zeigen und nach `super.bellen()` entfernen → Unterschied sichtbar.
- Diskutiere: „Warum erlaubt Java nicht `this()` und `super()` gleichzeitig?“
- Erinnerung: `super.bellen()` → explizit für Methoden der Elternklasse.

---

## Folie: Polymorphismus in Aktion

**Inhalt:**
```java
public class Hund {
    public void bellen() { System.out.println("Wuff!"); }
}

public class Dackel extends Hund {
    @Override
    public void bellen() { System.out.println("Wuff-wuff, kleiner Dackel!"); }
}

Hund h = new Dackel(); // erlaubt
h.bellen();
```
🧩 Ausgabe:
```
Wuff-wuff, kleiner Dackel!
```
➡️ Obwohl `h` vom Typ `Hund` ist, wird **die Dackel-Methode** ausgeführt.  
➡️ Das Verhalten richtet sich nach dem **Laufzeittyp** → Polymorphismus.

**Notizen (Trainer):**
- Wichtig: Laufzeittyp ≠ Deklarationstyp!
- Frage: „Was würde passieren, wenn Dackel eine zusätzliche Methode hat?“
- Demo: `if (h instanceof Dackel d) d.bellen();` – modernes Pattern Matching zeigen.

---

## Folie: Methoden überschreiben (`@Override`)

**Inhalt:**
```java
public class Hund {
    public void bellen() { System.out.println("Wuff!"); }
}

public class Dackel extends Hund {
    @Override
    public void bellen() {
        System.out.println("Wuff-wuff, ich bin ein Dackel!");
    }
}
```
➡️ Methode der Subklasse ersetzt jene der Superklasse.  
➡️ Entscheidung erfolgt **zur Laufzeit** (dynamische Bindung).

**Notizen (Trainer):**
- Live: entferne `@Override` → kein Fehler, aber Verhalten anders bei Tippfehlern.
- Zeige „Overload“ vs. „Override“.
- Beispiel mit kovariantem Rückgabetyp (z. B. `Object` → `String`).

---

## Folie: Sichtbarkeit und Zugriff

**Inhalt:**  
| Modifier | Sichtbarkeit | Vererbbar |
|-----------|---------------|-----------|
| `private` | nur Klasse | ❌ |
| *(default)* | Paket | ✅ |
| `protected` | Paket + Subklassen | ✅ |
| `public` | überall | ✅ |
💡 `protected` erlaubt Subklassen-Zugriff, schwächt aber Kapselung.

**Notizen (Trainer):**
- Zeige Beispiel mit zwei Paketen.
- Frage: „Wann ist `protected` sinnvoll?“
- Diskutiere: zu viele `protected` → API-Leck.

---

## Folie: Moderne Sprachfeatures (Java 17 – 25)

**Inhalt:**
```java
public sealed class Hund permits Dackel, Pudel {}
public final class Dackel extends Hund {}
public final class Pudel extends Hund {}

public record HundRecord(String name, int gewicht) {}

if (obj instanceof Dackel d) {
    d.bellen();
}
```
➡️ `sealed` = kontrollierte Vererbung  
➡️ `record` = immutable, nicht vererbbar  
➡️ Pattern Matching = modernes Downcasting

**Notizen (Trainer):**
- Zeige, dass `sealed` nützlich ist bei stabilen APIs.
- Diskutiere Unterschied zu `final` (komplette Sperre).
- Erwähne, dass `record` ab Java 25 `with`-Konstruktoren unterstützt (JEP 508).

---

## Folie: Tipps und Pitfalls

**Inhalt:**  
💡 **Best Practices**
- Immer `@Override` verwenden.
- Komposition > tiefe Vererbung.
- Konstruktoren kurz halten.
- Abstrakte Basisklassen sparsam einsetzen.
- Beziehungen klar dokumentieren (`is-a` vs. `has-a`).

⚠️ **Häufige Fehler**
- Fehlender `super(...)` bei nicht-parameterlosem Basiskonstruktor.
- Zugriff auf `private` Felder der Superklasse.
- Gleichnamige Felder (Shadowing).
- Zu tiefe Hierarchien.
- Verwechslung statische ↔ dynamische Bindung.

**Notizen (Trainer):**
- Frage: „Wer kennt reale Beispiele für schlechte Vererbung?“
- Diskutiere Refactoring: wann Basisklasse extrahieren?
- Hinweis: In Java 25 bleibt Vererbungsmodell stabil; Fokus jetzt mehr auf Typsystem und Pattern Matching.  
