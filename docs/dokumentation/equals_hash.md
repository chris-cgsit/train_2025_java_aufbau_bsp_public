# Person-Beispiel: UUID (String) + Gleichheit nur über `id`

Dieses Dokument zeigt eine `Person`-Klasse, deren **fachliche Identität ausschließlich durch die `id` (UUID als String)** bestimmt wird.
Felder `vorname` und `name` sind **nicht** Teil von `equals()`/`hashCode()`.

---

## Ziele

* `id` ist eine UUID (als `String`) und **final**.
* `equals()` prüft: **Identität (`==`) → Typ → `id` gleich**.
* `hashCode()` basiert **nur auf `id`**.
* Änderungen an `vorname` / `name` beeinflussen die Gleichheit **nicht**.

---

## Implementierung

```java
import java.util.Objects;
import java.util.UUID;

public class Person {

    private final String id;   // UUID als String, fachliche Identität
    private String vorname;
    private String name;

    // Primärer Konstruktor: erwartet gültige UUID als String
    public Person(String id, String vorname, String name) {
        this.id = validateUuid(id);
        this.vorname = vorname;
        this.name = name;
    }

    // Bequeme Factory: generiert neue UUID
    public static Person neu(String vorname, String name) {
        return new Person(UUID.randomUUID().toString(), vorname, name);
    }

    private static String validateUuid(String id) {
        Objects.requireNonNull(id, "id (UUID) darf nicht null sein");
        // Validierung erzwingen (wirft IllegalArgumentException bei Ungültigkeit)
        UUID.fromString(id);
        return id;
    }

    public String getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Gleichheit NUR über id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                 // gleiche Referenz
        if (!(o instanceof Person)) return false;   // gleicher Typ?
        Person person = (Person) o;
        return Objects.equals(this.id, person.id);  // nur id vergleicht
    }

    // Konsistent zu equals: hash nur aus id
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{id=" + id + ", vorname=" + vorname + ", name=" + name + "}";
    }
}
```

---

## Verwendungsbeispiele

### Set/Map-Verhalten (Duplikatserkennung nur über `id`)

* Zwei `Person`-Objekte mit **gleicher `id`**, aber unterschiedlichem `vorname`/`name`, gelten als **gleich** → im `Set` bleibt **nur eins**.
* In einer `Map<Person, …>` überschreibt ein Eintrag mit **gleicher `id`** einen vorhandenen.

### Suche in `List<Person>`

* `list.contains(new Person(id, "x","y"))` → **true**, wenn `id` übereinstimmt (Namen egal).

---

## Warum nur `id` in `equals/hashCode`?

* **Stabilität**: `id` ändert sich nie; `vorname`/`name` sind veränderlich (mutable).
  → Mutable Felder in `hashCode()` wären gefährlich (Objekte könnten „im Set verschwinden“).
* **Fachliche Identität**: In vielen Domänen identifiziert eine UUID das Objekt eindeutig.

---

## Hinweise & Best Practices

* **Konsistenz**: Wenn `equals()` nur die `id` nutzt, **muss** `hashCode()` genau das auch tun.
* **Validieren**: Beim Erzeugen die `UUID` prüfen (wie gezeigt), um Datenfehler früh zu finden.
* **Immutability der ID**: `id` als `final` belassen; niemals Setter anbieten.
* **Persistenz/DTOs**: Für JPA/Serialisierung ggf. No-Args-Konstruktor ergänzen (protected) und die `id` danach setzen – fachlich aber nicht ändern.

---

## Kurzfazit

* **Identität = `id`** (UUID als String).
* **`equals()`/`hashCode()` = nur `id`**.
* **Namen sind rein darstellend** und verändern die Gleichheit nicht.
