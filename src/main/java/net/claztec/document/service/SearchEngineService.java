package net.claztec.document.service;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 10..
 */
public class SearchEngineService implements SearchEngine {

//    private static final Logger log = LoggerFactory.getLogger(SearchEngineService.class);
    private Logger log = LoggerFactory.getLogger(SearchEngineService.class);

    private DocumentDao documentDao;

    public DocumentDao getDocumentDao() {
        return documentDao;
    }

    public void setDocumentDao(DocumentDao documentDao) {
        if (log.isDebugEnabled()) {
            log.debug("Document Dao Set: " + this);
        }

        log.info("TESTESTST");
        System.err.println(">>>>>>>>>>check>>>");
        log.error("EOEOEEOEOEOE");
        this.documentDao = documentDao;
    }


    public SearchEngineService() {
        log.info("TESTESTST");
        log.error("EOEOEddddEOEOEOE");

        if (log.isDebugEnabled()) {
            log.debug("SearchEngineService created: " + this);
        }
    }

    @Override
    public List<Document> findByType(Type documentType) {
        List<Document> result = new ArrayList<Document>();
        List<Document> listAll = listAll();
        for (Document doc : listAll) {
            if (doc.getType().getName().equals(documentType.getName())) {
                result.add(doc);
            }
        }
        return result;
    }

    @Override
    public List<Document> listAll() {
        return documentDao.getAll();
    }

    @Override
    public List<Document> findByLocation(String location) {
        throw new UnsupportedOperationException("findByLocation not yet implemented.");
    }

    @Override
    public Document findById(String documentId) {
        return null;
    }
}
