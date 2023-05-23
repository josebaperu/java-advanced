package com.pkg.multithreading.countdownlatch;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService service = Executors.newCachedThreadPool();
        IntStream.range(0, 3).forEach(e -> service.submit(new Processor(latch)));
        System.out.println("before await "+LocalTime.now());
        latch.await();
        System.out.println("after await "+LocalTime.now());
        service.shutdown();
    }
}
class Processor implements  Runnable {
    CountDownLatch latch; // synchronized
    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        try {
            System.out.println("Run before sleep: " + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println("Run after sleep : " + Thread.currentThread().getName());
        }catch (Exception ex) {
            Thread.currentThread().interrupt();
            ex.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}