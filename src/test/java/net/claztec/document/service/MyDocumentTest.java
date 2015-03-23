package net.claztec.document.service;

import net.claztec.document.amqp.RabbitMQProducer;
import net.claztec.document.jms.JMSProducer;
import net.claztec.document.model.Document;
import net.claztec.document.model.Type;

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


    @Autowired
    private JMSProducer jmsProducer;


    @Test
    public void testXmlUtils() {
        log.debug("Testing XML Utils...");
        Type type = new Type();
        type.setTypeId("4980d2e4-a424-4ff4-a0b2-476039682f43");
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");
        Document document = new Document();
        document.setDocumentId(UUID.randomUUID().toString());
        document.setName("Apress Books");
        document.setLocation("http://www.apress.com");
        document.setDescription("Apress Books");
        document.setType(type);
        document.setCreated(new Date());
        document.setModified(new Date());
        String string = XmlUtils.toXML(document);
        log.debug("\n" + string);
        Document other = XmlUtils.fromXML(string,Document.class);
        assertNotNull(other);
    }

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

    @Test
    public void testSpringJMS_1() {
        log.debug("Testing Spring JMS Producer...");
        jmsProducer.send();
    }

    @Test
    public void testSpringJMS_2() throws InterruptedException {
        log.debug("Testing Spring JMS Listener/Insert...");
        assertNotNull(engine);

        Thread.sleep(5000);

        assertEquals(MAX_ALL_DOCS, engine.listAll().size());

        Type documentType = new Type("WEB", ".url");
        assertEquals(MAX_WEB_DOCS, engine.findByType(documentType).size());
    }

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Test
    public void testSpringRabbitMQ_1() {
        log.debug("Testing RabbitMQ producer...");
        assertNotNull(rabbitMQProducer);

        Document document = engine.findById(DOCUMENT_ID);
        assertNotNull(document);
        rabbitMQProducer.send(document);
    }

}