package at.cgsit.train.functional;

import java.util.function.Consumer;

public class FunctionMain {

  static void main() {

    Consumer<String> drucker = System.out::println;
    drucker.accept("Hallo Welt!");



  }

}
