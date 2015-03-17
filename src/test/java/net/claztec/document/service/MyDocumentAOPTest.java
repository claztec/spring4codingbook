package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Derek Choi on 2015. 3. 12..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-aop-context.xml")
public class MyDocumentAOPTest {

    private static final Logger log = LoggerFactory.getLogger(MyDocumentAOPTest.class);

    @Autowired
    private SearchEngine engineProxy;

    @Autowired
    private SearchEngine engine;

    @Autowired
    private Type webType;

    @Test
    public void testUsingTestSpringAOP() {

        List<Document> documents = engineProxy.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(), documents.get(0).getType().getName());
        assertEquals(webType.getDesc(), documents.get(0).getType().getDesc());
        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());

        documents = engineProxy.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);

        try {
            engineProxy.findByLocation("/some/path/");
        } catch (Exception e) {
            log.error(" >>>>>>>>>>>>>>>>>  ERROR OCCUR >>>>>>>>>>>>>>>>>>");
            log.error(e.getMessage());
        }
    }

    @Test
    public void testUsingSpringAOPCaching() {
        log.debug("Testing Caching Module....");

        List<Document> documents = engineProxy.findByType(webType);
        assertNotNull(documents);

        int count = documents.size();

        log.debug("It should be now cached!");
        documents = engineProxy.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == count);

        log.debug("It should be now cached!");
        documents = engineProxy.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == count);

    }


    @Test
    public void testUsingSpringAOPCachingAnnotation() {
        log.debug("Testing Caching Module....");

        List<Document> documents = engine.findByType(webType);
        assertNotNull(documents);

        int count = documents.size();

        log.debug("It should be now cached!");
        documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == count);

        log.debug("It should be now cached!");
        documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == count);

    }
}
