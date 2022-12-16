package com.mta.jwt.demo.main;

//import com.mta.jwt.demo.config.topics.Topic;
//import com.mta.jwt.demo.config.topics.TopicRepository;

import com.mta.jwt.demo.payment.model.Card;
import com.mta.jwt.demo.payment.repo.CardRepo;
import com.mta.jwt.demo.token.model.Token;
import com.mta.jwt.demo.token.repo.TokenRepo;
import com.mta.jwt.demo.user.model.User;
import com.mta.jwt.demo.user.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
        Token token = new Token();
        token.setAccessToken(UUID.randomUUID().toString());
        token.setRefeshToken(UUID.randomUUID().toString());
        tokenRepo.save(token);

        assertNotNull(tokenRepo.findAll());

    }
}
