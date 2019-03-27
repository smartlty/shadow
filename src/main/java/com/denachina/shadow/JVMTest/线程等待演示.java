package com.denachina.shadow.JVMTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 线程等待演示 {
    /**
     * 线程死循环
     */
    public static void createBusyThread(){
        Thread thread = new Thread(() -> {
            while (true)
                ;
        },"testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待
     */
    public static void createLockThread(final Object lock){
        Thread thread = new Thread(() -> {
            synchronized(lock){
                try {
                    lock.wait();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },"testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        createLockThread(new Object());
    }
}
