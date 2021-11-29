package com.epam.rd.java.basic.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(input);
        System.out.println();
        System.out.println(convert1(input));
        System.out.println();
        System.out.println(convert2(input));
        System.out.println();
        System.out.println(convert3(input));
        System.out.println();
        System.out.println(convert4(input));
    }

    public static String convert1(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern mainPattern = Pattern.compile("(.*);(.*);(.+?@.*)");
        Matcher mainMatcher = mainPattern.matcher(input);

        while (mainMatcher.find()) {
            sb.append(mainMatcher.group(1));
            sb.append(": ");
            sb.append(mainMatcher.group(3));
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String convert2(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern mainPattern = Pattern.compile("(.*);(.*) (.*);(.+?@.*)");
        Matcher mainMatcher = mainPattern.matcher(input);

        while (mainMatcher.find()) {
            sb.append(mainMatcher.group(3));
            sb.append(" ");
            sb.append(mainMatcher.group(2));
            sb.append(" (email: ");
            sb.append(mainMatcher.group(4));
            sb.append(")");
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String convert3(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern mainPattern = Pattern.compile("(.*);(.*);(.*)((?<=@).*)");
        Matcher mainMatcher = mainPattern.matcher(input);
        Matcher addMatcher;
        String[] emails = new String[0];
        boolean isInBase;

        while (mainMatcher.find()) {
            addMatcher = mainPattern.matcher(input);
            isInBase = false;
            for (String email : emails) {
                if (email.equals(mainMatcher.group(4))) {
                    isInBase = true;
                    break;
                }
            }

            if(isInBase){
                continue;
            }

            sb.append(mainMatcher.group(4));
            sb.append(" ==> ");
            String[] cpyEmails = new String[emails.length + 1];
            System.arraycopy(emails, 0, cpyEmails, 0, emails.length);
            cpyEmails[cpyEmails.length - 1] = mainMatcher.group(4);
            emails = cpyEmails;
            while (addMatcher.find()) {
                if (addMatcher.group(4).equals(emails[emails.length - 1])) {
                    sb.append(addMatcher.group(1));
                    sb.append(", ");
                }
            }

            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern mainPattern = Pattern.compile("(.*);(.*);(.*)");
        Matcher mainMatcher = mainPattern.matcher(input);
        int iter = 0;

        while (mainMatcher.find()) {
            sb.append(mainMatcher.group(0));
            if(iter == 0){
                sb.append(";Password\n");
            }
            else {
                SecureRandom secureRandom = new SecureRandom();
                sb.append(";");
                sb.append(secureRandom.nextInt(10));
                sb.append(secureRandom.nextInt(10));
                sb.append(secureRandom.nextInt(10));
                sb.append(secureRandom.nextInt(10));
                sb.append("\n");
            }
            iter++;
        }
        return sb.toString();
    }
}