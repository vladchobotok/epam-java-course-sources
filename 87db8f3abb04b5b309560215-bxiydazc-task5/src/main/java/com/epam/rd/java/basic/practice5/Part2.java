package com.epam.rd.java.basic.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Part2 {

    public static void main(final String[] args) {
        InputStream originalIn = System.in;
        ByteArrayInputStream bais = new ByteArrayInputStream(System.lineSeparator().getBytes());
        Thread t = new Thread(() -> Spam.main(null));
        try {
            t.start();
            System.setIn(bais);
            t.join();
        } catch (InterruptedException e) {
            t.interrupt();
            Thread.currentThread().interrupt();
        } finally {
            System.setIn(originalIn);
        }
    }
}
