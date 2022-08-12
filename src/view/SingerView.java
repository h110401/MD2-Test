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
        System.out.println("SINGER MENU");
        System.out.println("1. Show List Singer");
        System.out.println("2. Create Singer");
        System.out.println("3. Update Singer");
        System.out.println("4. Detail Singer");
        System.out.println("5. Delete Singer");
        System.out.println("6. Sort Singer List");
        System.out.println("7. Exit");
        int choice = Integer.parseInt(sc.nextLine());
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
                System.out.println("Invalid choice");
        }
        new SingerView().menu();
    }

    public void showListSinger() {
        for (Singer singer : singerController.getSingers()) {
            System.out.println(singer.getId() + ". " + singer.getName() + " " + singer.getAge());
        }
    }

    public void createSinger() {
        System.out.println("Enter singer name:");
        String name = sc.nextLine();
        System.out.println("Enter singer age:");
        int age = Integer.parseInt(sc.nextLine());
        int lastId = singerController.getSingers().stream().max(Comparator.comparing(Singer::getId)).get().getId();
        System.out.println(lastId);
        singerController.saveSinger(new Singer(lastId + 1, name, age));
    }

    public void deleteSinger() {
        showListSinger();
        System.out.println("Enter singer id to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        if (singerController.findSingerById(id) == null) {
            System.out.println("Id not found");
            return;
        }
        System.out.println("Are you sure want to delete (Y / N)?");
        String check = sc.nextLine();
        if (check.equalsIgnoreCase("y")) {
            singerController.deleteSinger(id);
        } else if (check.equalsIgnoreCase("n")) {
            System.out.println("Not deleting");
        } else {
            System.out.println("Invalid choice");
        }
    }

    public void showDetailSinger() {
        showListSinger();
        System.out.println("Enter singer id to show:");
        int id = Integer.parseInt(sc.nextLine());
        Singer singer = singerController.findSingerById(id);
        System.out.println("Id: " + singer.getId() + ", name: " + singer.getName() + ", age: " + singer.getAge());
        System.out.println("Song performed: ");
        for (Song song : singer.getSongList()) {
            System.out.println("\t" + song.getName());
        }
    }

    public void updateSinger() {
        showListSinger();
        System.out.println("EDIT SINGER");
        System.out.println("Enter singer id :");
        int id = Integer.parseInt(sc.nextLine());
        if (singerController.findSingerById(id) == null) {
            System.out.println("Id not found");
            return;
        }
        System.out.println("Enter new singer name:");
        String name = sc.nextLine();
        System.out.println("Enter new singer age:");
        int age = Integer.parseInt(sc.nextLine());
        singerController.saveSinger(new Singer(id, name, age));
    }

    public void sortSingerList() {
        singerController.sortSingerList();
        System.out.println("Singer list sorted");
        showListSinger();
    }

}
