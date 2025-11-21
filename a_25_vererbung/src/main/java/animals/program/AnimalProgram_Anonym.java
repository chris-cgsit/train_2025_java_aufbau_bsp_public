package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_Anonym {
    public static void main(String[] args) {
        System.out.println("AnimalFilter mit anonymen Klassen");
        AnimalList animals = new AnimalList();
        System.out.println("Alle Tiere:");
        animals.showAll();
        System.out.println("Vegetarier:");
        // AnimalFilter mit anonymer Klasse
        // statt Instanz der separat definierten Klasse
        // HerbivoreFilter filter1 = new HerbivoreFilter();
        // Instanz einer anonymen Klasse
        AnimalFilter filter1 = new AnimalFilter() {
            @Override
            public boolean isTrueFor(Animal a) {
                return a.isHerbivore();
            }
        };
        animals.showAnimals(filter1);

        // direkt als Argument übergeben
        System.out.println("Schwere Tiere:");
        animals.showAnimals(new AnimalFilter() {
            @Override
            public boolean isTrueFor(Animal a) {
                return a.getWeight() >= 500;
            }
        });

    }
}
