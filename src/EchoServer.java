
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author gdicaterina
 */
public class EchoServer {

    public static void main(String[] args) throws IOException {
        //parms
        /*
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port>");
            System.exit(1);
        }*/

        //int portNumber = Integer.parseInt(args[0]);
        int portNumber = 12001;
        try (
                //ServerSocket serverSocket =
                //new ServerSocket(Integer.parseInt(args[0]));
                ServerSocket serverSocket
                = new ServerSocket(portNumber);) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Accept incoming connections
                // Start a service thread
                Thread.ofVirtual().start(() -> {
                    try (
                            PrintWriter out
                            = new PrintWriter(clientSocket.getOutputStream(), true); BufferedReader in = new BufferedReader(
                                    new InputStreamReader(clientSocket.getInputStream()));) {
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            System.out.println(inputLine);
                            out.println(inputLine);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
