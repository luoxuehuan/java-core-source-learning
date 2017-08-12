package com.java.jvm;

/**
 * Created by hulb on 17/5/23.
 *
 * ⚠️ 此代码 适用于JDK7 HotSpot 虚拟机 不适用与JDK8
 *
 * 由于 Full GC 的成本远远高于 Minor GC，因此某些情况下需要尽可能将对象分配在年轻代，
 * 虽然在大部分情况下，JVM 会尝试在 Eden 区分配对象，但是由于空间紧张等问题，很可能不得不将部分年轻对象提前向年老代压缩。
 * 因此，在 JVM 参数调优时可以为应用程序分配一个合理的年轻代空间，以最大限度避免新对象直接进入年老代的情况发生
 *
 *
 * -XX:+PrintGCDetails -Xmx20M -Xms20M
 *
 *
 * 增大 Eden 大小后清单 1 运行输出
 * -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn6M -Xss 128K
 *
 *
 * 清单 1 所示代码尝试分配 4MB 内存空间，观察一下它的内存使用情况。
 */
public class PutInEden {
    public static void main(String[] args) {
        byte[] b1, b2, b3, b4;//定义变量
        b1 = new byte[1024 * 1024];//分配 1MB 堆空间，考察堆空间的使用情况
        b2 = new byte[1024 * 1024];
        b3 = new byte[1024 * 1024];
        b4 = new byte[1024 * 1024];
        while (true){

        }
    }
}
