package com.example.demo.proxy;

import com.example.demo.proxy.jdk.LogHandler;
import com.example.demo.proxy.jdk.MapperRegistry;
import com.example.demo.proxy.jdk.UserService;
import com.example.demo.proxy.jdk.UserServiceImpl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * 动态代理
 */
public class DynamicProxyByJDK {

    private static MapperRegistry mapperRegistry = new MapperRegistry();

    static {
        // 加载此包及子包下的所有 Class
        String dir = DynamicProxyByJDK.class.getResource(".").getPath();
        Collection<File> files = FileUtils.listFiles(new File(dir), new String[]{"class"}, true);
        for (File file : files) {
            String path = file.getPath();
            path = path.replace('\\', '/');
            path = path.substring(path.indexOf("/com/example/demo") + 1, path.length() - 6).replace("/", ".");
            System.out.println(path);
            try {

                // Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(path);
                mapperRegistry.addMapper(UserService.class);
            } catch (Exception ignore) {

            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        dynamicProxy2();
    }

    private static void dynamicProxy2() {
        UserService mapper = mapperRegistry.getMapper(UserService.class);
        UserService mapper2 = mapperRegistry.getMapper(UserService.class);
        System.out.println(mapper);
        String select = mapper.select();
        System.out.println(select);
        String update = mapper.update();
        System.out.println(update);
    }

    private static void dynamicProxy1() {
        // 设置变量可以保存动态代理类，默认名称以 $Proxy0 格式命名
        // System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1. 创建被代理的对象，UserService接口的实现类
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        // 2. 获取对应的 ClassLoader
        ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();
        // 3. 获取所有接口的Class，这里的UserServiceImpl只实现了一个接口UserService，
        Class[] interfaces = userServiceImpl.getClass().getInterfaces();
        // 4. 创建一个将传给代理类的调用请求处理器，处理所有的代理对象上的方法调用
        //     这里创建的是一个自定义的日志处理器，须传入实际的执行对象 userServiceImpl
        InvocationHandler logHandler = new LogHandler(userServiceImpl);
        // 5.根据上面提供的信息，创建代理对象 在这个过程中，
        // a.JDK会通过根据传入的参数信息动态地在内存中创建和.class 文件等同的字节码
        // b.然后根据相应的字节码转换成对应的class，
        // c.然后调用newInstance()创建代理实例
        UserService proxy = (UserService) Proxy.newProxyInstance(classLoader, interfaces, logHandler);
        // 调用代理的方法
        proxy.select();
        proxy.update();

        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        // ProxyUtils.generateClassFile(userServiceImpl.getClass(), "UserServiceProxy");
    }
}
