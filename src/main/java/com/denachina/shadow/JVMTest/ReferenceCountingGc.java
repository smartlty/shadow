package com.denachina.shadow.JVMTest;

/**
 * VM Args: -XX:+PrintGCDetails
 * -XX:+PrintGC 输出GC日志
 * -XX:+PrintGCDetails 输出GC的详细日志
 * -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
 * -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 * -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
 * -Xloggc:../logs/gc.log 日志文件的输出路径
 */
public class ReferenceCountingGc {
    public Object instace = null;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGc(){
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();
        objA.instace = objB;
        objB.instace = objA;

        objA = null;
        objB = null;

        // 假设在这行发生GC，objA 和 objB 是否能被回收
        System.gc();
    }

    public static void main(String[] args) {
        testGc();
    }
}
