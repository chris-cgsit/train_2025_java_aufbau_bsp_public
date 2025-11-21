package at.cgsit.train.a_streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamSummeProAbteilung {

    public static void main(String[] args) {

        List<Person> personen = List.of(
            new Person("Anna", "IT", 65000),
            new Person("Bob", "IT", 52000),
            new Person("Carla", "HR", 48000),
            new Person("Alexander", "IT", 74000),
            new Person("Bea", "HR", 51000)
        );

        // Summiere das Gehalt je Abteilung
        Map<String, Double> summeProAbteilung =
            personen.stream()
                    .collect(Collectors.groupingBy(
                        Person::abteilung,
                        Collectors.summingDouble(Person::gehalt)
                    ));

      System.out.println("Gesamtgehalt pro Abteilung:");

        summeProAbteilung.forEach((abteilung, summe) ->
            System.out.println(abteilung + " → " + summe)
        );
    }

    public record Person(String name, String abteilung, double gehalt) { }
}



