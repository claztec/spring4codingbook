package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration("classpath:/META-INF/spring/mydocuments-jdbc-context.xml")
public class MyDocumentJDBCTest {

    private static final Logger log = LoggerFactory.getLogger(MyDocumentJDBCTest.class);

    @Autowired
    private SearchEngine engine;

    private Type webType = new Type("WEB", ".url");


    @Test
    public void testUsingSprinJDBC() {
        log.debug("Using Spring JDBC...");

        List<Document> documents = engine.listAll();
        assertNotNull(documents);
        assertTrue(documents.size() == 4);


        documents = engine.findByType(webType);
        assertNotNull(documents);
        assertTrue(documents.size() == 1);
        assertEquals(webType.getName(), documents.get(0).getType().getName());

        assertEquals(webType.getExtension(), documents.get(0).getType().getExtension());

       log.debug("Found WEB Document: " + documents.get(0));
    }



}
