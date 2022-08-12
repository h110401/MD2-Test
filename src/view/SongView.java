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
        System.out.println("SONG MENU");
        System.out.println("1. Show List Song");
        System.out.println("2. Create Song");
        System.out.println("3. Update Song");
        System.out.println("4. Delete Song");
        System.out.println("5. Song Charts");
        System.out.println("6. Exit");
        int choice = Integer.parseInt(sc.nextLine());
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
                System.out.println("Invalid choice");
        }
        new SongView().menu();

    }

    private void chartsView() {
        System.out.println("SONG CHARTS");
        System.out.println("1. Top 5 songs have most like");
        System.out.println("2. Top 5 songs have most listen");
        int choice = Integer.parseInt(sc.nextLine());
        List<Song> songList = songController.getSongs();
        switch (choice) {
            case 1:
                this.showTop5Like(songList);
                break;
            case 2:
                this.showTop5Listen(songList);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    private void showTop5Listen(List<Song> songList) {
        List<Song> clone = songList.stream().sorted((s1, s2) -> s2.getListen() - s1.getListen()).collect(Collectors.toList());
        if (clone.size() >= 5) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i + ". " + clone.get(i - 1).getName() + " Like:" + clone.get(i - 1).getLike() + " Listen:" + clone.get(i - 1).getListen());
            }
        } else {
            int i = 1;
            for (Song song : clone) {
                System.out.println(i + ". " + song.getName() + " Like:" + song.getLike() + " Listen:" + song.getListen());

                i++;
            }
        }

    }

    private void showTop5Like(List<Song> songList) {
        List<Song> clone = songList.stream().sorted((s1, s2) -> s2.getLike() - s1.getLike()).collect(Collectors.toList());
        if (clone.size() >= 5) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i + ". " + clone.get(i - 1).getName() + " Like:" + clone.get(i - 1).getLike() + " Listen:" + clone.get(i - 1).getListen());
            }
        } else {
            int i = 1;
            for (Song song : clone) {
                System.out.println(i + ". " + song.getName() + " Like:" + song.getLike() + " Listen:" + song.getListen());
                i++;
            }
        }
    }

    private void deleteSong() {
        System.out.println("Enter song's id to delete");
        int id = Integer.parseInt(sc.nextLine());
        if (songController.findSongById(id) == null) {
            System.out.println("Id not found");
            return;
        }
        System.out.println("Are you sure want to delete (Y / N)?");
        String check = sc.nextLine();
        if (check.equalsIgnoreCase("y")) {
            songController.deleteSong(id);
        } else if (check.equalsIgnoreCase("n")) {
            System.out.println("Not deleting");
        } else {
            System.out.println("Invalid choice");
        }
    }

    private void updateSong() {
        System.out.println("Enter song's id: ");
        int id = Integer.parseInt(sc.nextLine());
        if (songController.findSongById(id) == null) {
            System.out.println("Id not found");
            return;
        }
        System.out.println("Enter song's name: ");
        String name = sc.nextLine();
        songController.saveSong(new Song(id, name));
    }

    private void createSong() {
        System.out.println("Enter song name:");
        String songName = sc.nextLine();
        new SingerView().showListSinger();
        System.out.println("Enter singer ids to add, split with space");
        String idString = sc.nextLine();
        String[] idArray = idString.split("\\D+");
        int[] ids = Arrays.stream(idArray).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(ids));
        int lastId = songController.getSongs().stream().max(Comparator.comparing(Song::getId)).get().getId();
        Song newSong = new Song(lastId + 1, songName);
        addToList(newSong, ids);
        songController.saveSong(newSong);
    }

    private void showListSong() {
        for (Song song : songController.getSongs()) {
            System.out.println(song.getId() + ". " + song.getName());
        }
        System.out.println("Enter id to show details");
        int id = Integer.parseInt(sc.nextLine());
        this.formViewDetailsSong(id);
    }

    private void formViewDetailsSong(int id) {
        Song song = songController.findSongById(id);
        song.setListen(song.getListen() + 1);
        showSongDetails(song);
        System.out.println("Enter 1 to like this song or else to exit");
        int choose = Integer.parseInt(sc.nextLine());
        if (choose == 1) {
            song.setLike(song.getLike() + 1);
        }
        showSongDetails(song);

    }

    private void showSongDetails(Song song) {
        System.out.println(song.getName());
        System.out.println("\tPerformers:");
        System.out.print("\t\t");
        for (Singer singer : song.getSingerList()) {
            System.out.print(singer.getName() + " ");
        }
        System.out.println();
        System.out.println("\tPlayed:" + song.getListen());
        System.out.println("\tLiked:" + song.getLike());
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
