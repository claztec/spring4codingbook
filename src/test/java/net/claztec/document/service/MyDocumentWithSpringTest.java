package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import net.claztec.document.test.profile.CustomProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/mydocuments-custom-profile-context.xml")
@ProfileValueSourceConfiguration(CustomProfile.class)
public class MyDocumentWithSpringTest {

    private static final Logger log = LoggerFactory.getLogger(MyDocumentWithSpringTest.class);

    @Autowired
    private SearchEngine engine;

    @Autowired
    private Type webType;

    @IfProfileValue(name = "environment", value="dev")
    @Test
    public void testUsingSpringTestWithCustomProfilesX() {
        log.debug("Using Spring Test fixtures:");
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

    @IfProfileValue(name = "os.name", value = "Unix")
    @Timed(millis=2000)
    @Test
    public void testUsingSpringTestWithCustomProfilesY() {
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
    }

    @Test
    @Repeat(10)
    public void testUsingSpringRepeatedAnnotationTest() {
        log.debug("This message should be printed 10 times...");
    }

}
