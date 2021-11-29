package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    private int[][] array;
    private int length;
    private int width;
    private long counter;

    public static void main(final String[] args) {
        Part4 part4 = new Part4();
        part4.readArray();
        part4.parallelThreads();
        part4.singleThread();
    }

    public String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "utf8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            Logger logger = Logger.getLogger(null);
            logger.warning(ex.toString());
        }
        return sb.toString();
    }

    public void readArray(){
        String strArray = getInput("part4.txt");
        String [] lines = strArray.split("[\\r\\n]+");

        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(lines[0]);

        width = lines.length;
        length = 0;

        while (matcher.find()){
            length++;
        }

        array = new int[width][length];

        for (int i = 0; i < width; i++) {
            matcher = pattern.matcher(lines[i]);
            int j = 0;
            while (matcher.find()){
                array[i][j] = Integer.parseInt(matcher.group());
                j++;
            }
        }
    }

    public int maximumValue(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < length; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Logger.getLogger(Part3.class.getName()).log(Level.SEVERE, "Thread has been interrupted", e);
                Thread.currentThread().interrupt();
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public void singleThread() {
        long time = System.currentTimeMillis();
        int max = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    Logger.getLogger(Part3.class.getName()).log(Level.SEVERE, "Thread interrupted", e);
                    Thread.currentThread().interrupt();
                }
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        System.out.println(max);
        System.out.println();
        System.out.println((System.currentTimeMillis() - time));
    }

    public void parallelThreads() {
        int[] threads = new int[width];

        for (int i = 0; i < width; i++) {
            int count = i;
            Thread thread = new Thread(() -> {
                long time = System.currentTimeMillis();
                threads[count] = maximumValue(array[count]);
                time = System.currentTimeMillis() - time;
                if (counter < time) {
                    counter = time;
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Logger.getLogger(Part3.class.getName()).log(Level.SEVERE, "Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
        Arrays.sort(threads);
        System.out.println(threads[width - 1]);
        System.out.println();
        System.out.println(counter);
        System.out.println();
    }
}
