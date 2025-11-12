package at.cgsit.train.java.chatsys;

import at.cgsit.train.java.chatsys.demotest.DemoUserGenerator;
import at.cgsit.train.java.chatsys.exceptions.ProfantitatException;
import at.cgsit.train.java.chatsys.model.ChatMessage;
import at.cgsit.train.java.chatsys.model.ChatUser;
import at.cgsit.train.java.chatsys.model.MessageTyp;
import at.cgsit.train.java.chatsys.server.ChatServer;

import java.time.Instant;
import java.util.*;

/**
 * Simuliert einen relativ einfachen Chat-Server mit Usern und Nachrichten.
 * Keine Netzwerkfunktionalität – alles läuft in der Konsole.
 */
public class ChatDemoMain {

  public static void main(String[] args) {
    ChatServer server = new ChatServer();
    // Alle Demo-User generieren
    List<ChatUser> demoUsers = DemoUserGenerator.generateAll();

    System.out.println("füge user dem server hinzu: \n");

    for (ChatUser user : demoUsers) {
      server.addUser(user);
    }

    System.out.println("\n liste alle user vom server sortiert nach namen auf: \n");

    List<ChatUser> sortedUsersByName = server.getSortedUsersByName();
    sortedUsersByName
        .stream()
        .forEach( chatUser ->  System.out.printf("User: %s \n", chatUser.getUsername()));


    ChatUser first =  demoUsers.get(0);
    ChatUser second =  demoUsers.get(1);

    try {
      // erlaubt
      ChatMessage m1 = new ChatMessage(first.getStringId(),second.getStringId(), "Hallo zusammen!");
      server.sendMessage(m1);

      // verboten (enthält Schimpfwort)
      ChatMessage m2 = new ChatMessage(second.getStringId(), first.getStringId(), "Du Dummkopf!");
      server.sendMessage(m2);
    } catch (ProfantitatException e) {
      System.out.println("[Server] SENDEN FEHLGESCHLAGEN: " + e.getMessage());
      // könnte wieder weiter
      // throw e;
    }

    try {
      // Tippfehler-Variante "Iditot" ist ebenfalls verboten
      ChatMessage m3 = new ChatMessage(first.getStringId(), second.getStringId(), "Sei kein Iditot, bitte.");
      server.sendMessage(m3);
    } catch (ProfantitatException e) {
      System.out.println("[Server] SENDEN FEHLGESCHLAGEN: " + e.getMessage());
    }

    try {
      server.sendSystemMessage("Server wird in 10 Minuten neu gestartet.");
    } catch (ProfantitatException e) {
      System.out.println("[Server] System-Nachricht blockiert: " + e.getMessage());
    }

    // Historie ausgeben (nur erfolgreiche Sends)
    System.out.println("\n=== Chat-Historie ===");
    for (ChatMessage msg : server.getHistory()) {
      System.out.printf("%s | sender=%s | content='%s'%n",
          msg.getTimestamp(), msg.getSenderId(), msg.getContent());
    }
  }

}
