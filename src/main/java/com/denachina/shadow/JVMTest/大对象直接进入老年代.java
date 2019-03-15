package com.denachina.shadow.JVMTest;

public class 大对象直接进入老年代 {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM Args: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4 * _1MB]; // 直接分配至老年代
     }

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }
}
