package com.note.base;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * AtomicReference类提供了一个可以原子读写的对象引用变量。
 * 比较的是两个对象的地址是否相等。可以保证修改对象引用时的线程安全性。
 * 原子意味着尝试更改相同AtomicReference的多个线程（例如，使用比较和交换操作）不会使AtomicReference最终达到不一致的状态。
 * AtomicReference甚至有一个先进的compareAndSet方法，它可以将引用与预期值（引用）进行比较，如果它们相等，则在AtomicReference对象内设置一个新的引用。
 */
public class AtomicReference01 {
    @Test
    public void test01() {
        String initialReference = "initial value referenced";
        AtomicReference<String> atomicStringReference = new AtomicReference<>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);
    }
}
