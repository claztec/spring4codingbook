package net.claztec.document.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

/**
 * Created by claztec on 15. 3. 31.
 */
@Component("documentTweet")
public class DocumentTweet {

    @Autowired
    Twitter tweet;

    public void tweet(String text) {
        tweet.timelineOperations().updateStatus(text);
    }
}
