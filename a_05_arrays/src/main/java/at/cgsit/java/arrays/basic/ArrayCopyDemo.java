package at.cgsit.java.arrays.basic;

import java.util.Arrays;

public class ArrayCopyDemo {
    public static void main(String[] args) {

        int[] original = { 1, 2, 3, 4 };

        // 1) .clone()
        int[] clone = original.clone();

        // 2) Arrays.copyOf()
        int[] copy2 = Arrays.copyOf(original, original.length);

        // 3) System.arraycopy()
        int[] copy3 = new int[4];
        System.arraycopy(original, 0, copy3, 0, original.length);

        System.out.println("Clone: " + Arrays.toString(clone));
        System.out.println("Copy2: " + Arrays.toString(copy2));
        System.out.println("Copy3: " + Arrays.toString(copy3));
    }
}
