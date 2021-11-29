package com.epam.rd.java.basic.practice6.part6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class Part6Test {

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
    public void part6Test1() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "frequency"});
        String expected = "whale ==> 3\r\n" +
                "cheetah ==> 4\r\n" +
                "bison ==> 3\r\n";
        assertEquals(expected, out.toString());
    }

    @Test
    public void part6Test2() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "length"});
        String expected = "chimpanzee ==> 10\r\n" +
                "mongoose ==> 8\r\n" +
                "tortoise ==> 8\r\n";
        assertEquals(expected, out.toString());
    }

    @Test
    public void part6Test3() {
        Part6.main(new String[] {"--input", "part6.txt", "--task", "duplicates"});
        String expected = "RAUGAJ\r\n" +
                "NOSIB\r\n" +
                "ELAHW\r\n";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}