package com.epam.rd.java.basic.practice7;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DemoTest {

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
    public void demoTest() {
        Demo demo = new Demo();
        String expected = "";
        assertEquals(expected, out.toString());
    }

    @Test
    public void mainTest(){
        Main.main(new String[] { "input.xml" });
        String expected = "";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        File f1 = new File("output.dom.xml");
        File f2 = new File("output.sax.xml");
        File f3 = new File("output.stax.xml");
        f1.delete();
        f2.delete();
        f3.delete();
        System.setIn(IN);
        System.setOut(OUT);
    }
}
