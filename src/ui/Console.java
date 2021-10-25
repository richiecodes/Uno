package ui;

import java.util.Scanner;

public class Console {
    static Scanner scanner = new Scanner(System.in);

    public static int getInt(int min, int max, String query) {
        int choice;
        do {
            System.out.println(query);
            choice = scanner.nextInt();
        } while (choice < min || choice > max);

        return choice;
    }

    public static String getString(String query) {
        System.out.println(query);
        String s;

        do {
            s = scanner.nextLine().trim();
        } while (s.isEmpty());

        return s;
    }

    public static void cls() {
        int i = 100;
        while (i > 0) {
            System.out.println();
            i--;
        }
    }
}
