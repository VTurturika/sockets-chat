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
            System.out.println("Start conversation:\n");

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server:\n" + fromServer);
                if (fromServer.equals("Bye"))
                    break;

                System.out.println("Client: ");
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
