package at.cgsit.java.arrays.basic;

public class ArrayReferenceDemo {

    public static void main(String[] args) {

        int[] a = { 1, 2, 3 };
        int[] b = a;   // KEINE Kopie → gleiche Referenz

        b[1] = 99;

        System.out.println(a[1]); // 99
    }
}
