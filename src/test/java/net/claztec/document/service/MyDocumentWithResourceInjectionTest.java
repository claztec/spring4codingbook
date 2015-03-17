package net.claztec.document.service;

import net.claztec.document.model.Type;
import net.claztec.document.views.Menu;
import net.claztec.document.views.ResourceLoaderMenu;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by Derek Choi on 2015. 3. 12..
 */
public class MyDocumentWithResourceInjectionTest {

    private static final Logger log = LoggerFactory.getLogger(MyDocumentWithResourceInjectionTest.class);

    private ClassPathXmlApplicationContext context;
    private SearchEngine engine;
    private Type webType;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("META-INF/spring/mydocuments-resource-injection-context.xml");
    }

    @Test
    public void testMenu() {
        log.debug("About to read the Resource file: menu.txt");
        Menu menu = context.getBean(Menu.class);
        assertNotNull(menu);
        menu.printMenu();
    }

    @Test
    public void testMenuByResourceLoader() {
        log.debug("About to read the Resource file: menu.txt");
        ResourceLoaderMenu menu = context.getBean(ResourceLoaderMenu.class);
        assertNotNull(menu);
        menu.printMenu("classpath:META-INF/data/menu.txt");


        menu.printMenu("url:http://www.daum.net");

    }

}
