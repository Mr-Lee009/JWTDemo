package com.mta.jwt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ThymeleafService thymeleafService;

    public void SendMail() {
        String body = "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  </head>\n" +
                "  <body style=\"font-family: sans-serif;\">\n" +
                "    <div style=\"display: block; margin: auto; max-width: 600px;\" class=\"main\">\n" +
                "      <h1 style=\"font-size: 18px; font-weight: bold; margin-top: 20px\">Congrats for sending test email with Mailtrap!</h1>\n" +
                "      <p>Inspect it using the tabs you see above and learn how this email can be improved.</p>\n" +
                "      <img alt=\"Inspect with Tabs\" src=\"https://assets-examples.mailtrap.io/integration-examples/welcome.png\" style=\"width: 100%;\">\n" +
                "      <p>Now send your email using our fake SMTP server and integration of your choice!</p>\n" +
                "      <p>Good luck! Hope it works.</p>\n" +
                "    </div>\n" +
                "    <!-- Example of invalid for email html/css, will be detected by Mailtrap: -->\n" +
                "    <style>\n" +
                "      .main { background-color: white; }\n" +
                "      a:hover { border-left-width: 1em; min-height: 2em; }\n" +
                "    </style>\n" +
                "  </body>\n" +
                "</html>\n" +
                "\n" +
                "--boundary-string--\n" +
                "EOF";
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo("anhduc2781997@gmail.com");
//        simpleMailMessage.setSubject("mail test");
//        simpleMailMessage.setText(body);
//        simpleMailMessage.setText(body);


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "2525");
        props.put("mail.smtp.starttls.enable", "false");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("195a694c825e8c", "ec0ed295dae1bf");
                    }
                });

        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("ducla@gmail.com")});

            message.setFrom(new InternetAddress("sender_email@gmail.com"));
            message.setSubject("Spring-email-with-thymeleaf subject");
            message.setContent(thymeleafService.getContent(), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
//        try {
//            mailSender.send(simpleMailMessage);
//        }catch (Exception e){
//            System.err.println(e);
//        }

    }
}
