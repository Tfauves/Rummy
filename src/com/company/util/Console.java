package com.company.util;

import com.company.deck.Card;
import com.company.rummy.Hand;
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

    public static String getString(String prompt, boolean isRequired) {
        String input;
        do {
            System.out.println(prompt);
            input = scanner.nextLine();
            if (isRequired && input.length() == 0) {
                System.out.println("required input");
                continue;
            }
            break;
        } while (true);

        return input;
    }

    public static void showHandWithIndex(Hand activeHand) {
        System.out.println(activeHand.displayHand());
        for (int i = 1; i < activeHand.getCards().size() + 1; i++) {
            System.out.print(i + "    ");
        }
    }

    public static void spaces() {
        int spaceCounter = 0;
        while (spaceCounter < 33) {

            System.out.println();
            spaceCounter++;
        }
    }


}
