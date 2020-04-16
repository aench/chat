package server;

import observer.Observer;
import observer.Subject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<Observer> observers = new ArrayList<Observer>();
    private String subjectState;

    public Server(){
        System.out.println("I've created a server");
    }

    public void run(){

        int port = 6868;
        subjectState = "";

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);

            while (true) {

                Socket socket = serverSocket.accept();

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String type = reader.readLine();
                System.out.println("New Client connected with type " + type);
                if (type.equals("Reader")){
                    System.out.println("We are inside type: " + type);
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    // Aspetta!
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException intrx) {System.out.println("Errore! " + intrx.getMessage());}
                    // Aspetta!
                    System.out.println("I send to the client the history");
                    writer.println(this.subjectState);

                }
                else if (type.equals("Writer")){
                    System.out.println("We are inside type: " + type);
                    String text = reader.readLine();
                    // Aspetta!
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException intrx) {System.out.println("Errore! " + intrx.getMessage());}
                    // Aspetta!
                    System.out.println("I received the text: " + text);
                    this.subjectState = this.subjectState + "£--£" + text;
                }
                else {
                    System.out.println("Error! Unknown type: " + type);
                }

            }

        } catch (IOException ex) {
            System.out.println("server.Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
