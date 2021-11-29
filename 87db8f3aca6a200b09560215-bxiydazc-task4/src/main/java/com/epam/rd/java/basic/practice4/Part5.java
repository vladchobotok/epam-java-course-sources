package com.epam.rd.java.basic.practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if(input.equals("stop")){
                break;
            }
            String[] parts = input.split(" ");
            String key = parts[0];
            String locale = parts[1];
            System.out.println(getValueFromResources(key, locale));
        }
    }

    public static String getValueFromResources(String key, String locale){
        Locale locale1 = new Locale(locale);
        ResourceBundle rb = ResourceBundle.getBundle("resources", locale1);
        return rb.getString(key);
    }
}
