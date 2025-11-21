package animals.program;

import animals.model.Animal;
import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram_MethodenReferenzen {
    public static void main(String[] args) {
        System.out.println("AnimalFilter mit Methoden-Referenzen");
        AnimalList animals = new AnimalList();
        System.out.println("Alle Tiere:");
        animals.showAll();

        // 1. Art: statische Methode in einer Klasse
        AnimalFilter filter1;
        // als Lambda Expression
        // filter1 = a -> FilterUtil.isCarnivore(a);
        // als Method reference
        filter1 = FilterUtil::isCarnivore;
        System.out.println("Fleischfresser:");
        animals.showAnimals(filter1);

        System.out.println("Schwere Tiere:");
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(FilterUtil::isHeavy);
        // mit Lambda Expression würde es so aussehen:
        // animals.showAnimals(a -> FilterUtil.isHeavy(a));

        // 2. Art: Instanz-Methode in einer Klasse
        // Objekt erzeugen
        FilterHelper helperObjekt = new FilterHelper();
        System.out.println("Vegetarier:");
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(helperObjekt::isVegetarian);
        // mit Lambda Expression:
        // animals.showAnimals(a -> helperObjekt.isVegetarian(a));

        System.out.println("Leichte Tiere:");
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(helperObjekt::isLightweight);
        // mit Lambda Expression:
        // animals.showAnimals(a -> helperObjekt.isLightweight(a));


        // 3. Art: Instanz-Methode in einer Klasse mit "arbiträrem" Objekt
        // dh ein Objekt aus der Parameterliste wird für den Aufruf der
        // Methode verwendet
        System.out.println("Herbivoren:");
        // Method reference als AnimalFilter-Implementierung
        animals.showAnimals(Animal::isHerbivore);
        // mit Lambda Expression:
        // animals.showAnimals(a -> a.isHerbivore());

    }


}


