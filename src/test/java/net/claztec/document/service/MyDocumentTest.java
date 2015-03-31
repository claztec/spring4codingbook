package net.claztec.document.service;

import net.claztec.document.amqp.RabbitMQProducer;
import net.claztec.document.email.EmailService;
import net.claztec.document.jms.JMSProducer;
import net.claztec.document.model.Document;
import net.claztec.document.model.Type;

import net.claztec.document.social.DocumentTweet;
import net.claztec.document.test.profile.CustomProfile;
import net.claztec.document.utils.XmlUtils;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyDocumentTest {


    //Based on the META-INF/data/jms.txt - only one record
    private static final int MAX_ALL_DOCS = 5;
    private static final int MAX_WEB_DOCS = 2;
    private static final String DOCUMENT_ID = "df569fa4-a513-4252-9810-818cade184ca";


    private static final Logger log = LoggerFactory.getLogger(MyDocumentTest.class);

    @Autowired
    private SearchEngine engine;

    @Autowired
    private Type webType;


    @Test
    @Ignore
    public void testUsingSpringTest() {
        log.debug("Using Spring Test fixtures on Unix:");
        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(), documents.get(0).getType().getName());
        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());

        documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);
    }

    @Test
    public void testAll() {
        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(), documents.get(0).getType().getName());
        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());

        documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);

        for (Document doc:documents) {
            log.debug("id:" + doc.getDocumentId());
        }
    }

    @Autowired
    EmailService email;

    @Test
//    @Repeat(5)
    public void testEmail() {
        log.debug("Testing Email...");
        assertNotNull(log);

        long start = new Date().getTime();
        email.send("claztec@gmail.com", "claztec@hanmail.net", "테스트 메일",  "샘플 이메일.\n 메일 발송이 잘 되나.");
        long end = new Date().getTime();
        long time = (end - start) / 1000;
        log.debug("Sending email done. Took: " + time + " seconds.");
    }

    @Test
//    @Repeat(5)
    public void testAsyncEmail() throws InterruptedException, ExecutionException {
        log.debug("Testing Email...");
        assertNotNull(log);

        long start = new Date().getTime();
        Future result = email.sendAsync("claztec@gmail.com", "claztec@hanmail.net", "Async 테스트 메일", "샘플 이메일.\n 메일 발송이 잘 되나. \n 비동기로 메일 발송.");
//        log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> RESULT " + result.get() + " >>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>> RESULT: " + result.get());
        long end = new Date().getTime();
        long time = (end - start) / 1000;
        log.debug("Sending email done. Took: " + time + " seconds.");
//        Thread.sleep(10000);

    }

    @Test
    public void testInfinity() {
        long start = new Date().getTime();
        email.infinityLoop("THREAD-1");
        long end = new Date().getTime();
        long time = (end - start) / 1000;
        log.debug("Infinity loop. Took: " + time + " seconds.");
    }

    @Test
    public void testAsyncInfinity() throws InterruptedException, ExecutionException {
        long start = new Date().getTime();
        Future result = email.asyncInfinityLoop("THREAD-1");
        System.out.println(">>>>>> RESULT: " + result.get());
        long end = new Date().getTime();
        long time = (end - start) / 1000;
        log.debug("Infinity loop. Took: " + time + " seconds.");

    }

    @Test
    public void testScheduler() throws InterruptedException {
        Thread.sleep(45000);
    }

    @Autowired
    DocumentTweet documentTweet;

    @Test
    public void testTweet() {
        log.debug("Testing Spring Social....");
        assertNotNull(documentTweet);
        documentTweet.tweet("Playing with Spring Social!!");

    }

}