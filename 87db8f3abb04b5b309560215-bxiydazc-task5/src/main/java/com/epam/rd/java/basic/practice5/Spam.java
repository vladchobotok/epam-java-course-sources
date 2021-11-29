package com.epam.rd.java.basic.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spam {

    private Thread[] threads;

    public Spam(final String[] messages, final int[] delays) {
        if(messages.length == delays.length){
            threads = new Thread[messages.length];
            for (int i = 0; i < messages.length; i++) {
                threads[i] = new Worker(messages[i], delays[i]);
            }
        }
    }

    public static void main(final String[] args) {
        String[] messages = new String[] { "@@@", "bbbbbbb" };
        int[] times = new int[] { 333, 222 };
        Spam spam = new Spam(messages, times);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        spam.start();
        while (true){
            try {
                Thread.sleep(2000);
                if (reader.readLine().isEmpty()) {
                    spam.stop();
                    break;
                }

            } catch (IOException | InterruptedException e) {
                Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static class Worker extends Thread {

        private String message;
        private int delay;

        Worker(String message, int delay){
            this.message = message;
            this.delay = delay;
        }

        @Override
        public void run() {
            while(true){
                System.out.print(message);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}
