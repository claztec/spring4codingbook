package net.claztec.document.test.profile;

import org.springframework.test.annotation.ProfileValueSource;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */
public class CustomProfile implements ProfileValueSource {
    @Override
    public String get(String key) {

        if (key.equals("environment")) {
            return "qa";
        } else if (key.equals("os.name")) {
            return "Unix";
        }
        return null;
    }
}
