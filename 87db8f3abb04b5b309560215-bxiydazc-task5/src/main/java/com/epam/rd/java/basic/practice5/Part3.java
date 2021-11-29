package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {

    private int counter;

    private int counter2;

    private int numberOfThreads;
    private int numberOfIterations;
    private Thread[] threads;

    public Part3(int numberOfThreads, int numberOfIterations) {
        this.numberOfThreads = numberOfThreads;
        this.numberOfIterations = numberOfIterations;
        this.threads = new Thread[numberOfThreads];
    }

    public static void main(final String[] args) {
        Part3 part3 = new Part3(3, 5);
        part3.compare();
        part3.counter = 0;
        part3.counter2 = 0;
        System.out.println("---------------------");
        part3.compareSync();
    }

    public void compare() {
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new ExtThread();
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part3.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void compareSync() {
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new SyncThread();
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part3.class.getName()).log(Level.SEVERE, "Thread interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public class ExtThread extends Thread{
        @Override
        public void run() {
            output();
        }
    }

    public class SyncThread extends Thread{
        @Override
        public void run() {
            synchronized (Part3.this){
                output();
            }
        }
    }

    public void output(){
        for (int i = 0; i < numberOfIterations; i++) {
            try {
                System.out.println(counter + " == " + counter2);
                this.counter += 1;
                Thread.sleep(100);
                this.counter2 += 1;
            } catch (InterruptedException e) {
                Logger.getLogger(Part3.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
