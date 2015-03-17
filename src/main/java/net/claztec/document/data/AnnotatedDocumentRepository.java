package net.claztec.document.data;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 11..
 */
@Repository("documentDao")
public class AnnotatedDocumentRepository implements DocumentDao {
    @Override
    public Document[] getAll() {
        return storage();
    }

    private Document[] storage() {
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

        Document document2 = new Document();
        document2.setName("Sample Contract");
        document2.setType(type);
        document2.setLocation("/Users/claztec/Documents/Random/Sample Contract.pdf");

        result.add(document2);

        Type type2 = new Type();
        type2.setName("NOTE");
        type2.setDesc("Text Notes");
        type2.setExtension(".txt");

        Document document3 = new Document();
        document3.setName("Clustering with RabbitMQ");
        document3.setType(type2);
        document3.setLocation("/Users/claztec/Documents/Random/Clustering with RabbitMQ.txt");

        result.add(document3);

        Type type3 = new Type();
        type3.setName("WEB");
        type3.setDesc("Web Link");
        type3.setExtension(".url");

        Document document4 = new Document();
        document.setName("Pro Spring Security Book");
        document4.setType(type3);
        document4.setLocation("http://www.apress.com/9781430248187");

        result.add(document4);

        return result.toArray(new Document[result.size()]);

    }


}
