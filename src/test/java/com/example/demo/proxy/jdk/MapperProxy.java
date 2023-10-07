package com.example.demo.proxy.jdk;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Chris
 * @date 2020/05/21
 * @since 1.0.0
 */
public class MapperProxy<T> implements InvocationHandler {

    private final Class<T> mapperInterface;

    public MapperProxy(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        // 调用 target 的 method 方法
        System.out.println(this + " , method: " + method);
        // mybatis
        Object result = null;
        try {
            // 生成代理类中的数据。
            // m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            // m4 = Class.forName("com.example.demo.mybatis.mapper.common.CommonMapper").getMethod("selectById", Class.forName("java.lang.String"), Integer.TYPE);
            // m2 = Class.forName("java.lang.Object").getMethod("toString");
            // m3 = Class.forName("com.example.demo.mybatis.mapper.common.CommonMapper").getMethod("selectAll");
            // m0 = Class.forName("java.lang.Object").getMethod("hashCode");
            // 主要过滤 Object 中的 equals， toString， hasCode 方法调用
            if (Object.class.equals(method.getDeclaringClass())) {
                result = method.invoke(this, args);
            } else {
                // 不再走原有的 method.invoke(obj, args), 因为 在 mybatis 中 this，并没有实例化。仍为一个抽象类
                // com.example.demo.proxy.jdk.MapperProxy@75471dd2 ,
                // method: public abstract void com.example.demo.proxy.jdk.UserService.update()
                result = cachedInvoker(method).invoke(proxy, method, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        after();
        return result;  // 返回方法的执行结果
    }

    private MapperMethodInvoker cachedInvoker(Method method) throws Throwable {
        return new PlainMethodInvoker();
    }

    interface MapperMethodInvoker {
        Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
    }

    private static class DefaultMethodInvoker implements MapperMethodInvoker {

        public DefaultMethodInvoker() {
            super();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return "hello world: DefaultMethodInvoker";
        }
    }

    private static class PlainMethodInvoker implements MapperMethodInvoker {

        public PlainMethodInvoker() {
            super();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation instanceof Select) {
                    return ((Select) annotation).value();
                } else if (annotation instanceof Update) {
                    return ((Update) annotation).value();
                }
            }
            return "hello world: PlainMethodInvoker";
        }
    }

    // private MethodHandle getMethodHandleJava9(Method method)
    //         throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    //     final Class<?> declaringClass = method.getDeclaringClass();
    //     return ((MethodHandles.Lookup) privateLookupInMethod.invoke(null, declaringClass, MethodHandles.lookup())).findSpecial(
    //             declaringClass, method.getName(), MethodType.methodType(method.getReturnType(), method.getParameterTypes()),
    //             declaringClass);
    // }
    //
    // private MethodHandle getMethodHandleJava8(Method method)
    //         throws IllegalAccessException, InstantiationException, InvocationTargetException {
    //     final Class<?> declaringClass = method.getDeclaringClass();
    //     return lookupConstructor.newInstance(declaringClass, ALLOWED_MODES).unreflectSpecial(method, declaringClass);
    // }

    // 调用invoke方法之前执行
    private void before() {
        System.out.println(String.format("log start time [%s] ", new Date()));
    }

    // 调用invoke方法之后执行
    private void after() {
        System.out.println(String.format("log end time [%s] ", new Date()));
    }
}
