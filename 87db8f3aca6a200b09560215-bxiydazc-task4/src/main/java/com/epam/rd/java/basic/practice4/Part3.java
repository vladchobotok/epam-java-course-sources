package com.epam.rd.java.basic.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        String input = Part1.getInput("part3.txt");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String method = scanner.nextLine();
            if(method.equals("stop")){
                break;
            }
            System.out.println(returnRequest(input, method));
        }
    }

    public static String returnRequest(String input, String method) {
        Pattern pattern;
        StringBuilder sb = new StringBuilder();
        switch (method){
            case "char":
                pattern = Pattern.compile("(?<=^|\\s)[A-Za-zА-Яа-яЇїІіЁёЄєҐґ](?=$|\\s)");
                break;
            case "int":
                pattern = Pattern.compile("(?<=^|\\s)(-?\\d+)(?=$|\\s)");
                break;
            case "double":
                pattern = Pattern.compile("(((-?\\d+)|())[\\.](-?\\d+))");
                break;
            case "String":
                pattern = Pattern.compile("(?<=^|\\s)[A-Za-zА-Яа-яЇїІіЁёЄєҐґ][A-Za-zА-Яа-яЇїІіЁёЄєҐґ]+(?=$|\\s)");
                break;
            default:
                return "Incorrect input";
        }
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()){
            sb.append(matcher.group());
            sb.append(" ");
        }
        return sb.toString();
    }
}
