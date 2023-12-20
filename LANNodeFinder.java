
// Find ID address of all nodes connected to your LAN

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class LANNodeFinder {
    public static void main(String[] args) {
        try {
            // Get all network interfaces on the machine
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            // Iterate through each network interface
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                System.out.println("Interface: " + networkInterface.getDisplayName());

                // Get all IP addresses associated with the network interface
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                // Iterate through each IP address
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("  IP Address: " + inetAddress.getHostAddress());
                }

                System.out.println(); // Separate each interface's output
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
