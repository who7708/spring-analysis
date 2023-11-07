package com.example.copy;

public class Student implements Cloneable {

    // 对象引用
    private Subject subj;

    private String name;

    public Student(String s, String sub) {
        name = s;
        subj = new Subject(sub);
    }

    public Subject getSubj() {
        return subj;
    }

    public String getName() {
        return name;
    }

    public void setName(String s) {
        name = s;
    }

    // /**
    //  * 重写clone()方法
    //  */
    // @Override
    // public Object clone() {
    //     //浅拷贝
    //     try {
    //         // 直接调用父类的clone()方法
    //         return super.clone();
    //     } catch (CloneNotSupportedException e) {
    //         return null;
    //     }
    // }

    /**
     * 重写clone()方法
     */
    @Override
    public Object clone() {
        // 深拷贝，创建拷贝类的一个新对象，这样就和原始对象相互独立
        return new Student(name, subj.getName());
    }
}
