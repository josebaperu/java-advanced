package com.pkg.multithreading.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Processor> futures = new ArrayList<>();
        IntStream.range(0,3).forEach(i -> futures.add(new Processor(i)));
        service.invokeAll(futures).forEach( f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        futures.clear();
        System.out.println("");
        IntStream.range(4,7).forEach(i -> futures.add(new Processor(i)));
        service.invokeAll(futures).forEach( f -> {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        service.shutdown();
    }
}
class Processor implements Callable<String> {
    int i;
    public Processor(int i) {
        this.i = i;
    }

    @Override
    public String call() throws InterruptedException {
        System.out.println(String.format("Thread %d started",i));
        int random = (int)(Math.random() * 2000);
        Thread.sleep(random);
        return (String.format("Thread %d returns %d ms",i,random));
    }
}