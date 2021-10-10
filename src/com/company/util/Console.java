package com.company.util;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);


    public static int getInt(String prompt, int min, int max, String errorMsg) {
        int option = min -1;
        do {
            System.out.println(prompt);
            String input = scanner.nextLine();
            try {
                option = Integer.parseInt(input);

            }catch (NumberFormatException err) {
                System.out.println(errorMsg);
            }

        } while (option < min || option > max);
        return option;
    }

}
