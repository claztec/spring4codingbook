package net.claztec.document.service;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    @Override
    public List<Document> findByType(Type webType) {
        List<Document> list = new ArrayList<Document>();

        List<Document> documents = documentDao.getAll();
        for (Document document : documents) {
            if (document.getType().getName().equals(webType.getName())) {
                list.add(document);
            }
        }

        return list;
    }
}
