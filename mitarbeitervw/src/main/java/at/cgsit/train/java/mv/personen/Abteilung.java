package at.cgsit.train.java.mv.personen;

// während beschäftigungsart eher nur dem mitarbeiter zurechenbar ist
// wrid das enum Abteilung auch in anderen Klassen benötigt und verwendet
// abteilungen sind als Enum übergreifend daher hier extern sinnoller
public enum Abteilung {

    MARKETING(0, "Marketing"),
    PRODUKTION(1, "Produktion"),
    BUCHHALTUNG(2, "Buchhaltung"),
    IT(3,"It"),
    GF(4,"GF"),;

    private final Integer id;
    private final String name;

    Abteilung(Integer idParam, String nameParam) {
      this.id = idParam;
      name = nameParam;
   }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
