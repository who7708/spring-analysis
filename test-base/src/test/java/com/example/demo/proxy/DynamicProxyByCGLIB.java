package com.example.demo.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 动态代理
 */
public class DynamicProxyByCGLIB {
    @Test
    public void testCglibProxy() {
        //目标对象
        UserDao target = new UserDao();
        System.out.println(target.getClass());
        //代理对象
        UserDao proxy = new ProxyFactory<>(target).getProxyInstance();
        System.out.println(proxy.getClass());
        //执行代理对象方法
        // save 方法被增强了，被 final 或者 static 修饰的无法被代理
        System.out.println(proxy.save());
        proxy.finalMethod();
        proxy.staticMethod();
    }

    static class UserDao {
        public int save() {
            System.out.println("保存数据");
            return 1;
        }

        public final void finalMethod() {
            System.out.println("测试 final 方法");
        }

        public static void staticMethod() {
            System.out.println("测试 static 方法");
        }
    }

    static class ProxyFactory<T> implements MethodInterceptor {

        private T target;

        public ProxyFactory(T target) {
            this.target = target;
        }

        /** 为目标对象生成代理对象 */
        public T getProxyInstance() {
            // Cglib 动态代理
            // 代理类class文件存入本地磁盘方便我们反编译查看源码
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./code");
            // 工具类
            Enhancer enhancer = new Enhancer();
            // 设置父类
            enhancer.setSuperclass(target.getClass());
            // 设置回调函数
            enhancer.setCallback(this);
            // 创建子类对象代理
            return (T) enhancer.create();
        }

        @Override
        public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("开启事务");
            // 执行目标对象的方法
            Object returnValue = method.invoke(target, args);
            // Object returnValue = method.invoke(proxy, args);
            // final Object returnValue = methodProxy.invoke(proxy, args);
            System.out.println("关闭事务");
            return returnValue;
        }
    }
}
