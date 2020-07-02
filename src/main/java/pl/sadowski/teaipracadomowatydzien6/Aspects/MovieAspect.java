package pl.sadowski.teaipracadomowatydzien6.Aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sadowski.teaipracadomowatydzien6.MoviesAPI.Controller.MovieController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Properties;

@Aspect
@Component
public class MovieAspect {


    @After(value = "@annotation(SendEmailBefore)")
    public void sendMailToMe() {
final String username = "*******@gmail.com";
final String password = "*******";
        Thread t = new Thread(() -> {

            final String to = "*******@gmail.com";
            final String host = "smtp.gmail.com";
            Properties prop = System.getProperties();
            prop.put("mail.smtp.host", host);
            prop.put("mail.smtp.port", 465);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            session.setDebug(true);
            Message message = new MimeMessage(session);
            try {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                message.setSubject("Mail Subject");
                String msg = "This is my first email using JavaMailer";
                message.setText(msg);

                Transport.send(message);


            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

}

