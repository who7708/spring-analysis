package com.example;

import com.google.common.reflect.ClassPath;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EnableRetry
@SpringBootApplication
public class App {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(App.class, args);
        System.out.println("项目启动成功...");
        // 搜索应用或jar所有class，然后循环加载进来.
        // comment 2023/11/30 Class 在启动时进行预加载，即将所有Class，包含未使用和已使用的 Class 全部加载在内存中去
        // Reflections reflections = Reflections.collect("com.example.retry", s -> true);
        // final String packageName = "org.springframework";
        final String packageName = "com.example.retry";
        findAllClassesUsingReflectionsLibrary(packageName)
                // findAllClassesUsingGoogleGuice(packageName)
                .forEach(clazz -> {
                    try {
                        String clazzName = clazz.getName();
                        System.out.println(clazzName);
                        // getClassLoader().loadClass(clazzName);
                    } catch (Exception ignore) {
                    }
                });
        // classLoader.loadClass("com.example.retry.Student");
    }

    @Bean
    CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }

    private static Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        // Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Reflections reflections = new Reflections(packageName, Scanners.SubTypes);
        // Reflections reflections = new Reflections(packageName, Scanners.SubTypes.filterResultsBy(new Predicate<String>() {
        //     @Override
        //     public boolean test(String s) {
        //         return true;
        //     }
        // }));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }

    private static Set<Class<?>> findAllClassesUsingGoogleGuice(String packageName) throws IOException {
        return ClassPath.from(getClassLoader())
                .getAllClasses()
                .stream()
                .filter(clazz -> clazz.getPackageName()
                        .equalsIgnoreCase(packageName))
                .map(ClassPath.ClassInfo::load)
                .collect(Collectors.toSet());
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = App.class.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return classLoader;
    }

}
