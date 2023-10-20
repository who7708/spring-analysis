package org.spring.aop;

/**
 * @author skywalker
 */
public class SimpleChildAopBean extends SimpleAopBean {

    @Override
    public void testC() {
        System.out.println("child testC");
    }
}
