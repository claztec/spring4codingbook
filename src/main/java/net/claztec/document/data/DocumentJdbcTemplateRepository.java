package net.claztec.document.data;

import net.claztec.document.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by claztec on 15. 3. 19.
 */

public class DocumentJdbcTemplateRepository implements DocumentDao {

    private static final Logger log = LoggerFactory.getLogger(DocumentJdbcTemplateRepository.class);

    private JdbcTemplate jdbcTemplate;

    private DataSource dataSource;

    private String query;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.dataSource);
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public List<Document> getAll() {
        log.debug(">>>>>>>>>>>>> DocumentJdbcTemplateRepository >>>>>>>>>>");
        return jdbcTemplate.query(query, new DocumentRowMapper());
    }
}
