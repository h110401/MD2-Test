package view;

import java.util.Scanner;

public class Main {

    public Main() {
        System.out.println("MENU");
        System.out.println("1. Singer Menu");
        System.out.println("2. Song Menu");
        System.out.println("3. Exit");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
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
                System.out.println("Invalid choice");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
