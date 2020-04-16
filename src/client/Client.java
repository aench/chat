package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private String text = "";

    public Client(){

        System.out.println("Siamo appena dentro a Client constructor!");

        String hostname = "localhost";
        int port = 6868;

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            Scanner scanner = new Scanner(System.in);
            ClientGUI gui = new ClientGUI(this);

            while (!text.equals("bye")) {
                System.out.println(text); // Qui succede qualcosa di poco chiaro!! Se lo togli non viene aggiornato text.s
                if (text.equals("")) {
                }
                else {
                    writer.println(text);
                    System.out.println("Ho inviato il testo: " + text);
                    if (!text.equals("bye")) {
                        String time = reader.readLine();
                        String[] history = time.split("£--£");
                        gui.refreshScreen(history);
                    }
                    text = "";
                }
                System.out.println(text); // Qui succede qualcosa di poco chiaro!
                if (text.equals("bye")){
                    System.out.println("FINE!");
                }
            }

            System.out.println("bye"); // Qui succede qualcosa di poco chiaro! Se lo ometto non va!
            socket.close();
            System.exit(0);

        } catch (UnknownHostException ex) {

            System.out.println("server.Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }

    }

    public void setText(String s){
        this.text = s;
    }

    public String getText(){
        return this.text;
    }
}
