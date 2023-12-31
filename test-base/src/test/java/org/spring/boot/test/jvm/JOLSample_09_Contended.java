// /*
//  * Copyright (c) 2014, Oracle America, Inc.
//  * All rights reserved.
//  *
//  * Redistribution and use in source and binary forms, with or without
//  * modification, are permitted provided that the following conditions are met:
//  *
//  *  * Redistributions of source code must retain the above copyright notice,
//  *    this list of conditions and the following disclaimer.
//  *
//  *  * Redistributions in binary form must reproduce the above copyright
//  *    notice, this list of conditions and the following disclaimer in the
//  *    documentation and/or other materials provided with the distribution.
//  *
//  *  * Neither the name of Oracle nor the names of its contributors may be used
//  *    to endorse or promote products derived from this software without
//  *    specific prior written permission.
//  *
//  * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
//  * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
//  * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
//  * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
//  * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
//  * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
//  * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
//  * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
//  * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
//  * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
//  * THE POSSIBILITY OF SUCH DAMAGE.
//  */
// package org.spring.boot.test.jvm;
//
// import org.openjdk.jol.info.ClassLayout;
// import org.openjdk.jol.vm.VM;
//
// import java.util.HashMap;
//
// import static java.lang.System.out;
//
// /**
//  * @author Aleksey Shipilev
//  */
// public class JOLSample_09_Contended {
//
//     /*
//      * This is an example of special annotations that can affect the field layout.
//      *
//      * In order to dodge false sharing, users can put the @Contended annotation
//      * on the selected fields/classes. The conservative effect of this annotation
//      * is laying out the fields at sparse offsets, effectively providing the
//      * artificial padding.
//      *
//      * This example requires at least JDK 8 (for sun.misc.Contended), or JDK 9
//      * (for jdk.internal.vm.annotation.Contended). Any JDK also requires
//      * -XX:-RestrictContended to access @Contended from unprivileged code.
//      */
//
//     public static void main(String[] args) {
//         out.println(VM.current().details());
//         out.println(ClassLayout.parseClass(B.class).toPrintable());
//     }
//
//     public static class A {
//         int a;
//
//         int b;
//
//         @sun.misc.Contended
//         // @jdk.internal.vm.annotation.Contended
//         int c;
//
//         int d;
//     }
//
//     public static class B extends A {
//         int e;
//
//         @sun.misc.Contended("first")
//         // @jdk.internal.vm.annotation.Contended("first")
//         int f;
//
//         @sun.misc.Contended("first")
//         // @jdk.internal.vm.annotation.Contended("first")
//         int g;
//
//         @sun.misc.Contended("last")
//         // @jdk.internal.vm.annotation.Contended("last")
//         int i;
//
//         @sun.misc.Contended("last")
//         // @jdk.internal.vm.annotation.Contended("last")
//         int k;
//     }
//
//     public static class C extends B {
//         HashMap<String, Integer> map;
//
//         SomeEnum someEnum;
//
//         boolean bol1, bol2;
//
//         byte b1, b2;
//
//         char c1, c2;
//
//         double d1, d2;
//
//         float f1, f2;
//
//         int i1, i2;
//
//         long l1, l2;
//
//         short s1, s2;
//     }
//
// }
