package org.spring.boot.test.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
class User {
    String userName;

    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);
        User w5 = new User("li4", 25);

        atomicReference.set(z3);
        System.out.printf("%-10s, %-10s\n", atomicReference.compareAndSet(w5, li4), atomicReference.get());
        System.out.printf("%-10s, %-10s\n", atomicReference.compareAndSet(z3, li4), atomicReference.get());

    }
}
