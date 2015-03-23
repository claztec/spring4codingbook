package net.claztec.document.data;

import net.claztec.document.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by claztec on 15. 3. 19.
 */

@Repository("documentDao")
public class DocumentJdbcTemplateRepository implements DocumentDao {

    private static final Logger log = LoggerFactory.getLogger(DocumentJdbcTemplateRepository.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private String query;

    @Autowired
    private String find;

    @Autowired
    private String insert;

    @Autowired
    private String update;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

    }


    @Override
    public List<Document> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        log.debug(">>>>>>>>>>>>> DocumentJdbcTemplateRepository >>>>>>>>>>");
        return jdbcTemplate.query(query, new DocumentRowMapper());
    }

    @Override
    public void save(Document document) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        log.debug("@@@ save method called @@@");

        if (null == findById(document.getDocumentId())) {
            jdbcTemplate.update(insert, new Object[] {document.getDocumentId(), document.getName(), document.getLocation(), document.getDescription(), document.getType().getTypeId(), document.getCreated(), document.getModified()});
            log.debug("@@@ INSERT document @@@");
        } else {
            jdbcTemplate.update(update, new Object[] {document.getDocumentId(), document.getName(), document.getLocation(), document.getDescription(), document.getType().getTypeId(), document.getCreated(), document.getModified()});
            log.debug("@@@ UPDATE document @@@");
        }
    }

    public Document findById(String id) {

        Document updateDocument = null;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        try {
            updateDocument = jdbcTemplate.queryForObject(find, new Object[] {id}, new DocumentRowMapper());
        } catch (EmptyResultDataAccessException e) {
//            e.printStackTrace();
            log.debug("@@ id not found @@");
        }

        return updateDocument;
    }


}

