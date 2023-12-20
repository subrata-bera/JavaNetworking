// write a network program using java to Programmatically send email with CC, BCC & attachment

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;

public class EmailSenderWithAttachment {
    public static void main(String[] args) {
        // Read email credentials and other information from console input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your email address: ");
        String senderEmail = scanner.nextLine();

        System.out.print("Enter your email password: ");
        String senderPassword = scanner.nextLine();

        System.out.print("Enter recipient's email address: ");
        String recipientEmail = scanner.nextLine();

        System.out.print("Enter CC recipient's email address (comma-separated if multiple): ");
        String ccEmails = scanner.nextLine();

        System.out.print("Enter BCC recipient's email address (comma-separated if multiple): ");
        String bccEmails = scanner.nextLine();

        System.out.print("Enter email subject: ");
        String subject = scanner.nextLine();

        System.out.print("Enter email content (press Enter and then Ctrl+D to finish): ");
        StringBuilder contentBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            contentBuilder.append(scanner.nextLine()).append("\n");
        }
        String emailContent = contentBuilder.toString();

        System.out.print("Enter the path to the file you want to attach: ");
        String filePath = scanner.nextLine();

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

            // Set CC recipients
            if (!ccEmails.isEmpty()) {
                String[] ccAddresses = ccEmails.split(",");
                for (String ccAddress : ccAddresses) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress.trim()));
                }
            }

            // Set BCC recipients
            if (!bccEmails.isEmpty()) {
                String[] bccAddresses = bccEmails.split(",");
                for (String bccAddress : bccAddresses) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bccAddress.trim()));
                }
            }

            // Set the subject and text of the email
            message.setSubject(subject);

            // Create the email body with attachment
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(emailContent);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the file
            if (!filePath.isEmpty()) {
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(source.getName());
                multipart.addBodyPart(attachmentBodyPart);
            }

            // Set the content of the email
            message.setContent(multipart);

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
