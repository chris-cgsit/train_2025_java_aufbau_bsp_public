package animals.model;

// So würde das Interface als  generische Schnittstelle aussehen
// wenn wir es für Animal-Objekte brauchen, nehmen wir GenericFilter<Animal>
// wenn wir es für String-Objekte brauchen, nehmen wir GenericFilter<String>
public interface GenericFilter<T> {
    boolean isTrueFor(T obj);
}
