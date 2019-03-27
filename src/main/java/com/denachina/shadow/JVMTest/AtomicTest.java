package com.denachina.shadow.JVMTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static AtomicInteger race = new AtomicInteger();

    public static void increase(){
        race.incrementAndGet();
    }
    public static int i = 0;
    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                    System.out.println("Thread " + i + ", race " + race);
                }
            });
            threads[i].start();

        }
//        while (Thread.activeCount() == 2){
//            System.out.println(race);
//        }

    }
}
