package at.cgsit.train.java.fileio;

import java.io.File;

public class ListRecursive {

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        System.out.println("Start directory: " + workingDir);

        File root = new File(workingDir);
        listFilesRecursive(root, 0);
    }

    // rekursive Methode
    private static void listFilesRecursive(File dir, int level) {
        File[] files = dir.listFiles();
        if (files == null) return;

        // Einrückung für optische Baumstruktur
        String indent = "  ".repeat(level);

        for (File f : files) {
            if (f.isDirectory()) {
                System.out.println(indent + "[DIR]  " + f.getName());
                listFilesRecursive(f, level + 1); // REKURSION
            } else {
                System.out.println(indent + "[FILE] " + f.getName());
            }
        }
    }
}
