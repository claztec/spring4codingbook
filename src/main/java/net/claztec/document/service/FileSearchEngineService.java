package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */

@Service
@Profile("qa")
public class FileSearchEngineService implements SearchEngine {
    @Override
    public List<Document> findByType(Type documentType) {

        throw new UnsupportedOperationException("QA Environment. Not yet implemented operation");

    }

    @Override
    public List<Document> listAll() {
        throw new UnsupportedOperationException("QA Environment. Not yet implemented operation");
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
