## `CGS_Java_Object_Boxing_Master.md`

# Java – Basisklasse Object und Autoboxing (CGS IT)

## 1. Basisklasse Object

- Jede Klasse in Java erbt implizit von `java.lang.Object`,  
  wenn keine andere Basisklasse angegeben ist.
- Damit besitzt jede Klasse grundlegende Methoden:
  - `toString()` – Zeichenkettenrepräsentation eines Objekts
  - `equals(Object o)` – Vergleich zweier Objekte auf inhaltliche Gleichheit
  - `hashCode()` – Hashwert für z. B. `HashMap`, `HashSet`
  - `getClass()` – liefert Laufzeittyp (`Class<?>`)
  - `clone()` – Kopie des Objekts (optional)
  - `finalize()` – (veraltet) Aufräum-Mechanismus, **seit Java 9 deprecated**, **seit 18 entfernt**

### Beispiel

```java
class Tier {
    String name;
    public String toString() {
        return "Tier: " + name;
    }
}

class Hund extends Tier { }

public class Demo {
    public static void main(String[] args) {
        Hund h = new Hund();
        h.name = "Bello";
        System.out.println(h.toString());
        System.out.println(h.getClass());
    }
}
````

---

## 2. toString(), equals() und hashCode()

### toString()

* Standard-Implementierung aus `Object`:
  `getClass().getName() + "@" + Integer.toHexString(hashCode())`
* Sinnvoll überschreiben für aussagekräftige Objektdarstellung.

```java
@Override
public String toString() {
    return String.format("Hund[name=%s]", name);
}
```

### equals() und hashCode()

* Beide Methoden **gemeinsam überschreiben**, wenn Gleichheit logisch definiert ist.
* Regel:
  Wenn `a.equals(b)` true ist, muss auch `a.hashCode() == b.hashCode()` gelten.

```java
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
```

---

## 3. getClass() und Reflection

```java
Hund h = new Hund();
System.out.println(h.getClass().getName());
System.out.println(h instanceof Tier);     // true
System.out.println(h instanceof Object);   // true
```

* `getClass()` liefert exakten Laufzeittyp.
* Über Reflection (`Class`-API) lassen sich Methoden, Felder, Konstruktoren inspizieren.
* Seit **Java 21+**: erweitertes Pattern-Matching und `ClassDesc` API (JEP 457).

---

## 4. Wrapper-Klassen und Autoboxing

* Primitive Datentypen sind **keine Objekte**, aber Java bietet **Wrapper-Klassen**:

  | Primitiv  | Wrapper-Klasse |
    | --------- | -------------- |
  | `int`     | `Integer`      |
  | `double`  | `Double`       |
  | `boolean` | `Boolean`      |
  | `char`    | `Character`    |
  | `byte`    | `Byte`         |
  | `short`   | `Short`        |
  | `long`    | `Long`         |
  | `float`   | `Float`        |

### Autoboxing / Unboxing

* **Autoboxing**: automatisches Umwandeln von primitiven Typen in ihre Wrapper.
* **Unboxing**: Umwandeln eines Wrappers in seinen primitiven Wert.

```java
int x = 5;
Integer obj = x;      // Autoboxing
int y = obj;          // Unboxing
```

* Seit **Java 5** automatisch unterstützt.
* Performance: vermeide unnötiges Boxen in Schleifen oder Streams.

---

## 5. Vergleich von Wrappern

```java
Integer a = 100;
Integer b = 100;
System.out.println(a == b);     // true (Integer Cache)
System.out.println(a.equals(b)); // true

Integer c = 200;
Integer d = 200;
System.out.println(c == d);     // false
System.out.println(c.equals(d)); // true
```

* **Integer Cache**: Werte von `-128` bis `127` werden gepoolt.
* Vergleich mit `==` prüft Referenzen, **nicht Werte**.
* Immer `equals()` verwenden für Wrapper-Vergleiche.

---

## 6. Typumwandlung und Polymorphismus

```java
Object o = 5;          // Autoboxing auf Integer → Object
System.out.println(o.getClass()); // java.lang.Integer

if (o instanceof Integer i) {
    System.out.println("Wert: " + i);
}
```

* Auch Autoboxing-Ergebnisse sind echte Objekte.
* `Object` kann beliebige Typen aufnehmen → Polymorphismus auf höchster Ebene.

---

## 7. Unboxing-Fallstricke

```java
Integer i = null;
int x = i; // NullPointerException!
```

* Unboxing bei `null` führt zu `NullPointerException`.
* In Streams oder optionalen Typen vermeiden (`OptionalInt`, `OptionalDouble`, etc.).

---

## 8. Best Practices

✅ **Empfehlungen**

* Immer `equals()` statt `==` für Wrapper.
* `Objects.equals(a, b)` nutzen – sicher auch bei `null`.
* Keine Autoboxing-Schleifen bei großen Datenmengen.
* `record`-Klassen nutzen statt manuellem `equals`/`hashCode`.

🚫 **Vermeiden**

* Vergleich `==` bei `Integer`, `Double` etc.
* Autounboxing von `null`.
* `finalize()` verwenden (seit Java 18 entfernt).

---

## 9. Moderne Alternativen (Java 21–25)

* **`record`**: ersetzt viele `Object`-Überschreibungen automatisch:

  ```java
  public record Hund(String name, int alter) { }
  ```

  → erzeugt automatisch `toString()`, `equals()`, `hashCode()`.

* **`Pattern Matching` für `switch`**: kombiniert Typprüfung und Extraktion:

  ```java
  Object obj = 5;
  switch (obj) {
      case Integer i -> System.out.println("int: " + i);
      case String s  -> System.out.println("String: " + s);
      default -> System.out.println("Unbekannt");
  }
  ```

---

## 10. Zusammenfassung

* Jede Klasse in Java erbt implizit von `Object`.
* `Object` definiert fundamentale Methoden für alle Klassen.
* Wrapper-Klassen ermöglichen Objektarbeit mit primitiven Typen.
* Autoboxing vereinfacht, kann aber Performance kosten.
* Moderne Alternativen wie `record` und Pattern-Matching erleichtern objektorientierte Arbeit.

```
