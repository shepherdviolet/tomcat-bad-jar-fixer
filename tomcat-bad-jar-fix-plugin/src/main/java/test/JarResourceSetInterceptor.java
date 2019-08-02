package test;

import org.apache.skywalking.apm.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import sviolet.thistle.util.reflect.ClassPrinter;

import java.lang.reflect.Method;

/**
 * 拦截器
 *
 * @author S.Violet
 */
public class JarResourceSetInterceptor implements InstanceMethodsAroundInterceptor {

    private static final ILog logger = LogManager.getLogger(JarResourceSetInterceptor.class);

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
//        logger.debug("FIX: BeforeMethod: " + ClassPrinter.print(objInst, true));
    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        return ret;
    }

    /**
     * 遇到有问题的JAR包时会抛出异常, 打印当前JarResourceInterceptor对象的成员变量即可知道是哪个JAR包有问题了
     */
    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {
        try {
            //将当前对象的成员变量都打印出来, 里面可以看到JAR包路径
            logger.error("FIX!!! Bad Jar Found: " + ClassPrinter.print(objInst, true));
        } catch (IllegalAccessException e) {
            logger.warn("FIX: Error while print bad jar", e);
        }
    }

}
