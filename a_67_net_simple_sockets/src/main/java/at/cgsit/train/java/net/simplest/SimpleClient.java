package at.cgsit.train.java.net.simplest;

import at.cgsit.train.java.net.constants.AppConstants;

import java.io.*;
import java.net.*;

public class SimpleClient {

    public static void main(String[] args) {

      String host = "localhost";
      // String host = "176.28.18.8";
      // String host = "0.0.0.0";

      int port = AppConstants.SERVER_PORT_IP;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Verbunden mit Server. Tippe eine Nachricht (exit zum Beenden):");
            String line;
            while ((line = userInput.readLine()) != null) {
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
                out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
