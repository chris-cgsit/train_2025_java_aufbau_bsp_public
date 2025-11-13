package at.cgsit.train.java.net.simplest;

import at.cgsit.train.java.net.constants.AppConstants;

import java.io.*;
import java.net.*;

/**
 *
 * In der aktuellen Implementierung dieses SimpleServer-Codes kann nur ein einziger Client gleichzeitig bedient werden,
 * und alle weiteren Clients müssen warten.
 * <br/>
 * Der Standard: Der ServerSocket-Konstruktor, den Sie hier verwenden (new ServerSocket(port)),
 * enutzt eine Standard-Warteschlangengröße (Backlog-Wert), die vom Betriebssystem abhängt
 * (typischerweise liegt dieser Wert oft zwischen 50 und 200).
 * <br>
 * Der zweite, dritte oder n-te Client muss warten, bis der erste Client seine Übertragung beendet hat
 * und die Verbindung geschlossen wird.
 */
public class SimpleServer {

    public static void main(String[] args) {
      int port = AppConstants.SERVER_PORT_IP;
        System.out.println("Server startet auf Port " + port + " ...");

      //Vorbereitung: Der Server startet und öffnet einen ServerSocket auf einem vordefinierten Port
      // (hier: AppConstants.SERVER_PORT_IP).

      // Sicherheit und Aufräumen: Wir verwenden hier Try-with-Resources (try(...)).
      // Das garantiert, dass die Netzwerkverbindungen (ServerSocket und BufferedReader) automatisch und sicher geschlossen werden,
      // selbst wenn Fehler (IOException) auftreten.

      try (ServerSocket serverSocket = new ServerSocket(port)) {

        // man könnte ein timeout configurieren: Warten maximal 5 Sekunden (5000 ms) auf eine Verbindung
        // serverSocket.setSoTimeout(5000);

        // Die Methode serverSocket.accept() hält den Server an und wartet, bis ein Client versucht, eine Verbindung herzustellen.
        // Sobald ein Client "anruft", wird eine Socket-Verbindung hergestellt.
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client verbunden: " + clientSocket.getInetAddress());

            // Nach der Verbindung liest der Server die eingehenden Daten (Zeile für Zeile) über einen BufferedReader aus dem InputStream des Clients und gibt sie auf der Konsole aus.
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()))) {

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("[Server empfängt] " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
