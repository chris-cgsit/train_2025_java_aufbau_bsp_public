package at.cgsit.train.java.mv.firma;

import at.cgsit.train.java.mv.personen.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakte Basisklasse FirmaBase verwaltet gemeinsame Eigenschaften
 * und grundlegende Logik für sämtliche Implementierungen der Firma.
 */
public abstract class FirmaBase {

  private final static int INTIAL_CAPACITY = 100;

  // wir verwenden hier statt dem native array gleich einen "Abstrakten Datentypen"
  // Dieses Interface Liste wird von der Klasse ArrayList implementiert bereits Hilfsfunktionen
  // um das Einfügen, Suchen oder Löschen einfacher zu machen
  // und um uns das Problem des Speichermanagements eines fixen native Arrays abzunehmen
  // der List Speicher für die Slots des Arrays wird also intern von der Liste verwaltet und ggf vergrössert.
  // Wir können die initiale Kapazität hier gleich beim Konstruktor mitgeben.
  // inital bedeutet aber der Speicher wird bei Bedarf vergrössert
  protected List<Person> personen = new ArrayList<>(INTIAL_CAPACITY); // Gemeinsame Personenliste

  protected String firmenNamen;

  // da eine Liste bereits mit Objekten arbeitet ist die Deklation und Initialiserung
  // mit native Datentypen nicht mehr möglich:
  // protected final List<int> intList = new ArrayList<int>(); // !!!! Compilerfehler
  // protected final List<Integer> intList = new ArrayList<>(); // in Ordnung
  // int value = intList.get(0);  // Integer → automatisch unboxed zu int


  public FirmaBase(String firmenNamen) {
    if( firmenNamen == null || firmenNamen.equals("") ) {
      throw new RuntimeException("Der FirmenName nicht null sein");
    }
    this.firmenNamen = firmenNamen;
  }

  // der Firmenname ist nur lesbar und wird bei der Erzeugung der Firma via Konstruktor fixiert
  public String getFirmenNamen() {
    return firmenNamen;
  }



   // wir behalten add person hier direkt da das einfügen immer gleich ist
    public void addPerson(Person p) {
        personen.add(p);
    }

}
