package net.claztec.document.aop.annotated;

import net.claztec.document.aop.CacheModule;
import net.claztec.document.model.Type;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Derek Choi on 2015. 3. 16..
 */
@Component
@Aspect
public class Caching {

    private static final Logger log = LoggerFactory.getLogger(CacheModule.class);

    private static final Map<String, Object> cache = new HashMap<String, Object>();

    @Around("execution(* net.claztec.document.service.SearchEngine.*(..))")
    public Object caching(ProceedingJoinPoint pjp) throws Throwable {

        Object result = null;

        Type documentType = null;

        log.debug("@@@(Caching) review if this call is cachable.. [annotation]");

        if ("findByType".equals(pjp.getSignature().getName()) && pjp.getArgs().length == 1 && pjp.getArgs()[0] instanceof Type) {
            documentType = (Type) pjp.getArgs()[0];
            log.debug("@@@(Caching) Is cachable!! [annotation]");
            if (cache.containsKey(documentType.getName())) {
                log.debug("@@@(Caching) Found in Cache!! [annotation]");
                return cache.get(documentType.getName());
            }
            log.debug("@@@(Caching) Not Found! but is cachable! [annotation]");
            result = pjp.proceed();
            cache.put(documentType.getName(), result);
            return result;
        }

        return pjp.proceed();
    }


}
