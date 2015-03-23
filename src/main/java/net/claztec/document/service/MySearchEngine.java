package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 9..
 */
public class MySearchEngine implements SearchEngine{
    @Override
    public List<Document> findByType(Type documentType) {
        List<Document> result = new ArrayList<Document>();
        for (Document document : storage()) {
            if (document.getType().getName().equals(documentType.getName())) {
                result.add(document);
            }
        }
        return result;
    }

    private List<Document> storage() {
        List<Document> result = new ArrayList<Document>();

        Type type = new Type();
        type.setName("PDF");
        type.setDesc("Portable Document Format");
        type.setExtension(".pdf");

        Document document = new Document();
        document.setName("Book Template");
        document.setType(type);
        document.setLocation("/Users/claztec/Documents/Random/Book Template.pdf");

        result.add(document);

        document = new Document();
        document.setName("Sample Contract");
        document.setType(type);
        document.setLocation("/Users/claztec/Documents/Random/Sample Contract.pdf");

        result.add(document);

        type = new Type();
        type.setName("NOTE");
        type.setDesc("Text Notes");
        type.setExtension(".txt");

        document = new Document();
        document.setName("Clustering with RabbitMQ");
        document.setType(type);
        document.setLocation("/Users/claztec/Documents/Random/Clustering with RabbitMQ.txt");

        result.add(document);

        type = new Type();
        type.setName("WEB");
        type.setDesc("Web Link");
        type.setExtension(".url");

        document = new Document();
        document.setName("Pro Spring Security Book");
        document.setType(type);
        document.setLocation("http://www.apress.com/9781430248187");

        result.add(document);

        return result;
    }

    @Override
    public List<Document> listAll() {
        return storage();
    }

    @Override
    public List<Document> findByLocation(String location) {
        return null;
    }

    @Override
    public Document findById(String documentId) {
        return null;
    }
}
