package com.mta.jwt.demo.main;

//import com.mta.jwt.demo.config.topics.Topic;
//import com.mta.jwt.demo.config.topics.TopicRepository;

import com.mta.jwt.demo.repo.payment.model.Card;
import com.mta.jwt.demo.repo.payment.repo.CardRepo;
import com.mta.jwt.demo.repo.token.model.Token;
import com.mta.jwt.demo.repo.token.repo.TokenRepo;
import com.mta.jwt.demo.repo.user.model.User;
import com.mta.jwt.demo.repo.user.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EnableTransactionManagement
public class JpaMultipleDBIntegrationTest {

    @Autowired
    CardRepo cardRepo;

    @Autowired
    TokenRepo tokenRepo;

    @Autowired
    UserRepo userRepo;

    @Test
    public void createCard() {
        Card card = new Card();
        card.setId(123L);
        card.setName("masterCard");
        card.setExprirationMoth("27-2-9900");
        cardRepo.save(card);

        assertNotNull(cardRepo.findAll());
    }

    @Test
    public void createUser() {
        User u = new User();
        u.setUsername("ducla");
        u.setPass("cacao hao han");
        userRepo.save(u);

        assertNotNull(userRepo.findAll());
    }

    @Test
    public void createToken() {
        SimpleDateFormat sim = new SimpleDateFormat("EEEEE, d MMM yyyy HH:mm:ss Z");
        Token token = new Token();
        token.setAccessToken(sim.format(new Date()));
        token.setRefeshToken(UUID.randomUUID().toString());
        tokenRepo.save(token);
        assertNotNull(tokenRepo.findAll());
    }

//    @Autowired
//    ApplicationContext applicationContextc;
//
//    @Test
//    public void whenDecryptedPasswordNeeded_GetFromService() {
//
//
//        System.setProperty("jasypt.encryptor.password", "password");
//        PropertyServiceForJasyptSimple service = applicationContextc
//                .getBean(PropertyServiceForJasyptSimple.class);
//
//        assertEquals("Password@2", service.getProperty());
//    }
}
