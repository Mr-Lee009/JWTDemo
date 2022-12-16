package com.mta.jwt.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MaillConfig {

//    play.mailer {
//        host = "smtp.mailtrap.io"
//        port = 2525
//        ssl = no
//        tls = yes
//        user = "a95e04e9f6f6f8"
//        password = "1c9889c0291a90"
//    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);

        // mail FROM
        mailSender.setUsername("195a694c825e8c");
        mailSender.setPassword("ec0ed295dae1bf");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
