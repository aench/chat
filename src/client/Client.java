package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private String clientHistory = "Default";
    String hostname = "localhost";
    int port = 6868;
    String clientType = "Reader";
    ClientGUI gui;

    public Client() {
        this.gui = new ClientGUI(this);
    }
    public void listen() {
        while (true) {
            // Aspetta!
            try {
                Thread.sleep(100);
            } catch (InterruptedException intrx) {System.out.println("Errore! " + intrx.getMessage());}
            // Aspetta!
            try (Socket socket = new Socket(hostname, port)) {

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(this.clientType);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                System.out.println("Aspetto di ricevere risposta dal Server");
                String answer = reader.readLine();
                System.out.println("Ho ricevuto: " + answer);
                this.clientHistory = answer;
                gui.refreshScreen(clientHistory);

                socket.close();

            } catch (UnknownHostException ex) {

                System.out.println("server.Server not found: " + ex.getMessage());

            } catch (IOException ex) {

                System.out.println("I/O error: " + ex.getMessage());
            }

        }

    }

}
