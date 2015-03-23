package net.claztec.document.utils;

import com.thoughtworks.xstream.XStream;

/**
 * Created by claztec on 15. 3. 20.
 */
public class XmlUtils {

    public static <T> String toXML(T object) {
        XStream xstream = new XStream();
        xstream.alias(object.getClass().getSimpleName().toLowerCase(), object.getClass());
        return xstream.toXML(object);
    }

    public static <T> T fromXML(String xml, Class<T> _class) {
        XStream xStream = new XStream();
        xStream.alias(_class.getSimpleName().toLowerCase(), _class);
        return (T) xStream.fromXML(xml);
    }
}
