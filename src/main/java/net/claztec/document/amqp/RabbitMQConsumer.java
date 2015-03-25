package net.claztec.document.amqp;

import net.claztec.document.model.Document;
import net.claztec.document.utils.XmlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * Created by claztec on 15. 3. 23.
 */

//@Component("rabbitmqConsumer")
public class RabbitMQConsumer implements MessageListener{

    public static final Logger log = LoggerFactory.getLogger(RabbitMQConsumer.class);


    @Override
    public void onMessage(Message message) {
        Document document = XmlUtils.fromXML(new String(message.getBody()), Document.class);
        log.debug("Document received: " + document);
    }
}
