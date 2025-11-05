package at.cgsit.train.a_streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SteamBspOne {

  static void main() {

    List<String> namen = List.of("Anna", "Bob", "Alexander", "Carla");

    namen.stream()
        .filter(name -> name.startsWith("A"))
        // .map(name -> name.toUpperCase())
        .map(String::toUpperCase)
        .forEach(System.out::println);
  }
}
