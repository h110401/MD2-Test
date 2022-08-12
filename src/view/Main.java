package view;

import java.util.Scanner;

public class Main {

    public Main() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                       MUSIC-MENU                       |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|    1. Singer Menu                                      |");
        System.out.println("|    2. Song Menu                                        |");
        System.out.println("|    3. Exit                                             |");
        System.out.println("'--------------------------------------------------------'\n");

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter your choice :                                 |");
        System.out.print("|    ");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        switch (choice) {
            case 1:
                new SingerView().menu();
                break;
            case 2:
                new SongView().menu();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println(".--------------------------------------------------------.");
                System.out.println("|    Invalid choice !!!                                  |");
                System.out.println("'--------------------------------------------------------'\n");

        }
        new Main();
    }

    public static void main(String[] args) {
        new Main();
    }
}
