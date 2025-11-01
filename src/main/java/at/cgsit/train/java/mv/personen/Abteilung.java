package at.cgsit.train.java.mv.personen;

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
