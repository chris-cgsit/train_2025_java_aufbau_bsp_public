# Java OOP – Quiz: Vererbung, Interfaces und Polymorphismus
meta:
version: 1.0
topic: Java OOP
difficulty: Beginner to Intermediate
points_total: 20

## Frage 1
Welche Aussage zur Vererbung in Java ist **richtig**?

- [ ] Eine Klasse kann von mehreren Klassen erben.
- [x] Eine Klasse kann nur **eine** Basisklasse haben.
- [ ] Nur Interfaces können vererbt werden.
- [ ] Vererbung funktioniert nur mit abstrakten Klassen.

> Punkte: 1

---

## Frage 2
Was passiert, wenn eine Subklasse eine Methode der Basisklasse mit gleichem Namen deklariert?

- [x] Die Methode wird **überschrieben (Override)**.
- [ ] Beide Methoden werden abwechselnd aufgerufen.
- [ ] Die Basismethode bleibt erhalten und wird immer zuerst ausgeführt.
- [ ] Die Subklasse kompiliert nicht.

> Punkte: 1  
> Erklärung: In Java überschreibt eine Methode mit identischer Signatur die Version der Basisklasse, sofern `@Override` angegeben ist.

---

## Frage 3
Was gibt der folgende Code aus?

```java
class Tier { void sprechen() { System.out.println("Tier"); } }
class Hund extends Tier { void sprechen() { System.out.println("Hund"); } }

public class Demo {
  public static void main(String[] args) {
    Tier t = new Hund();
    t.sprechen();
  }
}
```

- [x] Hund
- [ ] Tier
- [ ] Kompilierfehler
- [ ] Laufzeitfehler

> Punkte: 2  
> Erklärung: Der dynamische Typ (`Hund`) bestimmt die aufgerufene Methode → Polymorphismus.

---

## Frage 4
Wofür steht das Schlüsselwort `super`?

- [ ] Für das aktuelle Objekt der Klasse.
- [x] Für den Zugriff auf die **Basisklasse**.
- [ ] Für eine statische Methode.
- [ ] Für den Zugriff auf Unterklassen.

> Punkte: 1

---

## Frage 5
Welche Aussage zu `Object` ist **falsch**?

- [ ] Jede Klasse erbt implizit von `Object`.
- [x] `Object` kann nicht überschrieben werden.
- [ ] `equals()` und `hashCode()` sind in `Object` definiert.
- [ ] `toString()` liefert eine Standarddarstellung des Objekts.

> Punkte: 1

---

## Frage 6
Was ist **kein Merkmal** von Objektorientierung?

- [ ] Kapselung
- [x] Pointerarithmetik
- [ ] Vererbung
- [ ] Polymorphismus

> Punkte: 1  
> Erklärung: Pointerarithmetik gibt es in C/C++, aber nicht in Java.

---

## Frage 7
Welche der folgenden Aussagen über **Interfaces** ist korrekt?

- [x] Interfaces können statische und `default`-Methoden enthalten.
- [ ] Interfaces dürfen keine Konstanten enthalten.
- [ ] Interfaces können Instanzvariablen besitzen.
- [ ] Interfaces müssen immer abstrakt deklariert werden.

> Punkte: 1  
> Erklärung: Seit Java 8 können Interfaces `default`- und `static`-Methoden enthalten.

---

## Frage 8
Was passiert bei folgendem Code?

```java
interface Tier { void sprechen(); }
class Hund implements Tier {
    public void sprechen() { System.out.println("Wuff"); }
}
public class Demo {
    public static void main(String[] args) {
        Tier t = new Hund();
        t.sprechen();
    }
}
```

- [x] Ausgabe: `Wuff`
- [ ] Kompilierfehler
- [ ] Laufzeitfehler
- [ ] Keine Ausgabe

> Punkte: 2  
> Erklärung: `Tier` ist ein Interface, `Hund` implementiert es → polymorpher Aufruf.

---

## Frage 9
Welche Aussage über `abstract` Klassen ist richtig?

- [ ] Abstrakte Klassen dürfen keine Konstruktoren haben.
- [ ] Eine abstrakte Klasse kann nicht erweitert werden.
- [x] Eine abstrakte Klasse kann abstrakte **und konkrete** Methoden enthalten.
- [ ] Abstrakte Klassen können direkt instanziiert werden.

> Punkte: 1

---

## Frage 10
Welche Aussage beschreibt **Polymorphismus** korrekt?

- [x] Eine Referenzvariable des Supertyps kann ein Objekt des Subtyps referenzieren
- [ ] Ein Interface kann nur eine Implementierung haben.
- [ ] Nur abstrakte Klassen sind polymorph.
- [ ] Polymorphismus funktioniert nur mit final-Klassen.

> Punkte: 1  
> Erklärung: Polymorphie erlaubt Aufruf der Subklassen-Methoden über eine Referenz des Basistyps.

---

## Frage 11
Was ist der Unterschied zwischen **Überladen** und **Überschreiben**?

- [x] Überladen = gleiche Methode, unterschiedliche Parameter.
- [ ] Überschreiben = gleiche Methode, anderer Rückgabewert.
- [ ] Überladen = neue Methode mit anderem Namen.
- [ ] Überschreiben = Methode nur mit `static`.

> Punkte: 1  
> Erklärung: Überladen passiert in derselben Klasse, Überschreiben in Subklassen.

---

## Frage 12
Was passiert hier?

```java
class Tier {}
class Hund extends Tier {}
class Katze extends Tier {}

Tier t = new Hund();
Katze k = (Katze) t;
```

- [ ] Erfolgreiche Typumwandlung.
- [ ] Keine Ausgabe.
- [x] Laufzeitfehler (ClassCastException).
- [ ] Kompilierfehler.

> Punkte: 2  
> Erklärung: Der Compiler erlaubt den Cast, aber zur Laufzeit ist `t` kein `Katze`.

---

## Frage 13
Welche Aussage zu `instanceof` mit Pattern Matching (ab Java 14) ist korrekt?

```java
Object o = "Hallo";
if (o instanceof String s) {
    System.out.println(s.length());
}
```

- [x] Die Variable `s` ist nur im if-Block sichtbar.
- [ ] `instanceof` kann keine neue Variable erzeugen.
- [ ] Der Code ist erst ab Java 21 gültig.
- [ ] `s` überschreibt automatisch `o`.

> Punkte: 1

---

## Frage 14
Was gilt für das Schlüsselwort `final` in Bezug auf Klassen und Methoden?

- [x] Eine `final`-Klasse kann nicht erweitert werden.
- [x] Eine `final`-Methode kann nicht überschrieben werden.
- [ ] `final` verhindert Objektinstanziierung.
- [ ] `final`-Klassen sind automatisch abstrakt.

> Punkte: 1

---

## Frage 15
Welche Aussage zu `super()` im Konstruktor ist korrekt?

```java
class Tier {
    Tier(String name) { System.out.println("Tier: " + name); }
}
class Hund extends Tier {
    Hund() { super("Bello"); }
}
```

- [x] Der Konstruktor der Basisklasse wird aufgerufen, bevor der eigene Code läuft.
- [ ] `super()` ruft immer den parameterlosen Konstruktor auf.
- [ ] `super()` darf nur am Ende des Konstruktors stehen.
- [ ] Ohne `super()` wird die Basisklasse nicht initialisiert.

> Punkte: 2  
> Erklärung: `super()` muss der erste Befehl im Konstruktor sein und ruft die Elternklasse auf.

---

## Gesamtauswertung
> Gesamtpunkte: 20  
> Bestehensgrenze: 12 Punkte (60%)  
> Kategorie: OOP Grundlagen, Vererbung, Interfaces, Polymorphismus
