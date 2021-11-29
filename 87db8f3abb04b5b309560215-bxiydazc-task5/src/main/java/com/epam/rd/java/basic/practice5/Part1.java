package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {

    public static class ExtendsThread extends Thread {

        @Override
        public void run(){
            for (int i = 0; i < 4; i++){
                System.out.println(this.getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static class ImplementsRunnable implements Runnable{

        @Override
        public void run(){
            for (int i = 0; i < 4; i++){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread child1 = new ExtendsThread();
        Thread child2 = new Thread(new ImplementsRunnable());
        child1.setName("First thread");
        child2.setName("Second thread");
        child1.start();
        try {
            child1.join();
        } catch (InterruptedException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, "Interrupted", e);
            Thread.currentThread().interrupt();
        }
        child2.start();
        try {
            child2.join();
        } catch (InterruptedException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, "Interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}
