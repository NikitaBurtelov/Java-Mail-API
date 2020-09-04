import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {
    public static void sendMail (String recipient) throws MessagingException {
        String myAccountEmail = DataRetrieval.getJsonObject("accountGmail");
        String password = DataRetrieval.getJsonObject("password");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recipient);

        try {
            assert message != null;
            Transport.send(message);
        }
        catch (MessagingException exception) {
            exception.printStackTrace();
        }

    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(recipient)));

            message.setSubject("Test 2");
            message.setText("Test text 2");

            System.out.println("Email go");

            return message;
        }
        catch (MessagingException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws MessagingException {
        EmailSender.sendMail("recipient");
    }
}

