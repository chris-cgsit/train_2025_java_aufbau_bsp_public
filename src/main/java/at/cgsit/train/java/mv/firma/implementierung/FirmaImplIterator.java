package at.cgsit.train.java.mv.firma.implementierung;

import at.cgsit.train.java.mv.firma.Firma;
import at.cgsit.train.java.mv.personen.Person;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementierung mit Schleifen und Iteratoren.
 */
public class FirmaImplIterator extends Firma {

    @Override
    public boolean removeById(String id) {
        for (int i = 0; i < personen.size(); i++) {
            if (personen.get(i).getId().equals(id)) {
                personen.remove(i);
                return true;
            }
        }
        return false;
    }

  /**
   * NOCH ein weiteres Beispiel für ein REMOVE by IT
   * hier wird statt der for schleife ein Iterator verwendet
   * @param id
   * @return
   */
  public boolean removeByIdWithIterator(String id) {
    Iterator<Person> iterator = personen.iterator();
    while (iterator.hasNext()) {
      Person person = iterator.next();
      if (person.getId().equals(id)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }



    @Override
    public Optional<Person> findById(String id) {
        for (Person person : personen) {
            if (person.getId().equals(id)) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findByName(String teil) {
        return personen.stream()
                       .filter(person -> (person.getVorname() + " " + person.getNachname())
                                         .toLowerCase()
                                         .contains(teil.toLowerCase()))
                       .collect(Collectors.toList());
    }
}
