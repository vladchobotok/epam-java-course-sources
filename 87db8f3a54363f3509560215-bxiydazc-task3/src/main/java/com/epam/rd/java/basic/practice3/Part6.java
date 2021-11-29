package com.epam.rd.java.basic.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) {
        String input = Util.getInput("part6.txt");
        System.out.println(input);
        System.out.println();
        System.out.println(convert(input));
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        Pattern mainPattern = Pattern.compile("[A-Za-zА-Яа-яЇїЁё]+");
        Matcher mainMatcher;
        String[] words = input.split("\n");
        int[] amount = new int[0];
        String[] wordsBase = new String[0];

        for(String word: words){
            mainMatcher = mainPattern.matcher(word);
            boolean isFound;
            while(mainMatcher.find()){
                isFound = false;
                for (int i = 0; i < wordsBase.length; i++) {
                    if(wordsBase[i].equals(mainMatcher.group())) {
                        amount[i] += 1;
                        isFound = true;
                        break;
                    }
                }
                if(!isFound){
                    int[] amountCpy = new int[amount.length + 1];
                    String[] wordsBaseCpy = new String[wordsBase.length + 1];
                    System.arraycopy(amount, 0, amountCpy, 0, amount.length);
                    System.arraycopy(wordsBase, 0, wordsBaseCpy, 0, wordsBase.length);
                    amountCpy[amountCpy.length - 1] = 1;
                    wordsBaseCpy[wordsBaseCpy.length - 1] = mainMatcher.group();
                    amount = amountCpy;
                    wordsBase = wordsBaseCpy;
                }
            }

        }
        sbBuilding(sb, mainPattern, words, wordsBase, amount);
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public static void sbBuilding(StringBuilder sb, Pattern mainPattern, String[] words, String[] wordsBase, int[] amount){
        for(String word: words){
            Matcher mainMatcher = mainPattern.matcher(word);
            boolean isRepeated;
            while(mainMatcher.find()){
                isRepeated = false;
                for (int i = 0; i < wordsBase.length; i++) {
                    if(mainMatcher.group().equals(wordsBase[i]) && amount[i] > 1){
                        sb.append("_");
                        sb.append(mainMatcher.group());
                        sb.append(" ");
                        isRepeated = true;
                        break;
                    }
                }
                if(isRepeated){
                    continue;
                }
                sb.append(mainMatcher.group());
                sb.append(" ");
            }
            sb.setLength(sb.length() - 1);
            sb.append("\n");
        }
    }
}
