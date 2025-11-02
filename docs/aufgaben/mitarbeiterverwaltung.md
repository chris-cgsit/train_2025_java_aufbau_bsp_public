# 🧑‍💼 Hausübung: Mitarbeiterverwaltung

## 🎯 Ziel

Implementiere eine kleine **Personenverwaltung für eine Firma**.

Dabei soll das Konzept von **Vererbung und Polymorphie** in Java angewendet werden.  
Es werden verschiedene Typen von Personen verwaltet, die gemeinsame Eigenschaften besitzen.

---

## 🧱 Aufgabenbeschreibung

### 1. Klassenhierarchie

Erstelle eine **Basisklasse `Person`**.

Leite davon zwei Klassen ab:

- `Mitarbeiter`
- `Kunde`

Jede Klasse soll sinnvolle Felder (Attribute) besitzen, z. B.:

- `Vorname`, `Nachname`, `E-Mail`
- `Abteilung`, `Gehalt`, `Kundennummer`, `Umsatz` usw.

**Anforderungen:**

- Verwende **Kapselung** (private Felder, Getter/Setter)
- Überschreibe:
    - `toString()`
    - `equals()` / `hashCode()` (z. B. basierend auf `id`)

---

### 2. Klasse `Firma`

Erstelle eine Klasse `Firma`, die alle Personen verwalten kann.

**Pflichtmethoden:**
```java
void addPerson(Person p)
boolean removeById(String id)
Optional<Person> findById(String id)
List<Person> findByName(String teil)
List<Mitarbeiter> mitarbeiterNachAbteilung(String abt)
double durchschnittsGehalt() // nur für Mitarbeiter
Map<String, Long> anzahlMitarbeiterProAbteilung()
double gesamtUmsatzKunden()

```

## 3. Test / Beispiel

Implementiere eine kleine Main-Methode in einer Klasse DemoMain,
um die wichtigsten Funktionen zu demonstrieren (z. B. Personen hinzufügen, suchen, ausgeben).
 
## Hinweise zur Umsetzung

Nutze sinnvolle Default-Werte oder Konstruktoren

IDs können automatisch mit UUID.randomUUID() vergeben werden

Beachte sauberen Umgang mit null (z. B. Objects.requireNonNull)

Gib Listen z. B. mit System.out.println() oder for-Schleifen aus

Verwende List, Map, Optional aus java.util

##  Bonusideen (optional)

Erweitere das Projekt mit zusätzlichen Funktionen oder Techniken:

##  Thema und weitere Ideen

| 🧩 Thema | 💡 Idee |
|-----------|----------|
| **Validierung** | Überprüfe z. B. E-Mail-Format, Gehalt > 0, Umsatz ≥ 0 |
| **Persistenz** | Export/Import der Daten als CSV oder JSON |
| **Suche** | Implementiere eine `startsWith`- oder fuzzy-Suche |
| **Tests** | Schreibe Unit-Tests mit JUnit für die Klasse `Firma` |
| **Interfaces** | Z. B. `Kontaktierbar`-Interface mit Methode `getEmail()` |
| **Enums** | Definiere `Abteilung` oder `Beschäftigungsart` als `enum` |
| **Sortierung** | Sortiere Personen nach Nachname, Abteilung oder Umsatz |
| **Statistik** | Zeige Durchschnittswerte, Summen oder Anzahlen an |




# Lernziele

- Anwendung von Vererbung und Polymorphie
- Arbeiten mit Generics und Collections
- Nutzen von Streams und Lambdas (optional)
- Strukturierte Objektmodellierung
- Grundlegende Objektverwaltung (CRUD)

# Beispielhafter Aufbau
```text
src/main/java
 └── at/
     └── cgsit/
         └── kurs/
             └── mv/ // für mitarbeiterverwaltung
                 ├── Person.java
                 ├── Mitarbeiter.java
                 ├── Kunde.java
                 ├── Firma.java
                 └── DemoMain.java

```

Alles Gute für die Übungen !! 