package net.claztec.document.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */
public class ThrowLoggingModule implements ThrowsAdvice {

    private static final Logger log = LoggerFactory.getLogger(AfterLoggingModule.class);

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
        if (log.isDebugEnabled()) {
            log.debug("@@@@(THROW) Method called: " + method.getName());

            if (args.length == 0) {
                log.debug("@@@@(THROW) Method called: " + method.getName());
            }
            for (Object arg:args) {
                log.debug("@@@@(THROW) Argument passed:" + arg);
            }
        }
    }

}
