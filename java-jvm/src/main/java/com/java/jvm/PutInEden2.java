package com.java.jvm;

/**
 * Created by hulb on 17/5/23.
 *
 * -XX:+PrintGCDetails -Xmx1000M -Xms500M -Xmn100M -XX:SurvivorRatio=8
 *
 * 不同大小内存分配
 */
public class PutInEden2 {
    public static void main(String[] args) {
        byte[] b1, b2, b3;
        b1 = new byte[1024 * 512];//分配 0.5MB 堆空间
        b2 = new byte[1024 * 1024 * 4];//分配 4MB 堆空间
        b3 = new byte[1024 * 1024 * 4];
        b3 = null; //使 b3 可以被回收
        b3 = new byte[1024 * 1024 * 4];//分配 4MB 堆空间
    }
}