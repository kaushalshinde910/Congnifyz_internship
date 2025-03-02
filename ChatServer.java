import java.io.*;
import java.net.*;
import java.util.Scanner;

// Server class
class ChatServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server started. Waiting for clients...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            Thread receiveThread = new Thread(() -> {
                String clientMessage;
                try {
                    while ((clientMessage = input.readLine()) != null) {
                        System.out.println("Client: " + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            String serverMessage;
            while (true) {
                serverMessage = scanner.nextLine();
                output.println(serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Client class
class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234)) {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            Thread receiveThread = new Thread(() -> {
                String serverMessage;
                try {
                    while ((serverMessage = input.readLine()) != null) {
                        System.out.println("Server: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            String clientMessage;
            while (true) {
                clientMessage = scanner.nextLine();
                output.println(clientMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
            
        System.err.println("welcome to kaushal chat server");
        }
    }
}
