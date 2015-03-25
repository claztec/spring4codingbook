package net.claztec.document.data;

import net.claztec.document.model.Document;

import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 10..
 */
public interface DocumentDao {
    public List<Document> getAll();

    Document save(String id, Document document);

    public Document findById(String documentId);

    Document removeById(String id);

    void save(Document document);
}
