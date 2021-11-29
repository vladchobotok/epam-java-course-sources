package com.epam.rd.java.basic.practice1;

public class Part7 {

    public static void main(String[] args) {
        final String ARROW = " ==> ";
        System.out.println("A" + ARROW + str2int("A") + ARROW + int2str(1));
        System.out.println("B" + ARROW + str2int("B") + ARROW + int2str(2));
        System.out.println("Z" + ARROW + str2int("Z") + ARROW + int2str(26));
        System.out.println("AA" + ARROW + str2int("AA") + ARROW + int2str(27));
        System.out.println("AZ" + ARROW + str2int("AZ") + ARROW + int2str(52));
        System.out.println("BA" + ARROW + str2int("BA") + ARROW + int2str(53));
        System.out.println("ZZ" + ARROW + str2int("ZZ") + ARROW + int2str(702));
        System.out.println("AAA" + ARROW + str2int("AAA") + ARROW + int2str(703));
    }

    public static int str2int(String number) {
        int intNumber = 0;
        number = new StringBuilder(number).reverse().toString();
        for(int i = 0; i < number.length(); i++) {
            int symbol = number.charAt(i) - 64;
            intNumber += symbol * Math.pow(26, i);
        }
        return intNumber;
    }

    public static String int2str(int number) {
        StringBuilder stringNumber = new StringBuilder();
        while(number > 0) {
            if(number % 26 == 0) {
                stringNumber.append('Z');
                number = number / 26 - 1;
                continue;
            }
            stringNumber.append((char) (number % 26 + 64));
            number /= 26;
        }
        return stringNumber.reverse().toString();
    }

    public static String rightColumn(String number) {
        return int2str((str2int(number)+1));
    }
}
