package net.claztec.document.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */
public class BeforeLoggingModule implements MethodBeforeAdvice {

    private static final Logger log = LoggerFactory.getLogger(BeforeLoggingModule.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("@@@@(BEFORE) Method called: " + method.getName());

            if (args.length == 0) {
                log.debug("@@@@(BEFORE) Method called: " + method.getName());
            }
            for (Object arg:args) {
                log.debug("@@@@(BEFORE) Argument passed:" + arg);
            }
        }
    }
}
