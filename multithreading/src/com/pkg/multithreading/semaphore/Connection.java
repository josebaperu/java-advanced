package com.pkg.multithreading.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
    Semaphore sem = new Semaphore(10,true);
    private static Connection conn = new Connection();

    private int count = 0;
    private Connection(){

    }
    public static Connection getInstance() {
        return conn;
    }

    public void connect() throws InterruptedException {
        try {
            sem.acquire();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            doConnect();
        } finally {
            sem.release();
        }


    }
    public void doConnect() throws InterruptedException {
        synchronized (this) {
            count++;
            System.out.println("Conns " + count);
        }
        Thread.sleep(2000);
        synchronized (this) {
            count--;
            System.out.println("Conns " + count);
        }
    }
}
