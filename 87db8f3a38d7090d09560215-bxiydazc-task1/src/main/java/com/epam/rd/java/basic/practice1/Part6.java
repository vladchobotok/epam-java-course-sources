package com.epam.rd.java.basic.practice1;

public class Part6 {

    public static void main(String[] args) {
        int[] array = new int[Integer.parseInt(args[0])];
        int numbers = 0;
        int i = 0;
        while(numbers != Integer.parseInt(args[0])){
            if(isPrime(i) && i != 0 && i != 1) {
                array[numbers] = i;
                numbers++;
            }
            i++;
        }
        for(int j = 0; j < array.length; j++) {
            if(j == array.length - 1) {
                System.out.print(array[j]);
                break;
            }
            System.out.print(array[j] + " ");
        }
    }

    public static boolean isPrime(int number) {
        for(int i = 2; i <= number/2; i++)
        {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
