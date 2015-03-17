package net.claztec.document.config;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.data.DocumentRepository;
import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import net.claztec.document.service.SearchEngine;
import net.claztec.document.service.SearchEngineService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Derek Choi on 2015. 3. 11..
 */
@Configuration
public class MyDocumentContext {

    private Map<String, Document> documents = new HashMap<String, Document>();
    private Map<String, Type> types = new HashMap<String, Type>();

    @Bean
    public Type webType() {
        return getTypeFromMap("web");
    }

    @Bean
    public SearchEngine engine() {
        SearchEngineService engine = new SearchEngineService();
        engine.setDocumentDao(documentDao());
        return engine;
    }


    public MyDocumentContext() {


        Type type = new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");

        Document document = new Document();
        document.setName("Book Template");
        document.setType(type);
        document.setLocation("/Users/claztec/Documents/Random/Book Template.pdf");


        documents.put("doc1", document);
        types.put("pdf", type);

        Document document2 = new Document();
        document2.setName("Sample Contract");
        document2.setType(type);
        document2.setLocation("/Users/claztec/Documents/Random/Sample Contract.pdf");

        documents.put("doc2", document2);

        Type type2 = new Type();
        type2.setName("NOTE");
        type2.setDesc("Text Notes");
        type2.setExtension(".txt");

        types.put("note", type2);


        Document document3 = new Document();
        document3.setName("Clustering with RabbitMQ");
        document3.setType(type2);
        document3.setLocation("/Users/claztec/Documents/Random/Clustering with RabbitMQ.txt");

        documents.put("doc3", document3);

        Type type3 = new Type();
        type3.setName("WEB");
        type3.setDesc("Web Link");
        type3.setExtension(".url");

        types.put("web", type3);

        Document document4 = new Document();
        document4.setName("Pro Spring Security Book");
        document4.setType(type3);
        document4.setLocation("http://www.apress.com/9781430248187");

        documents.put("doc4", document4);
    }

    private DocumentDao documentDao() {
        DocumentRepository documentDao = new DocumentRepository();
//        documentDao.setDoc1(getDocumentFromMap("doc1"));
//        documentDao.setDoc2(getDocumentFromMap("doc2"));
//        documentDao.setDoc3(getDocumentFromMap("doc3"));
//        documentDao.setDoc4(getDocumentFromMap("doc4"));
        return documentDao;
    }

    private Document getDocumentFromMap(String documentKey) {
        return documents.get(documentKey);
    }

    private Type getTypeFromMap(String typeKey) {
        System.out.println("typeKey:" + typeKey);
        return types.get(typeKey);
    }
}
