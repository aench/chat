package server;

import observer.ConcreteSubject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server extends ConcreteSubject {

    public static void main(String[] args) {

        int port = 6868;
        String history = "";

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New Client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                //writer.println(new Date().toString());

                String text;

                do {
                    text = reader.readLine();

                    if (!text.equals("bye")){
                        // System.out.println("Received: " + text);
                        history = history + "£--£" + text;
                        // System.out.println("History: " + history);
                        writer.println(history);
                    }

                } while (!text.equals("bye"));

                socket.close();

            }

        } catch (IOException ex) {
            System.out.println("server.Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

}
