// Implement Post Office Protocol (POP)

import java.io.*;
import java.net.Socket;

public class POPClient {

    public static void main(String[] args) {
        String popServer = "pop.example.com"; // Replace with your POP server
        int popPort = 110; // Default POP port

        String username = "your_username"; // Replace with your POP username
        String password = "your_password"; // Replace with your POP password

        try {
            Socket socket = new Socket(popServer, popPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String response = reader.readLine();
            System.out.println("Server: " + response);

            sendCommand(writer, "USER " + username);
            sendCommand(writer, "PASS " + password);
            sendCommand(writer, "STAT");
            sendCommand(writer, "LIST");
            sendCommand(writer, "RETR 1");
            sendCommand(writer, "QUIT");

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendCommand(BufferedWriter writer, String command) throws IOException {
        System.out.println("Client: " + command);
        writer.write(command + "\r\n");
        writer.flush();
    }
}
