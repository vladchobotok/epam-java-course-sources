package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        String input = Util.getInput("part3.txt");
        System.out.println(input);
        System.out.println();
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String [] words = input.split("\n");
        Matcher mainMatcher;
        Pattern mainPattern = Pattern.compile("[A-Za-zА-Яа-яЇїЁё]+");

        for(String word: words){
            mainMatcher = mainPattern.matcher(word);
            while (mainMatcher.find()){
                if(mainMatcher.group().length() > 2)
                {
                    if(Character.toLowerCase(mainMatcher.group().charAt(0)) == mainMatcher.group().charAt(0)) {
                        sb.append(Character.toUpperCase(mainMatcher.group().charAt(0)));
                    }
                    else if(Character.toUpperCase(mainMatcher.group().charAt(0)) == mainMatcher.group().charAt(0)) {
                        sb.append(Character.toLowerCase(mainMatcher.group().charAt(0)));
                    }
                    sb.append(mainMatcher.group().substring(1));
                }
                else {
                    sb.append(mainMatcher.group());
                }
                sb.append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
