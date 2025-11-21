package at.cgsit.train.java.net.echo_srv;

import at.cgsit.train.java.net.constants.AppConstants;

import java.io.*;
import java.net.*;

public class EchoClient {

    public static void main(String[] args) {
        String host = "localhost";
        // String host = "176.28.18.8";

        int port = AppConstants.SERVER_PORT_IP;

        try (Socket socket = new Socket(host, port);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Verbunden mit Server. Tippe eine Nachricht (exit zum Beenden):");

            String line;
            while ((line = userInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line)) break;
                out.println(line);                     // an Server senden
                String echo = in.readLine();           // Echo vom Server empfangen
                System.out.println("[Client erhält] " + echo);
                System.out.println("Nächste Nachricht bitte: " );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
