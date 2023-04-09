package com.concurrency.a2_cas案例;

import sun.misc.Unsafe;

/**
 * 通过CAS解决并发修改问题
 *
 *
 * doSyncUpdate()方法中，我们调用了unsafe类中的compareAndSwapInt()方法来达到同样的目的，这个方法有四个参数，
 *
 * 分别是：当前对象实例、成员变量state在内存地址中的偏移量、预期值0、期望更改之后的值1。
 *
 * CAS机制会比较state内存地址偏移量对应的值和传入的预期值0是否相等，如果相等，就直接修改内存地址中state的值为1.
 *
 * 否则，返回false，表示修改失败，而这个过程是原子的，不会存在线程安全问题。
 *
 * CompareAndSwap是一个native方法，实际上它最终还是会面临同样的问题，就是先从内存地址中读取state的值，然后去比较，最后再修改。
 *
 * 这个过程不管是在什么层面上实现，都会存在原子性问题。
 *
 * 所以呢，CompareAndSwap的底层实现中，在多核CPU环境下，会增加一个Lock指令对缓存或者总线加锁，从而保证比较并替换这两个指令的原子性。
 *
 * CAS主要用在并发场景中，比较典型的使用场景有两个。
 *
 * 第一个是J.U.C里面Atomic的原子实现，比如AtomicInteger，AtomicLong。
 * 第二个是实现多线程对共享资源竞争的互斥性质，比如在AQS、ConcurrentHashMap、ConcurrentLinkedQueue等都有用到。
 */
public class Main1 {

    private static volatile int state = 0;
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(Main1.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private void doSyncUpdate() {
        if (unsafe.compareAndSwapInt(this, stateOffset, 0, 1)) {
            // do something
        }
    }
}
