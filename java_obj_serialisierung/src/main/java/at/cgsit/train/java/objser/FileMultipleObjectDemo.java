package at.cgsit.train.java.objser;

import at.cgsit.train.java.sharedObj.ChatMessage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileMultipleObjectDemo {

    public static void main(String[] args) {

        String filename = "message.bin";

        // 1. Objekte erzeugen
        ChatMessage msg = new ChatMessage("Wifi Kurs", "Hallo eins!");
        ChatMessage msg2 = new ChatMessage("Wifi Kurs 2 ", "Hallo zwei!");
        ChatMessage msg3 = new ChatMessage("Wifi Kurs 3", "Hallo drei!");
        List<ChatMessage> list = new ArrayList<ChatMessage>();
        list.add(msg);
        list.add(msg2);
        list.add(msg3);


        // 2. Objekt serialisiert in Datei schreiben
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filename))) {

            list.forEach( chatMessage -> {
              try {
                out.writeObject(chatMessage);
                out.flush();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
              System.out.println("Objekt gespeichert: " + chatMessage);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. Objekt wieder aus Datei lesen (deserialisieren)
        List<ChatMessage> listFromFile = new ArrayList<>();
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(filename))) {

          while (true) {
            try {
              Object o = in.readObject();
              System.out.println("Gelesen: " + o);
              listFromFile.add((ChatMessage)o);
            } catch (EOFException end) {
              break; // Ende der Datei erreicht
            }
          }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
