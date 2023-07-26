package org.spring.boot.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * java 元数据区域/方法区的内存溢出
 * <p>
 * VM Args JDK 1.6: set JAVA_OPTS=-verbose:gc -XX:PermSize=10m -XX:MaxPermSize=10m -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=D:\dump
 * <p>
 * VM Args JDK 1.8: set JAVA_OPTS=-verbose:gc -Xmx20m -XX:MetaspaceSize=5m -XX:MaxMetaspaceSize=5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\dump
 *
 * @author Chris
 */
public class TestOutOfMemoryErrorByMethodArea {

    static class MethodAreaOOM {
    }

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MethodAreaOOM.class);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] params, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, params);
                }
            });
            enhancer.create();
        }
    }
}