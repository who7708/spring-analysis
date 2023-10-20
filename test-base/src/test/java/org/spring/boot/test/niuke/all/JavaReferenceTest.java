package org.spring.boot.test.niuke.all;

import org.junit.Test;

/**
 * java 方法是值传入,对象则传入提指向地址的值.
 *
 * @author Chris
 * @version 1.0.0
 * @since 2023/10/12 012
 */
public class JavaReferenceTest {
    @Test
    public void test() throws Exception {
        System.out.println("===== test =====");
        // write code here
        Student student = new Student("zhangsan", 23);
        updateStudent(student);
        System.out.println("student = " + student);
    }

    private void updateStudent(Student student) {
        // student = new Student("lisi", 32);
        student.name = "lisi";
        System.out.println("student = " + student);
    }

    static class Student {
        private String name;

        public int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
