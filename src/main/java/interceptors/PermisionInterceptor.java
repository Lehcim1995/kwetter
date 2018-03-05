package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermisionInterceptor // https://dzone.com/articles/java-ee-interceptors
{

    @AroundInvoke
    public Object checkPermission(InvocationContext ctx) throws Exception
    {
        Logger.getAnonymousLogger().log(Level.INFO, ctx.getMethod().getName() + " inteceptorlol");


        return ctx.proceed();
    }
}
