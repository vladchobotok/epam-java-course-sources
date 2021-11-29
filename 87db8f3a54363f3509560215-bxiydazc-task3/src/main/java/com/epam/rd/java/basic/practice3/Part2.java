package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public static void main(String[] args) {
        String input = Util.getInput("part2.txt");
        System.out.println(input);
        System.out.println();
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("[A-Za-zА-Яа-яЇїЁё]+");
        Matcher matcher = pattern.matcher(input);
        int longest = -1;
        int shortest = 2000000000;
        String[] longestStr = new String[0];
        String[] shortestStr = new String[0];
        boolean nextIteration = false;

        while (matcher.find()) {
            nextIteration = isNextIter(longestStr, matcher, nextIteration);
            nextIteration = isNextIter(shortestStr, matcher, nextIteration);
            if(nextIteration){
                nextIteration = false;
            }
            else if(longest < matcher.group().length()) {
                longest = matcher.group().length();
                longestStr = new String[1];
                longestStr[0] = matcher.group();
            }
            else if(shortest > matcher.group().length()) {
                shortest = matcher.group().length();
                shortestStr = new String[1];
                shortestStr[0] = matcher.group();

            }
            else if(longest == matcher.group().length()) {
                String[] longestCpy = new String[longestStr.length + 1];
                System.arraycopy(longestStr, 0, longestCpy, 0, longestStr.length);
                longestCpy[longestCpy.length - 1] = matcher.group();
                longestStr = longestCpy;
            }
            else if(matcher.group().length() == shortest) {
                String[] shortestCpy = new String[shortestStr.length + 1];
                System.arraycopy(shortestStr, 0, shortestCpy, 0, shortestStr.length);
                shortestCpy[shortestCpy.length - 1] = matcher.group();
                shortestStr = shortestCpy;
            }
        }
        sb.append("Min: ");
        for (String s : shortestStr) {
            sb.append(s);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n");
        sb.append("Max: ");
        for (String s : longestStr) {
            sb.append(s);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    public static boolean isNextIter(String[] str, Matcher matcher, boolean nextIteration){
        for (String s : str) {
            if (s.equals(matcher.group())) {
                nextIteration = true;
                break;
            }
        }
        return nextIteration;
    }
}
