package at.cgsit.train.interf;

public interface TestInterface {

  Integer VAL = 0;

  final Integer VAL_FINAL = 0;

  static void doitStatic () {
     System.out.println("TestInterface doitStatic");
  }

  default void doitInstanceImpld()  {
    System.out.println("TestInterface doitInstanceImpl");
  }


  public void doitInstance () ;


}
