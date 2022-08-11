package view;

import java.util.Scanner;

public class Main {

    private final Scanner sc = new Scanner(System.in);

    public Main() {
        System.out.println("MENU");
        System.out.println("1. Singer Menu");
        System.out.println("2. Song Menu");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                new SingerView();
                break;
            case 2:
                new SongView();
                break;
            default:
                System.out.println("Invalid choice");
        }

    }

    public static void main(String[] args) {
        new Main();
    }
}
