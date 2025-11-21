package personen;

public class PersonenMain {

	// die Personengruppe in unserem Testprogramm, ist static, dh. es gibt nur
	// eine einzige Gruppe im gesamten Programm
	private  static PersonenGruppe meineGruppe = new PersonenGruppe(5);
	public static void main(String[] args) {
		System.out.println("******************** Anmeldung ********************");
		// anmelden
		testeAnmeldung("Max", 10);
		testeAnmeldung("Moritz", 8);
		testeAnmeldung("Susi", 9);
		System.out.println("Nach 3 Anmeldungen: ");
		meineGruppe.alleAnzeigen();

		testeAnmeldung("Karli", 7);
		testeAnmeldung("Karo", 9);
		System.out.println("Nach 5 Anmeldungen: ");
		meineGruppe.alleAnzeigen();

		testeAnmeldung("Kurti", 10);
		System.out.println("Nach 6 Anmeldungen: ");
		meineGruppe.alleAnzeigen();

		System.out.println("******************** Anzeigen ********************");
		System.out.println("Eine Person anzeigen:");
		meineGruppe.anzeigen(3);
		meineGruppe.anzeigen(99);

		System.out.println("******************** Abmelden ********************");
		// abmelden
		testeAbmeldung(3);
		System.out.println("Gruppen nach Abmelden:");
		meineGruppe.alleAnzeigen();

		// Person löschen die nicht mehr vorhanden ist
		testeAbmeldung(3);
		System.out.println("Gruppen nach erfolglosem Abmelden:");
		meineGruppe.alleAnzeigen();

		// abmelden
		testeAbmeldung(2);
		System.out.println("Gruppen nach Abmelden:");
		meineGruppe.alleAnzeigen();

		System.out.println("******************** Anmeldung  ********************");
		System.out.println("Weitere Anmeldung:");
		testeAnmeldung("Kurti", 10);
		meineGruppe.alleAnzeigen();


	}

	static void testeAnmeldung(String name, int alter) {
		try {
			// die Methode ausführen, in der ein Fehler auftreten kann
			Person p = new Person(name, alter);
			// die Methode anmelden ausführen
			meineGruppe.anmelden(p);
			System.out.printf("Person angemeldet: %s\n" , p/*.toString()*/);

		} catch (Exception e) {
			System.out.println("FEHLER bei der Anmeldung:" + e/*.toString()*/);
		}
	}
	
	static void testeAbmeldung(int nr) {
		try {
			Person tmpPerson = meineGruppe.abmelden(nr);
			// wenn das Abmelden erfolgreich war => anzeigen
			System.out.println("Person abgemeldet: " + tmpPerson/*.toString()*/);
		} catch (Exception e) {
			System.out.println("FEHLER beim Abmelden:" + e/*.toString()*/);
		}
	}



}
