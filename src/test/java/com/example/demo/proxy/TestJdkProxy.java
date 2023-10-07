package com.example.demo.proxy;

import com.example.demo.proxy.jdk.UserService;

/**
 * @author Chris
 * @date 2020/05/21
 * @since 1.0.0
 */
public class TestJdkProxy {
    public static void main(String[] args) throws Exception {
        Class<?> aClass = classForName(UserService.class.getName());
        System.out.println(aClass);
    }

    static Class<?> classForName(String name) throws ClassNotFoundException {
        return classForName(name, getClassLoaders(null));
    }

    private static Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                try {
                    Class<?> c = Class.forName(name, true, cl);
                    if (null != c) {
                        return c;
                    }
                } catch (ClassNotFoundException e) {
                    // we'll ignore this until all classloaders fail to locate the class
                }
            }
        }
        throw new ClassNotFoundException("Cannot find class: " + name);
    }

    private static ClassLoader systemClassLoader;

    static {
        try {
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ignored) {
            // AccessControlException on Google App Engine
        }
    }

    private static ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                Thread.currentThread().getContextClassLoader(),
                TestJdkProxy.class.getClassLoader(),
                systemClassLoader};
    }
}
