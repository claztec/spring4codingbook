package net.claztec.document.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */
public class AroundLoggingModule implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(AroundLoggingModule.class);


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;

        if (log.isDebugEnabled()) {
            log.debug("@@@@(AROUND) Method called: " + invocation.getMethod().getName());

            if (invocation.getArguments().length == 0) {
                log.debug("@@@@(AROUND) Method called: " + invocation.getMethod().getName());
            }
            for (Object arg:invocation.getArguments()) {
                log.debug("@@@@(AROUND) Argument passed:" + arg);
            }
        }

        try {
            if (log.isDebugEnabled()) {
                log.debug("@@@(AROUND) Processing... ");
            }

            result = invocation.proceed();

            if (log.isDebugEnabled()) {
                log.debug("@@@(AROUND-AFTER) Result: " + result);
            }

            return result;
        } catch (IllegalArgumentException ex) {
            log.error("@@@(AROUNG) Throws an exception: " + ex.getMessage());
            throw ex;
        }
    }
}
