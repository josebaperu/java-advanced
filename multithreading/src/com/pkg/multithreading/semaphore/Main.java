package com.pkg.multithreading.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {

            ExecutorService service = Executors.newCachedThreadPool();
            IntStream.range(0,200).forEach( i -> {
                service.submit(() -> {
                    try {
                        Connection.getInstance().connect();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
            service.shutdown();
            service.awaitTermination(1, TimeUnit.DAYS);
    }
}
