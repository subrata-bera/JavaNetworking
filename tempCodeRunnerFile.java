public static void main(String[] args) {
        String popServer = "pop.example.com"; // Replace with your POP server
        int popPort = 110; // Default POP port

        String username = "your_username"; // Replace with your POP username
        String password = "your_password"; // Replace with your POP password

        try {
            // Connect to the POP server
            Socket socket = new Socket(popServer, popPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Read the server's greeting message
            String response = reader.readLine();
            System.out.println("Server: " + response);

            // Send the USER command
            sendCommand(writer, "USER " + username);

            // Send the PASS command
            sendCommand(writer, "PASS " + password);

            // Send the STAT command to get the number of messages
            sendCommand(writer, "STAT");

            // Send the LIST command to list all messages
            sendCommand(writer, "LIST");

            // Send the RETR command to retrieve the first message
            sendCommand(writer, "RETR 1");

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
