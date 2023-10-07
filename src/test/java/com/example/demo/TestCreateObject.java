package com.example.demo;

/**
 * @author Chris
 * @date 2020/05/13
 * @since 1.0.0
 */
public class TestCreateObject {
    public static void main(String[] args) {
        Object o = new Object();
    }
}
// Classfile /G:/IdeaProjects/jdk7-demo/target/test-classes/com/example/demo/TestCreateObject.class
//   Last modified 2020-5-13; size 472 bytes
//   MD5 checksum 4d364e7fd03c42d4c2e62e592029eb67
//   Compiled from "TestCreateObject.java"
// public class com.example.demo.TestCreateObject
//   minor version: 0
//   major version: 51
//   flags: ACC_PUBLIC, ACC_SUPER
// Constant pool:
//    #1 = Methodref          #2.#19         // java/lang/Object."<init>":()V
//    #2 = Class              #20            // java/lang/Object
//    #3 = Class              #21            // com/example/demo/TestCreateObject
//    #4 = Utf8               <init>
//    #5 = Utf8               ()V
//    #6 = Utf8               Code
//    #7 = Utf8               LineNumberTable
//    #8 = Utf8               LocalVariableTable
//    #9 = Utf8               this
//   #10 = Utf8               Lcom/example/demo/TestCreateObject;
//   #11 = Utf8               main
//   #12 = Utf8               ([Ljava/lang/String;)V
//   #13 = Utf8               args
//   #14 = Utf8               [Ljava/lang/String;
//   #15 = Utf8               o
//   #16 = Utf8               Ljava/lang/Object;
//   #17 = Utf8               SourceFile
//   #18 = Utf8               TestCreateObject.java
//   #19 = NameAndType        #4:#5          // "<init>":()V
//   #20 = Utf8               java/lang/Object
//   #21 = Utf8               com/example/demo/TestCreateObject
// {
//   public com.example.demo.TestCreateObject();
//     descriptor: ()V
//     flags: ACC_PUBLIC
//     Code:
//       stack=1, locals=1, args_size=1
//          0: aload_0
//          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//          4: return
//       LineNumberTable:
//         line 8: 0
//       LocalVariableTable:
//         Start  Length  Slot  Name   Signature
//             0       5     0  this   Lcom/example/demo/TestCreateObject;
//
//   public static void main(java.lang.String[]);
//     descriptor: ([Ljava/lang/String;)V
//     flags: ACC_PUBLIC, ACC_STATIC
//     Code:
//       stack=2, locals=2, args_size=1
//          0: new           #2                  // class java/lang/Object
//          3: dup
//          4: invokespecial #1                  // Method java/lang/Object."<init>":()V
//          7: astore_1
//          8: return
//       LineNumberTable:
//         line 10: 0
//         line 11: 8
//       LocalVariableTable:
//         Start  Length  Slot  Name   Signature
//             0       9     0  args   [Ljava/lang/String;
//             8       1     1     o   Ljava/lang/Object;
// }
// SourceFile: "TestCreateObject.java"