package com.smart_device.app_device._device.input;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleInput {
    public static String optionsInput(List<InputOption> options) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------- Options ----------------------");
            options.forEach(o -> System.out.println(o.getValue() + " -> " + o.getLabel()));

            System.out.print("Option: ");
            try {
                String input = scanner.nextLine().trim();
                for (InputOption option : options) {
                    if (option.getValue().equalsIgnoreCase(input)) {
                        return option.getValue();
                    }
                }
                System.out.println("Invalid input. Please try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public static int numberInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------- Number input ----------------------");
            try {
                System.out.print("Input number: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}
