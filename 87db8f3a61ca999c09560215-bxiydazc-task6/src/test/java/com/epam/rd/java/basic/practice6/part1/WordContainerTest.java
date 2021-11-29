package com.epam.rd.java.basic.practice6.part1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordContainerTest {

    private String str1 = "asd\n43\nasdf\nasd\n43\n434\nasdf\nkasdf\nasdf\nstop\nasdf\nstop";
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
    public void wordContainerTest() {
        WordContainer.main(new String[]{});
        String expected = "asdf : 3\r\n" +
                "43 : 2\r\n" +
                "asd : 2\r\n" +
                "434 : 1\r\n" +
                "kasdf : 1\r\n";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}
