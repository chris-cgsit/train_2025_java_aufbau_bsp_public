package at.cgsit.train.java.net.p2pchat;

import java.io.*;
import java.net.*;

/**
 * Peer2PeerChat – Einfache Chat-Anwendung für Java
 *
 * Modi:
 *  Server: java Peer2PeerChat server <meinName> <listenPort>
 *  Client: java Peer2PeerChat client <meinName> <host> <port>
 *
 * Der Server akzeptiert nacheinander beliebig viele Clients,
 * kann aber immer nur mit einem gleichzeitig chatten.
 *
 * Beide Seiten können gleichzeitig senden und empfangen.
 */

public class Peer2PeerChat {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Verwendung:");
      System.out.println("  Server: java Peer2PeerChat server <meinName> <listenPort>");
      System.out.println("  Client: java Peer2PeerChat client <meinName> <host> <port>");
      return;
    }

    String mode = args[0];

    try {
      if ("server".equalsIgnoreCase(mode)) {
        runServer(args);
      } else if ("client".equalsIgnoreCase(mode)) {
        runClient(args);
      } else {
        System.out.println("Unbekannter Modus: " + mode);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // ───────────────────────────────────────────────
  // SERVER
  // ───────────────────────────────────────────────

  private static void runServer(String[] args) throws IOException {
    if (args.length < 3) {
      System.out.println("Server-Verwendung: java Peer2PeerChat server <meinName> <listenPort>");
      return;
    }

    String myName = args[1];
    int listenPort = Integer.parseInt(args[2]);

    try (ServerSocket serverSocket = new ServerSocket(listenPort)) {
      System.out.println("[" + myName + "] Lauscht dauerhaft auf Port " + listenPort + " ...");

      while (true) {
        System.out.println("Warte auf neuen Client ...");

        try {
          Socket socket = serverSocket.accept();
          System.out.println("Verbindung von " + socket.getRemoteSocketAddress());
          startServerChat(myName, socket);

        } catch (IOException e) {
          System.out.println("Fehler beim Client-Handling: " + e.getMessage());
        }
      }
    }
  }

  // Server-chat: Server kann chatten, aber hängt nicht.
  private static void startServerChat(String myName, Socket socket) {
    try (socket;
         BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

      System.out.println("Chat gestartet (SERVER chattet).");

      // Empfangsthread
      Thread recvThread = new Thread(() -> {
        try {
          String line;
          while ((line = socketIn.readLine()) != null) {
            System.out.println("Client: " + line);
          }
          System.out.println("Client hat Verbindung geschlossen.");
        } catch (IOException e) {
          System.out.println("Empfangsfehler: " + e.getMessage());
        }
      });
      recvThread.setDaemon(true);
      recvThread.start();

      // Sende-Thread
      Thread sendThread = new Thread(() -> {
        try {
          String line;
          while ((line = consoleIn.readLine()) != null) {
            if ("exit".equalsIgnoreCase(line.trim())) break;
            if (!socket.isClosed() && socket.isConnected()) {
              socketOut.println(myName + ": " + line);
            } else {
              break;
            }
          }
        } catch (IOException e) {
          System.out.println("Sende-Fehler: " + e.getMessage());
        }
      });
      sendThread.setDaemon(true);
      sendThread.start();

      // Warte bis Empfang endet → Client ist weg
      recvThread.join();

      System.out.println("Server-Chat beendet (Client getrennt).");
      sendThread.interrupt();

    } catch (Exception e) {
      System.out.println("Server-Chat-Fehler: " + e.getMessage());
    }
  }


  // ───────────────────────────────────────────────
  // CLIENT
  // ───────────────────────────────────────────────

  private static void runClient(String[] args) throws IOException {
    if (args.length < 4) {
      System.out.println("Client-Verwendung: java Peer2PeerChat client <meinName> <host> <port>");
      return;
    }

    String myName = args[1];
    String host = args[2];
    int port = Integer.parseInt(args[3]);

    System.out.println("[" + myName + "] verbindet zu " + host + ":" + port + " ...");

    Socket socket = new Socket(host, port);
    System.out.println("Verbunden zu " + socket.getRemoteSocketAddress());

    startClientChat(myName, socket);
  }

  private static void startClientChat(String myName, Socket socket) {
    try (socket;
         BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

      System.out.println("Chat gestartet. Tippe Nachrichten, 'exit' zum Beenden.");

      // Empfangsthread
      Thread recvThread = new Thread(() -> {
        try {
          String line;
          while ((line = socketIn.readLine()) != null) {
            System.out.println("Server: " + line);
          }
          System.out.println("Server hat Verbindung geschlossen.");
        } catch (IOException e) {
          System.out.println("Empfangsfehler: " + e.getMessage());
        }
      });
      recvThread.setDaemon(true);
      recvThread.start();

      // Senden im Hauptthread
      String line;
      while ((line = consoleIn.readLine()) != null) {
        if ("exit".equalsIgnoreCase(line.trim())) break;
        socketOut.println(myName + ": " + line);
      }

      System.out.println("Chat beendet.");

    } catch (IOException e) {
      System.out.println("Chat-Fehler: " + e.getMessage());
    }
  }
}
