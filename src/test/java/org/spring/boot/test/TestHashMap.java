package org.spring.boot.test;

import org.junit.Test;
import org.spring.hashmap.MaySameHashModel;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author Chris
 * @version 1.0.0
 * @since 2023-07-25
 */
public class TestHashMap {
    @Test
    public void test1() {
        System.out.println("===== test1 =====");
        int initialCapacity = 2;
        HashMap<String, String> map = new HashMap<>(initialCapacity);
        for (int i = 1; i <= initialCapacity; i++) {
            map.put("key_" + i, "value_" + i);
        }
        System.out.println("已装载");
        map.put("key_" + (initialCapacity + 1), "value_" + (initialCapacity++));

        String aa = map.get("key_" + initialCapacity);
        System.out.println(aa);
    }

    @Test
    public void test2() {
        System.out.println("===== test1 =====");
        int initialCapacity = 16;
        HashMap<MaySameHashModel, String> map = new HashMap<>(initialCapacity);
        // HashMap Node数组+链表的方式存储数据，如果某个链表长度大于 8 & Node数组长度大于 64 ，则此链表将转化成红黑树。
        // MaySameHashModel 返回相同的 hashCode 值，这样就会更快的形成链表或转换成红黑树结构
        for (int i = 1; i <= initialCapacity; i++) {
            MaySameHashModel maySameHashModel = new MaySameHashModel();
            maySameHashModel.setId(i);
            maySameHashModel.setName("key_" + i);
            map.put(maySameHashModel, "value_" + i);
        }
        System.out.println("已装载");
        MaySameHashModel maySameHashModel = new MaySameHashModel();
        maySameHashModel.setId((initialCapacity + 1));
        maySameHashModel.setName("key_" + (initialCapacity + 1));
        map.put(maySameHashModel, "value_" + (initialCapacity + 1));

        String aa = map.get(maySameHashModel);
        System.out.println(aa);
    }

    /**
     * static final 变量：必须初始化一个值 final 变量：必须初始化一个值，可以直接赋值也可以在构造方法中赋值 static 变量：可以不初始化，但是有默认值
     *
     * 那么static final和final 又有何区别呢？
     * static修饰的属性强调它是只有一个，被所有对象所共享。
     * final修饰的属性表明是一个常数（创建后不能被修改）。
     * static final修饰的属性表示一旦给定值（常数），就不可修改，并且可以通过类名访问（因为只有一个）。
     */
    @Test
    public void test3() throws Exception {
        System.out.println("===== test3 =====");
        MaySameHashModel model = new MaySameHashModel();
        Field a = MaySameHashModel.class.getDeclaredField("mode1");
        // Field a = MaySameHashModel.class.getDeclaredField("mode2");
        // Field a = MaySameHashModel.class.getDeclaredField("mode3");
        a.setAccessible(true);
        System.out.println("反射修改前：" + a.get(model));
        a.setInt(model, 3);
        System.out.println("反射修改后：" + a.get(model));
    }
}
