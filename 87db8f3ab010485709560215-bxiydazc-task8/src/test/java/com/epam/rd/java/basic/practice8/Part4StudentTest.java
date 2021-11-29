package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Spy;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class Part4StudentTest {
    private String str1 = "";
    private InputStream in = new ByteArrayInputStream(str1.getBytes(StandardCharsets.UTF_8));
    private OutputStream out = new ByteArrayOutputStream();
    private final InputStream IN = System.in;
    private final PrintStream OUT = System.out;
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=root;password=pass;";
    private static final String USER = "root";
    private static final String PASS = "pass";

    @Spy //actually this annotation is not necessary here
    private static DBManager dbManager;

    @BeforeClass
    public static void beforeTest() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        dbManager = DBManager.getInstance();

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "  id INTEGER(11) NOT NULL AUTO_INCREMENT,\n" +
                    " login VARCHAR(20) NOT NULL, \n" +
                    "  PRIMARY KEY (id));";
            statement.executeUpdate(sql);
        }
    }

    private static void printList(List<?> list) {

        System.out.println(list);

    }

    @Before
    public void changeBefore() {
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @Test
    public void Part4StudentTest() {
        Team teamA = dbManager.getTeam("teamA");

        dbManager.deleteTeam(teamA);

        printList(dbManager.findAllTeams());

        String expected = "[teamB, teamC]\r\n";
        assertEquals(expected, out.toString());
    }

    @After
    public void changeAfter() {
        System.setIn(IN);
        System.setOut(OUT);
    }
}