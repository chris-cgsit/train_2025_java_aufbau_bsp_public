package at.cgsit.train.java.fileio;

import java.io.*;

public class ReadFileSimple {
    public static void main(String[] args) {

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String filename = "Textfile1.txt";
        String outputFileName = "Copy_Textfile1.txt";

        File textfile = new File(filename);
        if( !textfile.exists() ) {
            System.out.println("file does not exist: " + textfile.getName() + " \n");
            System.out.println("file does not exist: " + textfile.getAbsolutePath());
            // verboten in allen application servern
            // System.exit(1);
            // return;
        }

        try (FileInputStream fis = new FileInputStream(textfile);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int b; // einzelnes Byte

            while ((b = fis.read()) != -1) {   // -1 bedeutet Ende der Datei
                baos.write(b);                // in Memory speichern
            }

            // Byte Array in String (UTF-8)
            // US-ASCII
            // "UTF-8");
            String content = baos.toString("UTF-8");

            System.out.println("Dateiinhalt:");
            System.out.println(content);


            File outfile = new File(outputFileName);

            /* if( outfile.exists()) {
                System.out.println("deleting file" );
                boolean delete = outfile.delete();
                System.out.println("outfile deleted: " + delete );
            }
             */

            // Now write the content of ByteArrayOutputStream to a new file:
            // wenn FileOutputStream append true, dann wird appended:
            try (FileOutputStream fos = new FileOutputStream(outfile, true)) {
                // file output stream can write the string buffer:
                byte[] byteArray = baos.toByteArray();
                fos.write(byteArray);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
