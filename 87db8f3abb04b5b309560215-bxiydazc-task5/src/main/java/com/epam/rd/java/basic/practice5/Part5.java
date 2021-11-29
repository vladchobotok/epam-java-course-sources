package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {

    private RandomAccessFile raf;
    private static final int REPEATS_NUMBER = 20;
    private int currentDigit;

    public static void main(final String[] args) {
        try {
            Files.deleteIfExists(new File("part5.txt").toPath());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(null);
            logger.warning(e.toString());
        }
        File file = new File("part5.txt");

        Part5 part5 = new Part5(); 
        try {
            part5.raf = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            Logger logger = Logger.getLogger(null);
            logger.warning(e.toString());
        }
        part5.writeOutput();
        part5.printOutput();
    }

    public synchronized void writeOutput(){
        for (int i = 0; i < 10; i++) {
            ExtThread thread = new ExtThread();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                Thread.currentThread().interrupt();
            }
            currentDigit++;
        }
    }

    public void printOutput(){
        try {
            raf.seek(0);
            for (int i = 0; i < 10;i++) {
                System.out.println(raf.readLine());
            }
    } catch (IOException e) {
            Logger logger = Logger.getLogger(null);
            logger.warning(e.toString());
        }
    }

    public class ExtThread extends Thread {
        @Override
        public void run() {
            try {
                raf.seek(((long) currentDigit * REPEATS_NUMBER) + ((long) currentDigit * System.lineSeparator().length()));
                for (int i = 0; i < REPEATS_NUMBER; i++) {
                    Thread.sleep(1);
                    raf.write('0' + currentDigit);
                }
                raf.write(System.lineSeparator().getBytes());
            } catch (IOException | InterruptedException e) {
                Logger logger = Logger.getLogger(null);
                logger.warning(e.toString());
                Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
