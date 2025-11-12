package at.cgsit.train.java.net.p2pchat;

import java.io.*;
import java.net.*;

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
            System.out.println("[" + myName + "] lauscht auf Port " + listenPort + " ...");
            Socket socket = serverSocket.accept();
            System.out.println("Verbindung von " + socket.getRemoteSocketAddress());

            startChat(myName, socket);
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

        Socket socket = new Socket(host, port);
        System.out.println("Verbunden zu " + socket.getRemoteSocketAddress());

        startChat(myName, socket);
    }

    // ----------------- Gemeinsame Chat-Logik -----------------

    private static void startChat(String myName, Socket socket) {
        try (socket;
             BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in))) {

            // Thread zum Empfangen
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
