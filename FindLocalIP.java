
// Find IP address of your local host

import java.net.InetAddress;
import java.net.UnknownHostException;

public class FindLocalIP {
    public static void main(String[] args) {
        try {
            // Get the local host
            InetAddress localHost = InetAddress.getLocalHost();

            // Get the IP address
            String localIP = localHost.getHostAddress();

            System.out.println("Local IP address: " + localIP);
        } catch (UnknownHostException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
