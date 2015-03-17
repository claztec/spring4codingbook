package net.claztec.document.data;

import net.claztec.document.model.Document;
import net.claztec.document.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek Choi on 2015. 3. 11..
 */
@Repository("documentDao")
public class AnnotatedDocumentRepository implements DocumentDao {
    private static final Logger log = LoggerFactory.getLogger(AnnotatedDocumentRepository.class);

    private static final String queryAll = "                select d.documentId, d.name, d.location, d.description as doc_desc,\n" +
            "                d.typeId, d.created, d.modified,\n" +
            "                t.name as type_name, t.description as type_desc, t.extension\n" +
            "                from documents d\n" +
            "                join types t\n" +
            "                on d.typeId = t.typeId";

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Document> getAll() {
        if (log.isDebugEnabled()) {
            log.debug("Start <getAll> Params: ");
        }


        List<Document> result = new ArrayList<Document>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Document document = null;
        Type type = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryAll);

            while (resultSet.next()) {
                document = new Document();
                document.setDocumentId(resultSet.getString("documentId"));
                document.setName(resultSet.getString("name"));
                document.setLocation(resultSet.getString("location"));
                document.setCreated(resultSet.getDate("created"));
                document.setModified(resultSet.getDate("modified"));
                document.setDescription("doc_desc");
                type = new Type();
                type.setTypeId(resultSet.getString("typeId"));
                type.setName(resultSet.getString("type_name"));
                type.setDesc(resultSet.getString("type_desc"));
                type.setExtension(resultSet.getString("extension"));
                document.setType(type);
                result.add(document);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;

    }

}
