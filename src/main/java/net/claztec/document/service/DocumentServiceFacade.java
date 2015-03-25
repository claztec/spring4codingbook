package net.claztec.document.service;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by claztec on 15. 3. 23.
 */
@Component("documentFacade")
public class DocumentServiceFacade implements DocumentService {

    @Autowired
    DocumentDao documentDao;

    @Override
    public List<Document> getAllDocuments() {
        return documentDao.getAll();
    }

    @Override
    public Document findDocumentById(String id) {
        return documentDao.findById(id);
    }

    @Override
    public Document saveDocument(String id, Document document) {
        return documentDao.save(id, document);
    }

    @Override
    public Document removeDocumentById(String id) {
        return documentDao.removeById(id);
    }

    @Override
    public boolean updateLocationFromDocumentId(String documentId, String location) {

        Document document = documentDao.findById(documentId);
        if (null == document) {
            return false;
        }

        document.setLocation(location);
        saveDocument(documentId, document);

        return true;
    }
}
