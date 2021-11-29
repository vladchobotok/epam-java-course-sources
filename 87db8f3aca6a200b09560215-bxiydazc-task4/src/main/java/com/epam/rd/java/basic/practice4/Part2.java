package com.epam.rd.java.basic.practice4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args){
        createFile("part2.txt");
        String input = Part1.getInput("part2.txt");
        System.out.println("input ==> " + input);
        sortFile(input);
        String output = Part1.getInput("part2_sorted.txt");
        System.out.println("output ==> " + output);

    }

    public static void createFile(String name) {
        File file = new File(name);

        SecureRandom secureRandom = new SecureRandom();

        try (FileWriter writer = new FileWriter(file)) {
            for (int i = 0; i < 10; i++) {
                writer.write(Integer.toString(secureRandom.nextInt(51)));
                if (i == 9) {
                    break;
                }
                writer.write(" ");
            }
        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(null);
            logger.warning(e.toString());
        }
    }

    public static void sortFile(String input){
        String sortedFileName = "part2_sorted.txt";
        createFile(sortedFileName);
        try (FileWriter writer = new FileWriter(sortedFileName)) {
            Pattern pattern = Pattern.compile("-?\\d+");
            Matcher matcher = pattern.matcher(input);
            int[] numberArray = new int[10];
            int iter = 0;

            while (matcher.find()) {
                numberArray[iter] = Integer.parseInt(matcher.group());
                iter += 1;
            }
            for (int i = 0; i < numberArray.length; i++) {
                for (int j = i; j < numberArray.length; j++) {
                    if (numberArray[i] > numberArray[j]) {
                        int tmp = numberArray[i];
                        numberArray[i] = numberArray[j];
                        numberArray[j] = tmp;
                    }
                }
            }
            for (int i = 0; i < numberArray.length; i++) {
                writer.write(Integer.toString(numberArray[i]));
                if (i == 9) {
                    break;
                }
                writer.write(" ");
            }
        }
        catch (IOException e) {
            Logger logger = Logger.getLogger(null);
            logger.warning(e.toString());
        }
    }
}
