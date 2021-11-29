package com.epam.rd.java.basic.practice6.part4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class RangeTest {

    private String str1 = "";
    private InputStream in = new ByteArrayInputStream(str1.getBytes(StandardCharsets.UTF_8));
    private OutputStream out = new ByteArrayOutputStream();
    private final InputStream IN = System.in;
    private final PrintStream OUT = System.out;

    @Before
    public void changeBefore() {
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void rangeTest() {
        Range range = new Range(3, 10);
        for (Integer el : range) {

            System.out.printf("%d ", el);
        }
        String expected = "3 4 5 6 7 8 9 10 ";
        assertEquals(expected, out.toString());
    }

    @Test
    public void rangeTest2() {
        Range range = new Range(3, 10, true);
        for (Integer el : range) {

            System.out.printf("%d ", el);

        }
        String expected = "10 9 8 7 6 5 4 3 ";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}
