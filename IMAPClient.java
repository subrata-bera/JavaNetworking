// Implement Internet Message Access Protocol (IMAP)

import java.io.*;
import java.net.Socket;

public class IMAPClient {

    public static void main(String[] args) {
        String imapServer = "imap.example.com"; // Replace with your IMAP server
        int imapPort = 143; // Default IMAP port

        try {
            // Connect to the IMAP server
            Socket socket = new Socket(imapServer, imapPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Read the server's greeting message
            String response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the LOGIN command
            sendCommand(writer, "LOGIN your_username your_password");

            // Send the LIST command to list all mailboxes
            sendCommand(writer, "LIST \"\" *");

            // Send the SELECT command to select the inbox
            sendCommand(writer, "SELECT INBOX");

            // Send the FETCH command to retrieve the first message
            sendCommand(writer, "FETCH 1 BODY[]");

            // Send the LOGOUT command
            sendCommand(writer, "LOGOUT");

            // Close the socket
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
