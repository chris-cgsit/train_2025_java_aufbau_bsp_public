package at.cgsit.train.java.net.p2pchat;

import java.io.*;
import java.net.*;

/**
 * The Peer2PeerChat class implements a simple peer-to-peer chat application
 * that can operate in either server or client mode. The application uses
 * sockets for communication and allows users to exchange text messages.
 *
 * In server mode, it listens for incoming connections on a specified port.
 * In client mode, it connects to a specified server and port.
 *
 * Usage:
 * - Server: java SimpleSocketChat server <myName> <listenPort>
 * - Client: java SimpleSocketChat client <myName> <host> <port>
 *
 * Examples:
 * - Server: java SimpleSocketChat server ServerUser 5000
 * - Client: java SimpleSocketChat client ClientUser 176.28.18.8 5000
 *
 * The communication is :
 * - A dedicated thread to receive messages.
 * - A console input system to send messages.
 * - A "exit" command to close the chat session.
 */
public class Peer2PeerChat {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Verwendung:");
            System.out.println("  Server: java SimpleSocketChat server <meinName> <listenPort>");
            System.out.println("  Client: java SimpleSocketChat client <meinName> <host> <port>");
            System.out.println();
            System.out.println("Beispiele:");
            System.out.println("  Server: java SimpleSocketChat server ServerUser 5000");
            System.out.println("  Client: java SimpleSocketChat client ClientUser 176.28.18.8 5000");
            return;
        }

        String mode = args[0];

        // entscheide server oder client mode.
        // der server soll auf clients warten bis sie connecten. der client sucht den server dann
        try {
            if ("server".equalsIgnoreCase(mode)) {
                runServer(args);
            } else if ("client".equalsIgnoreCase(mode)) {
                runClient(args);
            } else {
                System.out.println("Unbekannter Modus: " + mode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ----------------- Server-Modus -----------------

  private static void runServer(String[] args) throws IOException {
    if (args.length < 3) {
      System.out.println("Server-Verwendung: java SimpleSocketChat server <meinName> <listenPort>");
      return;
    }

    String myName = args[1];
    int listenPort = Integer.parseInt(args[2]);

    try (ServerSocket serverSocket = new ServerSocket(listenPort)) {
      System.out.println("[" + myName + "] Lauscht dauerhaft auf Port " + listenPort + " ...");

      // server akzeptiert nacheinander beliebig viele Clients, aber nie gleichzeitig!
      while (true) {
        System.out.println("Warte auf neuen Client ...");
        try {
          Socket socket = serverSocket.accept();
          System.out.println("Verbindung von " + socket.getRemoteSocketAddress());

          // chat für *diesen* Client ausführen
          startChat(myName, socket);

          // sobald startChat beendet wird:
          System.out.println("Client getrennt. Warten auf nächsten...");
        } catch (IOException e) {
          System.out.println("Fehler beim Client-Handling: " + e.getMessage());
        }
      }
    }
  }


    // ----------------- Client-Modus -----------------

    private static void runClient(String[] args) throws IOException {
        if (args.length < 4) {
            System.out.println("Client-Verwendung: java SimpleSocketChat client <meinName> <host> <port>");
            return;
        }

        String myName = args[1];
        String host = args[2];
        int port = Integer.parseInt(args[3]);

        System.out.println("[" + myName + "] verbindet zu " + host + ":" + port + " ...");

        // der client sucht den server und verbindet sich einfach
        Socket socket = new Socket(host, port);
        System.out.println("Verbunden zu " + socket.getRemoteSocketAddress());

        startChat(myName, socket);
    }

    // START ----------------- Gemeinsame Chat-Logik -----------------
    // wir lesen incommint aus socketIn je LINE !!
    // wir schreiben ausgehend auf socket out --je LINE
    // wir lesen von der Console input vom user ..
    // und wir schreiben auf die console den server output raus
    private static void startChat(String myName, Socket socket) {
        try (socket;
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            // Thread zum Empfangen läuft im hintergrund und schreibt uns auf die console immer wenn was kommt
            Thread receiveThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = socketIn.readLine()) != null) {
                        System.out.println("Peer: " + line);
                    }
                    System.out.println("Verbindung wurde von der Gegenstelle geschlossen.");
                } catch (IOException e) {
                    System.out.println("Empfangsfehler: " + e.getMessage());
                }
            });
            receiveThread.setDaemon(true);
            receiveThread.start();

            // hier im haupt thread machen wir weiter und lesen den user input und senden es dem server bzw
            // der socket gegenstelle.
            // wenn ein socket verbunden ist, dann ist er bi-direktonal
            System.out.println("Chat gestartet. Tippe Nachrichten, 'exit' zum Beenden.");

            String line;
            while ((line = consoleIn.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                socketOut.println(myName + ": " + line);
            }

            System.out.println("Chat beendet.");

        } catch (IOException e) {
            System.out.println("Chat-Fehler: " + e.getMessage());
        }
    }
}
