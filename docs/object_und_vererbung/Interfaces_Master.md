## **Erweiterte Master-Version: CGS_Java_Interfaces_Master.md (Ergänzungen integriert)**

# Java – Interfaces und Typinformation (CGS IT)

## 1. Grundlagen der Interfaces

Ein **Interface** definiert ein Verhalten, nicht den Zustand eines Objekts.

- Enthält standardmäßig:
  - Abstrakte Methoden (`public abstract`)
  - Konstanten (`public static final`)
- Ab **Java 8**:
  - `default`-Methoden mit Implementierung möglich
  - `static`-Methoden erlaubt (z. B. Hilfsfunktionen)
- Ab **Java 9**:
  - `private`-Methoden zur Wiederverwendung innerhalb von `default`-Methoden
- Ab **Java 17–25**:
  - `sealed interface` → nur bestimmte Klassen dürfen implementieren

### Beispiel

```java
public interface Media {
    void play();
    String getFilename();
    default void stop() {
        System.out.println("Stopped");
    }
    static String formatName(String name) {
        return name.toUpperCase();
    }
}
````

---

## 2. Statische und Default-Methoden in Interfaces

Seit **Java 8** können Interfaces neben abstrakten Methoden auch **Default-** und **statische Methoden** enthalten.

### Default-Methoden

* Ermöglichen gemeinsame Logik für alle Implementierungen
* Können von Subklassen **überschrieben** werden

```java
public interface Loggable {
    default void log(String msg) {
        System.out.println("[LOG] " + msg);
    }
}

public class Service implements Loggable {
    public void doWork() {
        log("Service gestartet");
    }
}
```

### Statische Methoden

* Gehören zum Interface selbst, nicht zu den Implementierungen
* Ideal für Hilfsfunktionen oder Factory-Methoden

```java
public interface Converter {
    static int parse(String s) {
        return Integer.parseInt(s);
    }
}
int value = Converter.parse("42");
```

---

## 3. Mehrfachvererbung und Konfliktlösung

* Eine Klasse kann mehrere Interfaces implementieren
* Interfaces können voneinander erben
* Konflikte bei `default`-Methoden müssen explizit aufgelöst werden

```java
interface A { default void run() { System.out.println("A"); } }
interface B { default void run() { System.out.println("B"); } }

class Test implements A, B {
    @Override
    public void run() { A.super.run(); }   // Konfliktauflösung
}
```

---

## 4. Sealed Interfaces (Java 17+)

```java
public sealed interface Shape permits Circle, Rectangle { }
public final class Circle implements Shape { }
public non-sealed class Rectangle implements Shape { }
```

* `sealed` beschränkt erlaubte Implementierungen
* Jede erlaubte Klasse muss `final`, `sealed` oder `non-sealed` sein
* Erleichtert **Pattern Matching** mit `switch`-Ausdrücken

```java
Shape s = new Circle();
switch (s) {
    case Circle c -> System.out.println("r=" + c.r());
    case Rectangle r -> System.out.println("area=" + r.area());
}
```

---

## 5. Pattern Matching mit `instanceof` (ab Java 14)

Das Pattern-Matching erweitert `instanceof`, um **automatisch zu casten** und eine Variable zu binden.

```java
Object obj = new Dackel();

if (obj instanceof Hund h) {   // ab Java 14
    h.bellen();                // kein manueller Cast nötig
} else {
    System.out.println("Kein Hund");
}
```

* Wenn das Objekt **vom angegebenen Typ ist**, wird es an die Variable gebunden.
* Wenn **nicht**, ist die Variable **nicht verfügbar**.
* Gilt auch innerhalb logischer Ausdrücke (Java 16+):

```java
if (obj instanceof Hund h && h.getName().equals("Bello")) {
    System.out.println("Gefundener Hund: " + h);
}
```

* **Ab Java 21:** Pattern Matching in `switch`-Statements mit `case`-Labels (siehe `sealed interface Shape`).

---

## 6. Typinformation und Reflection

```java
Object obj = new Dackel();

System.out.println(obj.getClass().getName());
System.out.println(obj instanceof Hund);
System.out.println(obj instanceof Tier);

if (obj instanceof Hund h) {
    h.bellen();
}
```

### Wichtige Methoden der Reflection-API

```java
Class<?> clazz = obj.getClass();
System.out.println(clazz.isInterface());
System.out.println(clazz.getSuperclass());
System.out.println(Arrays.toString(clazz.getInterfaces()));
```

* `instanceof` prüft Laufzeittyp
* `getClass()` liefert exakten Typ
* Reflection ermöglicht:

    * dynamische Typprüfung
    * Methoden- und Feldzugriff zur Laufzeit
    * dynamische Objekterzeugung (`clazz.getConstructor().newInstance()`)

---

## 7. Best Practices und Fallstricke

**Best Practices**

* Interfaces beschreiben Verhalten („can do“)
* Gemeinsame Logik gehört in abstrakte Klassen
* `default`-Methoden sparsam einsetzen
* `sealed interface` für kontrollierte Erweiterbarkeit
* Utility-Funktionen als `static` im Interface oder Hilfsklasse

**Fallstricke**

* Mehrdeutige `default`-Methoden → Konfliktlösung nötig
* Interfaces mit Zustand → Anti-Pattern
* Zu viele kleine Interfaces → unübersichtliche API
* `instanceof` vermeiden, wenn Polymorphismus möglich ist

---

## 8. Vergleich: Abstrakte Klasse vs Interface

| Aspekt            | Abstrakte Klasse    | Interface                 |
| ----------------- | ------------------- | ------------------------- |
| Zustand           | Ja (Felder erlaubt) | Nein                      |
| Konstruktoren     | Ja                  | Nein                      |
| Mehrfachvererbung | Nein                | Ja                        |
| Methoden          | Abstrakt + konkret  | Abstrakt, default, static |
| Verwendung        | Gemeinsame Logik    | Verhalten / Fähigkeit     |

---

## 9. Erweiterung mit `sealed`, `record`, `enum`

* Interfaces können mit modernen Sprachfeatures kombiniert werden:

    * `sealed interface` → eingeschränkte Hierarchie
    * `record` → implementiert automatisch Interfaces
    * `enum` → kann Interfaces implementieren

```java
sealed interface Tier permits Hund, Katze { void laut(); }
record Hund(String name) implements Tier { public void laut() { System.out.println("Wuff"); } }
enum Katze implements Tier { FELIX, LUNA; public void laut() { System.out.println("Miau"); } }
```

---

## 10. Zusammenfassung

* Interfaces sind **Verhaltensverträge**
* Ab Java 8+ mächtiger durch `default`, `static`, `private`-Methoden
* Ab Java 17+ kombinierbar mit `sealed`, `record`, `enum`
* Pattern-Matching und Reflection erweitern Polymorphismus zur Laufzeit

````