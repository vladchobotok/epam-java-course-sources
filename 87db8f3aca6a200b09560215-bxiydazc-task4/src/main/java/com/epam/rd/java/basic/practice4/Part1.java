package com.epam.rd.java.basic.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class Part1 {

    public static void main(String[] args) {
        String input = getInput("part1.txt");
        String output = deleteChar(input);
        System.out.println(output);
    }

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"))) {
            String currentLine = null;
            while ((currentLine = br.readLine()) != null) {
                sb.append(currentLine);
                sb.append("\n");
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger(null);
            logger.warning(e.toString());
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static String deleteChar(String input) {
        return input.replaceAll("((?<=^|\\s)([A-Za-zА-Яа-яЇїІіЁёЄєҐґ][A-Za-zА-Яа-яЇїІіЁёЄєҐґ])(?=[A-Za-zА-Яа-яЇїІіЁёЄєҐґ][A-Za-zА-Яа-яЇїІіЁёЄєҐґ]+($|\\s)))", "");
    }
}
