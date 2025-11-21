package at.cgsit.train.java.net.echo_srv;

import at.cgsit.train.java.net.constants.AppConstants;

import java.io.*;
import java.net.*;

public class EchoServer {

    public static void main(String[] args) {

        int port = AppConstants.SERVER_PORT_IP;
        System.out.println("EchoServer startet auf Port " + port + " ...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client verbunden: " + clientSocket.getInetAddress());

                try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("[Server empfängt] " + line);
                        out.println("Echo: " + line);
                    }
                } catch (Exception ex) {
                    System.out.println("Client hat abgebrochen" );
                }
        }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
