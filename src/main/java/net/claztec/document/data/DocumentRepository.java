package net.claztec.document.data;

import net.claztec.document.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Derek Choi on 2015. 3. 10..
 */

@Repository("documentDao")
public class DocumentRepository implements DocumentDao {

    private static final Logger log = LoggerFactory.getLogger(DocumentRepository.class);

    @Autowired
    private DataSource dataSource;

    @Resource
    private Map<String, String> sql;

    @Override
    public List<Document> getAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        return jdbcTemplate.query(sql.get("query"), new DocumentRowMapper());
    }

    @Override
    public Document findById(String id) {

        Document document = null;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);

        try {
            document = jdbcTemplate.queryForObject(sql.get("find"), new Object[]{id}, new DocumentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            log.debug("@@ id not found @@");
        }

        return document;
    }

    @Override
    public Document save(String id, Document document) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        Document _document = findById(id);

        if (null == _document) {
            jdbcTemplate.update(sql.get("insert"), new Object[]{document.getDocumentId(), document.getName(), document.getLocation(), document.getDescription(), document.getType().getTypeId(), document.getCreated(), document.getModified()});
        } else {
            _document.setName((null == document.getName()) ? _document.getName() : document.getName());
            _document.setLocation((null == document.getLocation()) ? _document.getLocation() : document.getLocation());
            _document.setDescription((null == document.getDescription()) ? _document.getDescription() : document.getDescription());
            _document.setType((null == document.getType()) ? _document.getType() : document.getType());
            _document.setModified(new Date());
            jdbcTemplate.update(sql.get("update"), new Object[]{_document.getName(), _document.getLocation(), _document.getDescription(), _document.getType().getTypeId(), new Date(), id});
            document = _document;
        }

        return document;
    }

    @Override
    public Document removeById(String id) {
        Document document = findById(id);
        if (null != document) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
            int rows = jdbcTemplate.update(sql.get("delete"), new Object[] {id});
            if (rows <= 0) {
                document = null;
            }

        }

        return document;
    }

    @Override
    public void save(Document document) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
        Document _document = findById(document.getDocumentId());

        if (null == _document) {
            jdbcTemplate.update(sql.get("insert"), new Object[]{document.getDocumentId(), document.getName(), document.getLocation(), document.getDescription(), document.getType().getTypeId(), document.getCreated(), document.getModified()});
        } else {
            _document.setName((null == document.getName()) ? _document.getName() : document.getName());
            _document.setLocation((null == document.getLocation()) ? _document.getLocation() : document.getLocation());
            _document.setDescription((null == document.getDescription()) ? _document.getDescription() : document.getDescription());
            _document.setType((null == document.getType()) ? _document.getType() : document.getType());
            _document.setModified(new Date());
            jdbcTemplate.update(sql.get("update"), new Object[]{_document.getName(), _document.getLocation(), _document.getDescription(), _document.getType().getTypeId(), new Date(), _document.getDocumentId()});
        }

    }
}
