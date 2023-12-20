import java.net.InetAddress;
import java.net.UnknownHostException;

public class DNSLookup {
    public static void main(String[] args) {
        findIPAddress("www.google.com");
        findIPAddress("www.gmail.com");
        findIPAddress("www.midnaporecollege.ac.in");
    }

    private static void findIPAddress(String domainName) {
        System.out.println("DNS Lookup for: " + domainName);
        try {
            InetAddress[] addresses = InetAddress.getAllByName(domainName);

            System.out.println("IP Addresses:");
            for (InetAddress address : addresses) {
                System.out.println("  " + address.getHostAddress());
            }

            System.out.println();
        } catch (UnknownHostException e) {
            System.err.println("Error: Unable to find IP address for " + domainName);
        }
    }
}
