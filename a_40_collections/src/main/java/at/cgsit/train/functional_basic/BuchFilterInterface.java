package at.cgsit.train.functional_basic;


import at.cgsit.train.java.objects.Buch;


// Ein Functional Interface ist ein Interface mit genau einer abstrakten Methode.
// Es beschreibt eine Funktion – hier eine Prüfung für ein Buch.
// isTrueFor() gibt true/false zurück.
// Das Interface sagt nur „ich prüfe ein Buch“, keine Details wie geprüft wird.
@FunctionalInterface
public interface BuchFilterInterface {

  boolean isTrueFor(Buch a);

}
