package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_Lambdas {
    public static void main(String[] args) {
        System.out.println("AnimalFilter mit Lambda Expressions");
        AnimalList animals = new AnimalList();
        System.out.println("Alle Tiere:");
        animals.showAll();
        // Lambda expression mit Parametertyp und Block-Klammern
        AnimalFilter filter1 = (Animal a) -> {
            return !a.isHerbivore();
        };

//        // kürzer ginge es so:
//        filter1 = a -> !a.isHerbivore();
        System.out.println("Fleischfresser:");
        animals.showAnimals(filter1);

        // Lambda expression ohne Block-Klammern, direkt als Parameter übergeben
        System.out.println("Leichte Tiere:");
        animals.showAnimals(a -> a.getWeight() < 500);


    }
}
