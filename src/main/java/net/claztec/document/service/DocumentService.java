package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;

import java.util.List;

/**
 * Created by claztec on 15. 3. 23.
 */
public interface DocumentService {
    public List<Document> getAllDocuments();
    public Document findDocumentById(String id);
    public Document saveDocument(String id, Document document);
    public Document removeDocumentById(String id);
    public boolean updateLocationFromDocumentId(String documentId, String location);

    List<Document> findByType(Type webType);
}
