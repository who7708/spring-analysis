// package org.spring.aop;
//
// public class LogAroundAdvice implements MethodInterceptor {
//
//     @Override
//     public Object invoke(MethodInvocation invocation) throws Throwable {
//         System.out.println("=============================方法调用开始===" + invocation.getMethod());
//         try {
//             Object result = invocation.proceed();
//             System.out.println("=============================方法调用正常结束===" + invocation.getMethod());
//             return result;
//         } catch (Exception e) {
//             System.out.println("=============================方法调用异常===" + invocation.getMethod());
//             throw e;
//         }
//     }
//
// }