package net.claztec.document.jms;

import net.claztec.document.data.DocumentDao;
import net.claztec.document.model.Document;
import net.claztec.document.utils.XmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by claztec on 15. 3. 20.
 */
@Component
public class JMSConsumer implements MessageListener {

    @Autowired
    DocumentDao documentDao;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;

        Document document = null;
        try {
            document = XmlUtils.fromXML(textMessage.getText(), Document.class);
            documentDao.save(document);
        } catch (JMSException e) {
//            e.printStackTrace();
        }

    }

}
