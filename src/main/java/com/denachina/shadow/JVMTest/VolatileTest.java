package com.denachina.shadow.JVMTest;

public class VolatileTest {
    public static volatile int race = 0;
    public static int i = 0;
    public static synchronized void increase(){
        race++;
    }
    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];

        for ( i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                    System.out.println("Thread " + i + ", race " + race);
                }
            });

            threads[i].start();
        }
        // 等待所有线程执行完毕
//        while (Thread.activeCount() > 1) {
//            Thread.yield();
//        }
    }
}
