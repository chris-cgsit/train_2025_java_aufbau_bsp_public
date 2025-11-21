package at.cgsit.java.arrays.basic;

public class ArrayIsObjectDemo {

    public static void main(String[] args) {

        int[] arr = { 10, 20, 30 };

        Class<?> clazz = arr.getClass();

        System.out.println("Klassenname: " + clazz.getName());
        System.out.println("Ist Array?  " + clazz.isArray());
        System.out.println("Komponententyp: " + clazz.getComponentType());
    }
}
