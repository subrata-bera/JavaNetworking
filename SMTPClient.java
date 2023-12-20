// write a network program using java to Implement Simple Mail Transfer Protocol (SMTP)

import java.io.*;
import java.net.Socket;

public class SMTPClient {

    public static void main(String[] args) {
        String smtpServer = "smtp.example.com"; // Replace with your SMTP server
        int smtpPort = 25; // Default SMTP port

        String sender = "your.email@example.com"; // Replace with your email address
        String recipient = "recipient.email@example.com"; // Replace with recipient's email address
        String subject = "Test Email";
        String body = "This is a test email sent using a simple SMTP client.";

        try {
            // Connect to the SMTP server
            Socket socket = new Socket(smtpServer, smtpPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Read the server's greeting message
            String response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the EHLO command
            sendCommand(writer, "EHLO example.com");

            // Send the MAIL FROM command
            sendCommand(writer, "MAIL FROM: <" + sender + ">");

            // Send the RCPT TO command
            sendCommand(writer, "RCPT TO: <" + recipient + ">");

            // Send the DATA command
            sendCommand(writer, "DATA");

            // Send the email content
            sendCommand(writer, "Subject: " + subject);
            sendCommand(writer, "");
            sendCommand(writer, body);
            sendCommand(writer, ".");

            // Send the QUIT command
            sendCommand(writer, "QUIT");

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
