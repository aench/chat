package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientScript {

    static String text = "";

    public static void main(String[] args) {

        Client client = new Client();
        client.listen();

    }
}
