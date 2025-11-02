package at.cgsit.train.java.mv.firma.schnittstellen;

import at.cgsit.train.java.mv.personen.Person;

import java.util.List;
import java.util.Optional;

/**
 * Interface zur Definition von Methoden zur Verwaltung von Personen.
 */
public interface PersonManager {

    /**
     * Fügt eine neue Person der Liste hinzu.
     *
     * @param p Die hinzuzufügende Person.
     */
    void addPerson(Person p);


    /**
     * Entfernt eine Person anhand ihrer ID durch manuelles Iterieren über die Liste.
     *
     * @param id Die ID der zu entfernenden Person.
     * @return true, wenn eine Person mit der ID entfernt wurde, ansonsten false.
     */
    boolean removeById(String id);

    /**
     * Sucht eine Person anhand ihrer ID.
     *
     * @param id Die ID der gesuchten Person.
     * @return Ein Optional-Objekt mit der gefundenen Person oder leer, wenn keine gefunden wurde.
     */
    Optional<Person> findById(String id);

    /**
     * Sucht Personen anhand eines Teilstrings im Namen.
     *
     * @param teil Ein Teilstring, der im Namen der Person gesucht werden soll.
     * @return Eine Liste von Personen, deren Namen den Teilstring enthalten.
     */
    List<Person> findByNachname(String teil);
}
