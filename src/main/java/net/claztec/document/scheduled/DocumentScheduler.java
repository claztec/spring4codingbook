package net.claztec.document.scheduled;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import net.claztec.document.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by claztec on 15. 3. 26.
 */
@Component
public class DocumentScheduler {

    public static final Logger log = LoggerFactory.getLogger(DocumentScheduler.class);

    private static int HTTP_NOT_FOUND_CODE = 404;
    private static int HTTP_OK_CODE = 200;

    @Autowired
    DocumentService documentService;

    private Type webType = new Type("WEB", ".url");

    @Scheduled(fixedRate = 3000)
    public void sampleCronMethod() {
        log.debug("Running every 3 seconds...");
    }

    @Scheduled(cron = "*/10 * * * * ?")
    public void urlCheck() throws IOException {
        log.debug("@@ Checking value WEB type Document's URL...");
        URL url = null;
        HttpURLConnection connection = null;
        int responseCode = 0;
        List<Document> documents = documentService.findByType(webType);
        for (Document document : documents) {
            url = new URL(document.getLocation());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            responseCode = connection.getResponseCode();
            log.debug("Name lookup: " + document.getName());
            log.debug("Code: " + Integer.toString(responseCode));
            if (HTTP_OK_CODE == responseCode) {
                log.info("URL is still valid");
            } else {
                log.error("URL INVALID! CODE: " + HTTP_NOT_FOUND_CODE + ". Please let the Administrator know");
            }
        }
    }


}
