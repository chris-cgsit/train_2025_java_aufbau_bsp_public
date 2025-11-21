package at.cgsit.java.arrays.basic;

public class BasicArrayDemo {

    public static void main(String[] args) {

        // Ein Array mit fixer Länge
        int[] numbers = new int[5];

        // Werte setzen
        numbers[0] = 10;
        numbers[1] = 20;

        // Direktes Literal-Array
        String[] names = { "Anna", "Bob", "Chris" };

        // Länge des Arrays
        System.out.println("Länge names: " + names.length);

        // Zugriff per Index
        System.out.println("Element 1: " + names[1]);

        // Iteration mit klassischem for
        for (int i = 0; i < names.length; i++) {
            System.out.println("Index " + i + " → " + names[i]);
        }
    }
}
