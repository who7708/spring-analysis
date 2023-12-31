package org.spring.boot.test;

import org.junit.Test;
import org.spring.base.Student;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author skywalker
 */
public class JavaTest {

    private class MyList extends ArrayList {

        @Override
        public String get(int index) {
            return "";
        }
    }

    public static void main(String[] args) {
        // 仅获取当前类的方法
        for (Method method : MyList.class.getDeclaredMethods()) {
            // 包含父类继承的方法
            // for (Method method : MyList.class.getMethods()) {
            System.out.println("name: " + method.getName() + ", return: " + method.getReturnType());
        }
    }

    @Test
    public void classpath() {
        System.out.println(System.getProperty("java.class.path"));
    }

    @Test
    public void findClass() throws IOException {
        Enumeration<URL> base = JavaTest.class.getClassLoader().getResources("org/spring/base/*");
        while (base.hasMoreElements()) {
            System.out.println(base.nextElement().toString());
        }
    }

    @Test
    public void intro() throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo(Student.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getReadMethod());
            System.out.println(pd.getWriteMethod());
        }
    }

    @Test
    public void split() {
        String str = "1\t2\taug\tfri\t14.7\t66\t2.7\t0\t0";
        String[] arr = str.split("\t");
        System.out.println(Arrays.toString(arr));
        System.out.println(arr);
        System.out.println("月份: " + arr[2]);
        System.out.println("天气: " + arr[4]);
    }

}
