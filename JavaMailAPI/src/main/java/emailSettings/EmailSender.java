package emailSettings;

import database.Connector;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

public class EmailSender {
    private static ArrayList<String> arrayList;

    public static void sendMail (String recipient) {
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

    private static ArrayList<String> getRecipientMail() {
        return Connector.runConnect();
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            for (String s : arrayList)
                message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(s)});

            message.setSubject("Test 3");
            message.setText("Test text 3");

            System.out.println("Email go db");

            return message;
        }
        catch (MessagingException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        arrayList = getRecipientMail();
        EmailSender.sendMail("@gmail");
    }
}

