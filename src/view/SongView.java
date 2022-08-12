package view;

import controller.SingerController;
import controller.SongController;
import model.Singer;
import model.Song;

import java.util.*;
import java.util.stream.Collectors;

public class SongView {

    private final Scanner sc = new Scanner(System.in);
    private final SongController songController = new SongController();

    private final SingerController singerController = new SingerController();

    public void menu() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                       SONG MENU                        |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|    1. Show List Song                                   |");
        System.out.println("|    2. Create Song                                      |");
        System.out.println("|    3. Update Song                                      |");
        System.out.println("|    4. Delete Song                                      |");
        System.out.println("|    5. Song Charts                                      |");
        System.out.println("|    6. Exit                                             |");
        System.out.println("'--------------------------------------------------------'\n");

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter your choice :                                 |");
        System.out.print("|    ");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        switch (choice) {
            case 1:
                this.showListSong();
                break;
            case 2:
                this.createSong();
                break;
            case 3:
                this.updateSong();
                break;
            case 4:
                this.deleteSong();
                break;
            case 5:
                this.chartsView();
                break;
            case 6:
                new Main();
                break;
            default:
                System.out.println(".--------------------------------------------------------.");
                System.out.println("|    Invalid choice !!!                                  |");
                System.out.println("'--------------------------------------------------------'\n");
        }
        new SongView().menu();

    }

    private void chartsView() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                      SONG CHARTS                       |");
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|    1. Top 5 songs have most like                       |");
        System.out.println("|    2. Top 5 songs have most listen                     |");
        System.out.println("'--------------------------------------------------------'\n");

        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter your choice :                                 |");
        System.out.print("|    ");
        int choice = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        List<Song> songList = songController.getSongs();
        switch (choice) {
            case 1:
                this.showTop5Like(songList);
                break;
            case 2:
                this.showTop5Listen(songList);
                break;
            default:
                System.out.println(".--------------------------------------------------------.");
                System.out.println("|    Invalid choice !!!                                  |");
                System.out.println("'--------------------------------------------------------'\n");
        }
    }

    private void showTop5Listen(List<Song> songList) {
        List<Song> sorted = songList.stream().sorted((s1, s2) -> s2.getListen() - s1.getListen()).collect(Collectors.toList());
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                   TOP 5 MOST LISTEN                    |");
        System.out.println("|--------------------------------------------------------|");
        showTopList(sorted);
        System.out.println("'--------------------------------------------------------'\n");
    }

    private void showTop5Like(List<Song> songList) {
        List<Song> sorted = songList.stream().sorted((s1, s2) -> s2.getLike() - s1.getLike()).collect(Collectors.toList());
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                    TOP 5 MOST LIKE                     |");
        System.out.println("|--------------------------------------------------------|");
        showTopList(sorted);
        System.out.println("'--------------------------------------------------------'\n");

    }

    private void showTopList(List<Song> sorted) {
        if (sorted.size() >= 5) {
            for (int i = 1; i <= 5; i++) {
                System.out.printf("|%3d     %-15s  |  Listen  %-5d  Like  %-5d  |\n", i, sorted.get(i - 1).getName(), sorted.get(i - 1).getListen(), sorted.get(i - 1).getLike());
            }
        } else {
            int i = 1;
            for (Song song : sorted) {
                System.out.printf("|%3d     %-15s  |  Listen  %-5d  Like  %-5d  |\n", i, song.getName(), song.getListen(), song.getLike());
                i++;
            }
        }
    }


    private void deleteSong() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter song's id to delete :                         |");
        System.out.print("|    ");
        int id = Integer.parseInt(sc.nextLine());
        if (songController.findSongById(id) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|    ID not found!                                       |");
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Are you sure want to delete (Y / N)?                |");
        System.out.print("|    ");
        String check = sc.nextLine();
        if (check.equalsIgnoreCase("y")) {
            songController.deleteSong(id);
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

    private void updateSong() {
        showListSong();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter song's id to edit :                           |");
        System.out.print("|    ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        if (songController.findSongById(id) == null) {
            System.out.println(".--------------------------------------------------------.");
            System.out.println("|    ID not found!                                       |");
            System.out.println("'--------------------------------------------------------'\n");
            return;
        }
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter song's name :                                 |");
        System.out.print("|    ");
        String name = sc.nextLine();
        System.out.println("'--------------------------------------------------------'\n");
        songController.saveSong(new Song(id, name));
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Edit successfully!                                  |");
        System.out.println("'--------------------------------------------------------'\n");
    }

    private void createSong() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter song's name to create :                       |");
        System.out.print("|    ");
        String songName = sc.nextLine();
        new SingerView().showListSinger();
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter singer ids to add. (Can type many id !)       |");
        System.out.print("|    ");
        String idString = sc.nextLine();
        System.out.println("'--------------------------------------------------------'\n");
        String[] idArray = idString.split("\\D+");
        int[] ids = Arrays.stream(idArray).mapToInt(Integer::parseInt).toArray();
        int lastId = songController.getSongs().stream().max(Comparator.comparing(Song::getId)).get().getId();
        Song newSong = new Song(lastId + 1, songName);
        addToList(newSong, ids);
        songController.saveSong(newSong);
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Create successfully !                               |");
        System.out.println("'--------------------------------------------------------'\n");
    }

    private void showListSong() {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|      ID  |  NAME             |  LISTEN    |  LIKE      |");
        System.out.println("|--------------------------------------------------------|");
        for (Song song : songController.getSongs()) {
            System.out.printf("|  %6d  |   %-15s |  %-8d  |  %-8d  |\n", song.getId(), song.getName(), song.getListen(), song.getLike());
        }
        System.out.println("'--------------------------------------------------------'\n");
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter song's id to show details :                   |");
        System.out.print("|    ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("'--------------------------------------------------------'\n");
        this.formViewDetailsSong(id);
    }

    private void formViewDetailsSong(int id) {
        Song song = songController.findSongById(id);
        song.setListen(song.getListen() + 1);
        showSongDetails(song);
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|    Enter 1 to like this song !                         |");
        System.out.print("|    ");
        int choose = Integer.parseInt(sc.nextLine());
        if (choose == 1) {
            song.setLike(song.getLike() + 1);
        }
        showSongDetails(song);

    }

    private void showSongDetails(Song song) {
        System.out.println(".--------------------------------------------------------.");
        System.out.println("|                    SONG'S DETAILS                      |");
        System.out.println("|--------------------------------------------------------|");
        System.out.printf("|    %-52s|\n", song.getName());
        System.out.println("|--------------------------------------------------------|");
        System.out.println("|    Performers:                                         |");
        for (Singer singer : song.getSingerList()) {
            System.out.printf("|        %-48s|\n", singer.getName());
        }
        System.out.println("|--------------------------------------------------------|");
        System.out.printf("|    Listened :  %-38d  |\n", song.getListen());
        System.out.printf("|    Liked :  %-41d  |\n", song.getLike());
        System.out.println("'--------------------------------------------------------'\n");
    }

    private void addToList(Song song, int[] ids) {
        for (int id : ids) {
            if (song.getSingerList().stream().filter(s -> s.getId() == id).findAny().orElse(null) == null) {
                song.getSingerList().add(singerController.findSingerById(id));
                singerController.findSingerById(id).getSongList().add(song);
            }
        }
    }

}
