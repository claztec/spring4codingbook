package net.claztec.document.data;

import net.claztec.document.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


/**
 * Created by Derek Choi on 2015. 3. 12..
 */
public class TypeDataRepository implements TypeDataDao {

    public static final Logger log = LoggerFactory.getLogger(TypeDataRepository.class);

    private Map<String, Type> types = null;

    public Map<String, Type> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Type> types) {
        this.types = types;
    }


    @Override
    public Type[] getAll() {
        return types.values().toArray(new Type[types.values().size()]);
    }

    @Override
    public Type findById(String id) {
        if (log.isDebugEnabled()) {
            log.debug("Start <findById> Params: " + id);
        }

        Type type = types.get(id);

        if (log.isDebugEnabled()) {
            log.debug("End <findById> Params: " + type);
        }

        return type;
    }
}
