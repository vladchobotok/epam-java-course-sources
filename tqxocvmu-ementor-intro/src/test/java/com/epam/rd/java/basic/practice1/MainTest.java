package com.epam.rd.java.basic.practice1;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    @Test
    public void firstTest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        PrintStream consoleStream = System.out;
        System.setOut(stream);
        Main.main(null);
        String result = outputStream.toString();
        System.out.flush();
        System.setOut(consoleStream);
        Assert.assertEquals("Hello, World", result);
    }
}
