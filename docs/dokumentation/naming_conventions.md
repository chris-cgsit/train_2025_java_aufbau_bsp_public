# Java Namenskonventionen (Naming Conventions) für Interfaces 📜

In der Java-Programmierung gibt es **klare und etablierte Konventionen** für die Benennung von Interfaces. Das Ziel ist es, Code lesbar, einheitlich und verständlich zu machen.

Die wichtigste Regel: **Vermeiden Sie unnötige Präfixe oder Suffixe** wie `Interface` oder `I`.

---

## 🏆 Empfohlene Praxis: Substantive ohne Suffix

Benennen Sie Ihr Interface nach dem **Konzept, der Entität oder der Fähigkeit**, die es repräsentiert.

| Empfohlen | Nicht empfohlen |
| :--- | :--- |
| **Firma** | `FirmaInterface` |
| **List** | `IList` |
| **UserDao** | `UserDaoInterface` |
| **Document** | `IDocument` |

### Beispielcode

```java
// Empfohlen: Benennung nach dem Zweck
public interface Firma {
    void fuehreGeschaefte();
    String getFirmenname();
}

// Die implementierende Klasse macht den Interface-Typ durch 'implements' klar
public class MeineFirma implements Firma {
    // ... Implementierung ...
}

```


💡 Warum diese Konvention?
Kürze und Lesbarkeit: Der Code wird weniger redundant.

Fokus auf den Zweck: Der Name beschreibt was der Typ ist, nicht wie er technisch deklariert wurde (als Interface).

Standard der Java-Bibliothek: Die Java Standardbibliothek hält sich an diese Regel (List, Map, Runnable).

✨ Sonderfall: Fähigkeits-Interfaces
Interfaces, die eine Fähigkeit oder Eigenschaft beschreiben, enden oft auf -able oder -ible (Suffixe, die "fähig zu..." bedeuten).
- Runnable (fähig zu laufen)
- Serializable (fähig serialisiert zu werden)
- Comparable (fähig verglichen zu werden)

---

### Weiterführende Links (für Ihre eigene Recherche)

Hier sind die Links, die im Markdown-Inhalt enthalten sind, noch einmal separat:

1.  **Oracle Code Conventions - Naming Conventions (Abschnitt 9)** (Ursprüngliche Konventionen):
    * [https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
2.  **Google Java Style Guide** (Moderner Stil):
    * [https://google.github.io/styleguide/javaguide.html#s5-naming](https://google.github.io/styleguide/javaguide.html#s5-naming)
3.  **Baeldung Tutorial - Java Interface Naming Conventions** (Zusammenfassung):
    * [https://www.baeldung.com/java-interface-naming-conventions](https://www.baeldung.com/java-interface-naming-conventions)

---

