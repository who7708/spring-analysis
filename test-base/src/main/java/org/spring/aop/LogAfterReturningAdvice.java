// package org.spring.aop;
//
// import org.springframework.aop.AfterReturningAdvice;
//
// import java.lang.reflect.Method;
//
// public class LogAfterReturningAdvice implements AfterReturningAdvice {
//
//     @Override
//     public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
//         System.out.println("==============调用成功返回，返回值是：" + returnValue);
//         System.out.println("Method: " + method);
//         if (returnValue instanceof User) {
//             //不能修改返回值，但可以修改返回值的某些属性，因为是对象引用
//             ((User) returnValue).setName("modifyedName");
//         }
//     }
//
// }