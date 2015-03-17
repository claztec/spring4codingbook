package net.claztec.document.service;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 11..
 */
@Service("engine")
public class AnnotatedSearchEngine implements SearchEngine {

    private static final Logger log = LoggerFactory.getLogger(SearchEngineService.class);

    public AnnotatedSearchEngine() {
        log.debug("<>>>>>>>>>>>>>\n" + this);
    }


    @Autowired
    private DocumentDao documentDao;

    @Override
    public List<Document> findByType(Type documentType) {
        List<Document> result = new ArrayList<Document>();
        List<Document> lists = listAll();
        for (Document doc : lists) {
            if (doc.getType().getName().equals(documentType.getName())) {
                result.add(doc);
            }
        }
        return result;
    }

    @Override
    public List<Document> listAll() {
        System.out.println(">>>>>>>> AnnotatedSearchEngin listAll() >>>>>>>>>>>>>>");
        return documentDao.getAll();
    }

    @Override
    public List<Document> findByLocation(String location) {
        return null;
    }
}
