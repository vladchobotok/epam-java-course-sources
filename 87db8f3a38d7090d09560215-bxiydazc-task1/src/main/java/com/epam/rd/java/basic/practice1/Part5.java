package com.epam.rd.java.basic.practice1;

public class Part5 {

    public static void main(String[] args) {
        for(int i = 0; i < args.length; i++) {
            int number = Integer.parseInt(args[i]);
            int sum = 0;
            while (number != 0) {
                sum += number % 10;
                number /= 10;
            }
            if(i == args.length - 1) {
                System.out.print(sum);
                break;
            }
            System.out.print(sum + " ");
        }
    }
	
}
