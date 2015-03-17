package net.claztec.document.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Derek Choi on 2015. 3. 13..
 */
public class MyDocumentWithLoginTest {

    public static final Logger log = LoggerFactory.getLogger(MyDocumentWithLoginTest.class);

    public static final String EMAIL = "test@mydocuments.com";
    public static final String PASS = "test123";
    public static final String SUCCESS = "This user is authorized";
    public static final String FAILURE = "WARNING! This user is not authorized!";

    private ClassPathXmlApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-login-context.xml");
    }

    @Test
    public void testLogin() {
        log.debug("Login Test");
        Login login = context.getBean(Login.class);
        assertNotNull(login);
        if (login.isAuthorized(EMAIL, PASS)) {
            log.debug(SUCCESS);
        } else {
            log.debug(FAILURE);
        }
    }
}
