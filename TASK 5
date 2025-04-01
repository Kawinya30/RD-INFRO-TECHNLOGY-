import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Server started...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // Create a new client handler and start a new thread for the client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to broadcast messages to all clients
    public static void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    // ClientHandler class to handle individual client communication
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                // Get the client's name
                out.println("Enter your name: ");
                clientName = in.readLine();
                System.out.println(clientName + " has joined the chat.");

                // Broadcast the new client joining the chat
                broadcastMessage(clientName + " has joined the chat!", this);

                // Listen for messages from the client
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    }
                    broadcastMessage(clientName + ": " + message, this);
                }

                // Clean up when client exits
                clients.remove(this);
                socket.close();
                System.out.println(clientName + " has left the chat.");
                broadcastMessage(clientName + " has left the chat.", this);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Send a message to this client
        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
