


```java
import java.util.*;

Set<String> namen = new HashSet<>();
namen.add("Anna");
namen.add("Bob");
namen.add("Anna");  // Duplikat wird ignoriert

System.out.println(namen);   // z. B. [Bob, Anna]
System.out.println("Anzahl: " + namen.size());

// Sortiertes Set
Set<String> sortiert = new TreeSet<>(namen);
System.out.println(sortiert);  // [Anna, Bob]

```

