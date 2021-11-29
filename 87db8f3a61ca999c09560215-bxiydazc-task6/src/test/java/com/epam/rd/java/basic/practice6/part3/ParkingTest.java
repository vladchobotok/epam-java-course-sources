package com.epam.rd.java.basic.practice6.part3;

import com.epam.rd.java.basic.practice6.part1.WordContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ParkingTest {

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
    public void ParkingTest() {
        Parking parking = new Parking(4);
        parking.arrive(2);
        parking.arrive(2);
        parking.print();
        String expected = "0011\r\n";
        assertEquals(expected, out.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ParkingTest2() {
        Parking parking = new Parking(4);
        parking.depart(-1);
        parking.print();
    }

    @Test(expected = IllegalArgumentException.class)
    public void ParkingTest3() {
        Parking parking = new Parking(4);
        parking.arrive(-1);
        parking.print();
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}