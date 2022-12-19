package com.mta.jwt.demo.main;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@PropertySource(value = "classpath:/config/db.properties")
public class TestEncode {

    @Autowired
    ApplicationContext appCtx;

    @Test
    public void whenConfiguredExcryptorUsed_ReturnCustomEncryptor() {
        Environment environment = appCtx.getBean(Environment.class);
        assertEquals(
                "Password@3",
                environment.getProperty("encryptedv3.property"));
    }

    StringEncryptor stringEncryptor;

    @Test
    public void whenConfiguredExcryptorUsed_ReturnCustomEncryptor2() {
        Environment environment = appCtx.getBean(Environment.class);
        assertEquals(
                "Password@3",
                environment.getProperty("encryptedv3.property"));
    }

    @Test
    public void whenConfiguredExcryptorUsed_ReturnCustomEncryptor3() {
        Environment environment = appCtx.getBean(Environment.class);
        assertEquals(
                "abc@123456789",
                environment.getProperty("app.datasource.payment.password"));
    }
}
