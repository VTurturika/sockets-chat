package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int portNumber = Integer.parseInt(args[0]);

        System.out.println("Waiting clients ...");
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {

            System.out.println("Client successfully connected!\nStart conversation:\n");
            String fromServer, fromUser;
            System.out.println("Server: ");

            while ( !(fromServer = stdIn.readLine()).equals("Bye") ) {
                out.println(fromServer);

                if( (fromUser = in.readLine()) != null )
                System.out.println("Client:\n" + fromUser);
                System.out.println("Server: ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
