package net.claztec.document.data;

import net.claztec.document.model.Type;

/**
 * Created by Derek Choi on 2015. 3. 12..
 */
public interface TypeDataDao {
    public Type[] getAll();
    public Type findById(String id);
}
