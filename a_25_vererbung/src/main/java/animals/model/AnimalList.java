package animals.model;

/**
 * diese Klasse enthält ein Array von Animal-Objekten.
 * 
 * @author Michaela
 *
 */
public class AnimalList {

	private Animal[] allAnimals = new Animal[] {
			new Animal("Löwe", "Panthera leo", 250, false),
			new Animal("Waschbär", "Procyon", 12, false),
			new Animal("Bison", "Bison bison", 950, true),
			new Animal("Elefant", "Loxodonta", 4000, true),
			new Animal("Eisbär", "Ursus maritimus", 650, false),
			new Animal("Emu", "Dromaius novaehollandiae", 37, false)

	};

	public void showAll() {
		for (int i = 0; i < allAnimals.length; i++) {
			System.out.println(allAnimals[i]);
		}
		System.out.println();
	}

	public void showAnimals(AnimalFilter filter) {
		for(Animal animal : allAnimals){
			// wenn das Tier laut Filter-Objekt angezeigt werden soll
			// (dh wenn die Filter-Implementierung für das Tier "true" geliefert hat)
			if(filter.isTrueFor(animal)) {
				System.out.println(animal);
			}
		}

		System.out.println();
	}

	// die spezialisierten Methoden werden durch Aufrufe von  showAnimals
	// mit einem passenden AnimalFilter-Objekt ersetzt
	/*public void showVegetarians() {
		for(Animal animal : allAnimals){
			if(animal.isHerbivore()) {
				System.out.println(animal);
			}
		}

		System.out.println();
	}

	public void showHeavyAnimals() {
		for(Animal animal : allAnimals){
			if(animal.getWeight() >= 500) {
				System.out.println(animal);
			}
		}

		System.out.println();
	}*/

}
