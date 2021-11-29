package com.epam.rd.java.basic.practice6.part5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static com.epam.rd.java.basic.practice6.part5.Part5.SPACE;
import static org.junit.Assert.assertEquals;

public class TreeTest {

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
    public void treeTest1() {
        Tree<Integer> tree = new Tree<>();

        System.out.println();
        System.out.println(tree.add(3));
        System.out.println();
        String expected = "\r\ntrue\r\n\r\n";
        assertEquals(expected, out.toString());
    }

    @Test
    public void treeTest2() {
        Tree<Integer> tree = new Tree<>();

        System.out.println();
        System.out.println(tree.add(3));
        System.out.println();
        System.out.println(tree.remove(3));
        System.out.println();
        System.out.println(tree.add(3));
        System.out.println();
        String expected = "\r\ntrue\r\n\r\ntrue\r\n\r\ntrue\r\n\r\n";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}