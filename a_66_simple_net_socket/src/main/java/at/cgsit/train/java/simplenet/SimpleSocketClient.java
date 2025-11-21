package at.cgsit.train.java.simplenet;

import java.io.*;
import java.net.*;

public class SimpleSocketClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Hallo vom Client!");        // Nachricht senden
            String reply = in.readLine();            // Antwort empfangen
            System.out.println("Antwort: " + reply);
        }
    }
}
