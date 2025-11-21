package at.cgsit.java.arrays.mit.personen;

public class PersonenMainSimple {
    public static void main(String[] args) {
        Person[] gruppe = new Person[5];
        // ein paar Indices verwenden
        gruppe[0] = new Person("Max", 10);
        gruppe[1] = new Person("Moritz", 8);
        gruppe[3] = new Person("Susi", 12);

        for (int i = 0; i < gruppe.length; i++) {
            Person tmpPerson = gruppe[i];
            // wenn an der Stelle eine Referenz auf ein Objekt vorhanden ist
            if (tmpPerson != null) {
                System.out.printf("Person am Index %d: %s\n", i, tmpPerson.getName());
            } else {
                System.out.printf("Person am Index %d: nicht vorhanden\n", i);
            }
        }

    }
}
