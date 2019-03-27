package com.denachina.shadow.JVMTest;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class 内存占位符现象 {
    static class OOMObject{
        public byte[] placeholder = new byte[64 * 1024];
    }
    public static void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延迟，令监视曲线更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
