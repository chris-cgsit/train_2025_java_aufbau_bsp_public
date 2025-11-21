package at.cgsit.train.interf;

import at.cgsit.train.java.objects.Buch;

public class AnnonymesIFImpl {

  public interface BuchFilter {
    boolean isTrueFor(Buch a);
  }


  static void main() {

    BuchFilter filter = new BuchFilter() {

      @Override
      public boolean isTrueFor(Buch a) {
        return false;
      }
    };


  }

}
