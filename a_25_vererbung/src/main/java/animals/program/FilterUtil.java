package animals.program;

import animals.model.Animal;

public class FilterUtil {
    // diese Methode passt (außer beim Namen) zur Signatur von
    // boolean AnimalFilter.isTrueFor(Animal a)
    // daher kann die Methode als Method Reference für eine AnimalFilter-Implementierung
    // eingesetzt werden
    public static boolean isCarnivore(Animal a){
        return  !a.isHerbivore();
    }

    // auch diese Methode passt zur Signatur
    public static boolean isHeavy(Animal a){
        return a.getWeight() >= 500;
    }
}
