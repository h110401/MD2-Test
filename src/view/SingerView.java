package view;

import controller.SingerController;
import model.Singer;
import model.Song;

import java.util.Comparator;
import java.util.Scanner;

public class SingerView {

    private final Scanner sc = new Scanner(System.in);
    private final SingerController singerController = new SingerController();

    public void menu() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                      SINGER MENU                       |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|    1. Show List Singer                                 |");
        System.out.println("|    2. Create Singer                                    |");
        System.out.println("|    3. Update Singer                                    |");
        System.out.println("|    4. Detail Singer                                    |");
        System.out.println("|    5. Delete Singer                                    |");
        System.out.println("|    6. Sort Singer List                                 |");
        System.out.println("|    7. Back                                             |");
        System.out.println("'--------------------------------------------------------'\n");

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter your choice :                                 |");
        System.out.print("|    ");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        switch (choice) {
            case 1:
                this.showListSinger();
                break;
            case 2:
                this.createSinger();
                break;
            case 3:
                this.updateSinger();
                break;
            case 4:
                this.showDetailSinger();
                break;
            case 5:
                this.deleteSinger();
                break;
            case 6:
                this.sortSingerList();
                break;
            case 7:
                new Main();
                break;
            default:
                System.out.println(".--------------------------------------------------------.");
                System.out.println("|    Invalid choice !!!                                  |");
                System.out.println("'--------------------------------------------------------'\n");
        }
        new SingerView().menu();
    }

    public void showListSinger() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|      ID  |  NAME                     |  AGE            |");
        System.out.println("|--------------------------------------------------------|");
        for (Singer singer : singerController.getSingers()) {
            System.out.printf("|%8d  |  %-25s|  %-15d|\n", singer.getId(), singer.getName(), singer.getAge());
        }
        System.out.println("'--------------------------------------------------------'\n");
    }

    public void createSinger() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter singer's name to create :                     |");
        System.out.print("|    ");
        String name = sc.nextLine();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter singer's age :                                |");
        System.out.print("|    ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        int lastId = singerController.getSingers().stream().max(Comparator.comparing(Singer::getId)).get().getId();
        singerController.saveSinger(new Singer(lastId + 1, name, age));
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Create successfully !                               |");
        System.out.println("'--------------------------------------------------------'\n");
    }

    public void deleteSinger() {
        showListSinger();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter singer's id to delete :                       |");
        System.out.print("|    ");
        int id = Integer.parseInt(sc.nextLine());
        if (singerController.findSingerById(id) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|    ID not found!                                       |");
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Are you sure want to delete (Y / N)?                |");
        System.out.print("|    ");
        String check = sc.nextLine();
        System.out.println("'--------------------------------------------------------'\n");
        if (check.equalsIgnoreCase("y")) {
            singerController.deleteSinger(id);
        } else if (check.equalsIgnoreCase("n")) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|   Delete cancelled.                                    |");
            System.out.println("'--------------------------------------------------------'\n");
        } else {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|    Invalid choice !!!                                  |");
            System.out.println("'--------------------------------------------------------'\n");
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Delete successfully!                                |");
        System.out.println("'--------------------------------------------------------'\n");
    }

    public void showDetailSinger() {
        showListSinger();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter singer's id to show details :                 |");
        System.out.print("|    ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        Singer singer = singerController.findSingerById(id);
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                   SINGER'S DETAILS                     |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|      ID  |  NAME                     |  AGE            |");
        System.out.println("|--------------------------------------------------------|");
        System.out.printf("|%8d  |  %-25s|  %-15d|\n", singer.getId(), singer.getName(), singer.getAge());
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|    Songs performed :                                   |");
        System.out.println("|--------------------------------------------------------|");
        for (Song song : singer.getSongList()) {
            System.out.printf("|        %-48s|\n", song.getName());
        }
        System.out.println("'--------------------------------------------------------'\n");
    }

    public void updateSinger() {
        showListSinger();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter singer's id to edit :                         |");
        System.out.print("|    ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        if (singerController.findSingerById(id) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|    ID not found!                                       |");
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter new singer's name :                           |");
        System.out.print("|    ");
        String name = sc.nextLine();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter new singer's age :                            |");
        System.out.print("|    ");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        singerController.saveSinger(new Singer(id, name, age));
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Edit successfully!                                  |");
        System.out.println("'--------------------------------------------------------'\n");
    }

    public void sortSingerList() {
        singerController.sortSingerList();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Singer list sorted !                                |");
        System.out.println("'--------------------------------------------------------'\n");
        showListSinger();
    }

}
