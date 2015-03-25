package net.claztec.document.web;

import net.claztec.document.model.Document;
import net.claztec.document.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by claztec on 15. 3. 25.
 */

@Controller
@RequestMapping("/documents")
public class DocumentController {

    public static final Logger log = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    DocumentService documentFacade;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
//    public @ResponseBody List<Document> getDocuments() {
    public Object getDocuments() {
        System.out.println("@@@@ getDocuments @@@@");
        List<Document> documents = documentFacade.getAllDocuments();
        for (Document tmp : documents) {
            log.debug(tmp.getDocumentId());
        }

//        return "<h1>Hello World</h1>";
        return documents;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Document findDocument(@PathVariable String id) {
        return documentFacade.findDocumentById(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public Document addDocument(@RequestBody Document document) {
        String id = document.getDocumentId();
        return documentFacade.saveDocument(id, document);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public Document updateDocument(@RequestBody Document document, @PathVariable String id) {
        return documentFacade.saveDocument(id, document);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public Document removeDocument(@PathVariable String id) {
        return documentFacade.removeDocumentById(id);
    }

}
