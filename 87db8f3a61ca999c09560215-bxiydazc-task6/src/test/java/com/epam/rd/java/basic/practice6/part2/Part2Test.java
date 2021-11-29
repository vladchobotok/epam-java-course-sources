package com.epam.rd.java.basic.practice6.part2;

import com.epam.rd.java.basic.practice6.part1.WordContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class Part2Test {

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
    public void part2Test() {
        Part2 part2 = new Part2(4);
        String expected = "";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}