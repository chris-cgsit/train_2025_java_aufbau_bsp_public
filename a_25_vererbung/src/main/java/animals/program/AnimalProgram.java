package animals.program;


import animals.model.AnimalFilter;
import animals.model.AnimalList;

public class AnimalProgram {

	public static void main(String[] args) {
		
		AnimalList animals = new AnimalList();
		System.out.println("Alle Tiere:");
		animals.showAll();
		System.out.println("Vegetarier:");
		// statt spezialisierter Methode
		//animals.showVegetarians();
		HerbivoreFilter filter1 = new HerbivoreFilter();
		// implizite Umwandlung von HerbivoreFilter nach AnimalFilter
		// (dh. Umwandlung von implementierender Klasse nach Basisinterface)
		animals.showAnimals(filter1);

		// Tiere schwerer als 200 kg
		System.out.println("Schwere Tiere:");
		//animals.showHeavyAnimals();
		// implizite Umwandlung von HeavyAnimalFilter nach AnimalFilter
		AnimalFilter filter2 = new HeavyAnimalFilter(200);
		animals.showAnimals(filter2);

		// Tiere schwerer als 500 kg
		System.out.println("Schwere Tiere ( >= 500kg)");
		// implizite Umwandlung von HeavyAnimalFilter nach AnimalFilter
		animals.showAnimals(new HeavyAnimalFilter(500));
	
	}

}


