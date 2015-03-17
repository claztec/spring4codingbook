package net.claztec.document.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */
public class AfterLoggingModule implements AfterReturningAdvice {

    private static final Logger log = LoggerFactory.getLogger(AfterLoggingModule.class);

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("@@@@(AFTER) Method called: " + method.getName());

            if (args.length == 0) {
                log.debug("@@@@(AFTER) Method called: " + method.getName());
            }
            for (Object arg:args) {
                log.debug("@@@@(AFTER) Argument passed:" + arg);
            }
        }
    }
}
