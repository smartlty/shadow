package com.denachina.shadow.JVMTest;

import java.util.Vector;

public class Singleton {
    private volatile static Singleton instance;
    private static Vector<Integer> vector = new Vector<>();

    private static Singleton getInstance(){
        if (instance ==null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());

        while (true){
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(
                    ()->{
                        synchronized (vector){
                            for (int i = 0; i < vector.size(); i++) {
                                vector.remove(i);
                            }
                        }

            });

            Thread printThred = new Thread(
                    ()->{
                        synchronized (vector){
                            for (int i = 0; i < vector.size(); i++) {
                                System.out.println(vector.get(i));
                            }
                        }
            });

            removeThread.start();
            printThred.start();

            while (Thread.activeCount() > 20);
        }
    }
}
