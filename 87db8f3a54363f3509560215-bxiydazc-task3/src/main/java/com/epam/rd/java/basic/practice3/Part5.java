package com.epam.rd.java.basic.practice3;

public class Part5 {

    public static void main(String[] args) {
        System.out.println(decimal2Roman(14));
        System.out.println(roman2Decimal("XIV"));
    }

    public static String decimal2Roman(int dec) {
        int[] numbers = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        String[] symbols = {"I","IV","V","IX","X","XL","L","XC","C"};
        StringBuilder sb = new StringBuilder();
        int i = 8;
        while(dec > 0)
        {
            int div = dec / numbers[i];
            dec = dec % numbers[i];
            while(div-->0)
            {
                sb.append(symbols[i]);
            }
            i--;
        }
        return sb.toString();
    }

    public static int roman2Decimal(String roman) {

        int number = 0;
        int[] decimal = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < decimal.length; i++ ) {
            while (roman.indexOf(symbols[i]) == 0) {
                number += decimal[i];
                roman = roman.substring(symbols[i].length());
            }
        }
        return number;
    }
}
