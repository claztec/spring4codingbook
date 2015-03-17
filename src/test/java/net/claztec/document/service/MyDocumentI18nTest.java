package net.claztec.document.service;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * Created by Derek Choi on 2015. 3. 13..
 */
public class MyDocumentI18nTest {
    public static final Logger log = LoggerFactory.getLogger(MyDocumentWithLoginTest.class);


    private ClassPathXmlApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-i18n-context.xml");
    }

    @Test
    public void testMenu() {

//        http://lahuman.jabsiri.co.kr/m/post/87

        log.debug("About to Translate..");
        String english = context.getMessage("main.title", null, Locale.ENGLISH);
        String korean = context.getMessage("main.title", null, Locale.KOREAN);
        log.debug("English:" + english);
        log.debug("Korean:" + korean);
    }
}
