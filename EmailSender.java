// Programmatically send email

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class EmailSender {
    public static void main(String[] args) {
        // Read email credentials and other information from console input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email address: ");
        String senderEmail = scanner.nextLine();

        System.out.print("Enter your email password: ");
        String senderPassword = scanner.nextLine();

        System.out.print("Enter recipient's email address: ");
        String recipientEmail = scanner.nextLine();

        System.out.print("Enter email subject: ");
        String subject = scanner.nextLine();

        System.out.println("Enter email content (press Enter and then Ctrl+D to finish):");
        StringBuilder contentBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine()).append("\n");
        }
        String emailContent = contentBuilder.toString();

        // Set up the properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            // Set the subject and text of the email
            message.setSubject(subject);
            message.setText(emailContent);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
