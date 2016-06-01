package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        System.out.println("Connection to " + hostName);
        try (
                Socket clientSocket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String fromServer, fromUser;

            System.out.println("Successfully connected to " + hostName);
            System.out.println("You start conversation:\n");
            System.out.println("Client:");

            while (!(fromUser = stdIn.readLine()).equals("Bye")) {
                out.println(fromUser);
                System.out.println("Server: ");
                fromServer = in.readLine();
                System.out.println(fromServer);
                System.out.println("Client:");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
