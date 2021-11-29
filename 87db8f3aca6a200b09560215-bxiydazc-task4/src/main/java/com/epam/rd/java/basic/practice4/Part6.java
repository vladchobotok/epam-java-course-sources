package com.epam.rd.java.basic.practice4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String input = Part1.getInput("part6.txt");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String locale = scanner.nextLine();
            if(locale.equals("stop")){
                break;
            }
            System.out.println(getLocaleWords(locale, input));
        }
    }

    public static String getLocaleWords(String locale, String input){
        StringBuilder sb = new StringBuilder(locale);
        sb.append(": ");
        Pattern pattern;
        switch (locale){
            case "Latn":
                pattern = Pattern.compile("[A-Za-z]+");
                break;
            case "Cyrl":
                pattern = Pattern.compile("[А-Яа-яЇїІіЁёЄєҐґ]+");
                break;
            default:
                sb.append("Incorrect input");
                return sb.toString();
        }
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()){
            sb.append(matcher.group());
            sb.append(" ");
        }
        return sb.toString();
    }
}
