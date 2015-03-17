package net.claztec.document.service;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;

import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 9..
 */
public interface SearchEngine {
    public List<Document> findByType(Type documentType);
    public List<Document> listAll();
    public List<Document> findByLocation(String location);
}
