package pt.test.payaramicro.interceptors;

import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import pt.test.payaramicro.beans.DataCacheService;

@Interceptor
@FunctionLogger
@Priority(Interceptor.Priority.APPLICATION + 5)
public class FunctionLoggerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(FunctionLoggerInterceptor.class.getSimpleName());

    @EJB
    private DataCacheService dataCache;

    //    @Inject
    //    private CDIServiceBean cdiService;

    @AroundInvoke
    public Object logFunctionResult(InvocationContext invocationContext) throws Exception {

        //        Context context = new InitialContext();
        //        DataCacheService ejbService = (DataCacheService) context.lookup("java:global/application/ejbs/DataCacheService");
        //        ejbService.loadCache();

        Long start = System.currentTimeMillis();
        Object result = invocationContext.proceed();
        Long end = System.currentTimeMillis();
        LOGGER.info("Took " + (end - start) + " milliseconds to complete");

        return result;
    }

}
