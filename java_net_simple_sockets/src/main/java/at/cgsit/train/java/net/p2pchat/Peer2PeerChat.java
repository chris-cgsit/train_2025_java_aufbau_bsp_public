package at.cgsit.train.java.net.p2pchat;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Peer-to-Peer 1:1 Chat <br/>
 * (beide Seiten sind Server und Client).
 *
 * Start auf zwei Terminals (Beispiel):
 *   statt localhost die
 *   Terminal A: java PeerChatUser Alice 5000 localhost 5001 Bob
 *   Terminal B: java PeerChatUser Bob   5001 localhost 5000 Alice
 *
 * Befehle im Chat:
 *   exit  -> Chat beenden
 */
public class Peer2PeerChat {

    // ANSI-Farben (funktioniert meistens)
    private static final String RESET = "\u001B[0m";
    private static final String FG_SELF = "\u001B[36m";   // Cyan
    private static final String FG_PEER = "\u001B[35m";   // Magenta
    private static final String FG_SYS  = "\u001B[90m";   // Grau

    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        if (args.length < 5) {
            // # Args: myName listenPort targetHost targetPort peerName [bindHost]
            System.out.println("Verwendung: java Peer2PeerChat <meinName> <listenPort> <targetHost> <targetPort> <peerName>");
            System.out.println("Beispiel A: java Peer2PeerChat Chris 5000 0.0.0.0 5001 Bob 0.0.0.0");
            System.out.println("Beispiel B: java Peer2PeerChat Frank 5001 0.0.0.0 5000 Alice 0.0.0.0");
            return;
        }

        final String myName = args[0];
        final int listenPort = Integer.parseInt(args[1]);
        final String targetHost = args[2];
        final int targetPort = Integer.parseInt(args[3]);
        final String peerName = args[4];

        // optionales 6. Argument
        // 0.0.0.0 = auf allen Interfaces lauschen (auch über LAN erreichbar).
        final String bindHost = (args.length >= 6) ? args[5] : "0.0.0.0";
        final Thread listenerThread = new Thread(() -> listenAndReceive(myName, bindHost, listenPort, peerName));
        listenerThread.setName("listener-" + listenPort);
        listenerThread.start();

        // Kleiner Delay/Sleep, damit der Listener hochfährt, bevor wir verbinden
        try { Thread.sleep(600); } catch (InterruptedException ignored) {}

        connectAndSend(myName, targetHost, targetPort, peerName);

        // Aufräumen
        try { listenerThread.join(500); } catch (InterruptedException ignored) {}
        logSys("Beendet.");
    }

  // listenAndReceive mit bind host .. wo lauschen
  private static void listenAndReceive(String myName, String bindHost, int listenPort, String peerName) {
    try (ServerSocket serverSocket = new ServerSocket()) {
      serverSocket.bind(new InetSocketAddress(bindHost, listenPort)); // "0.0.0.0" = alle Interfaces
      logSys("[" + myName + "] lauscht auf " + bindHost + ":" + listenPort + " …");
      try (Socket socket = serverSocket.accept();
           BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

        logSys("Eingehende Verbindung: " + socket.getInetAddress() + ":" + socket.getPort());
        String line;
        while ((line = in.readLine()) != null) {
          printPeer(peerName, line);
        }
        logSys("Gegenstelle hat die Verbindung geschlossen.");
      }
    } catch (IOException e) {
      logSys("Listener-Fehler: " + e.getMessage());
    }
  }


    private static void connectAndSend(String myName, String host, int port, String peerName) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in))) {

            logSys("Verbunden zu " + host + ":" + port + " (Peer: " + peerName + ")");
            System.out.println(FG_SYS + "Tippe Nachrichten. 'exit' beendet." + RESET);

            String line;
            while (true) {
                System.out.print(prompt(myName));
                line = userIn.readLine();
                if (line == null || "exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                out.println(line);
                printSelf(myName, line);
            }
        } catch (IOException e) {
            logSys("Sende-Fehler: " + e.getMessage());
        }
    }

    // ---------- hübsche Ausgabe ----------

    private static String now() { return LocalTime.now().format(TS); }

    private static void printSelf(String myName, String msg) {
        System.out.println(FG_SELF + "[" + now() + "] " + myName + ": " + msg + RESET);
    }

    private static void printPeer(String peerName, String msg) {
        System.out.println(FG_PEER + "[" + now() + "] " + peerName + ": " + msg + RESET);
    }

    private static void logSys(String msg) {
        System.out.println(FG_SYS + "[" + now() + "] " + msg + RESET);
    }

    private static String prompt(String myName) {
        return FG_SELF + myName + "> " + RESET;
    }
}
