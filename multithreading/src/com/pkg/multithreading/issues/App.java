package com.pkg.multithreading.issues;

import java.util.concurrent.atomic.AtomicInteger;

public class App  {

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.doRun();
    }
    //AtomicInteger atomicInteger = new AtomicInteger(0);
    int count = 0;
    private  void increment(){ //synchronized
        count++;
    }
    public void doRun() throws InterruptedException{
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 200; i++){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //atomicInteger.getAndAdd(1);
                increment();
            }

        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 200; i++){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //atomicInteger.getAndAdd(1);
                increment();

            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //System.out.println("total count is : " + atomicInteger.get());
        System.out.println("total count is : " + count);
    }
}
