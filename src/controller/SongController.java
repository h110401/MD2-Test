package controller;

import model.Song;
import service.song.ISongService;
import service.song.SongServiceIMPL;

import java.util.List;

public class SongController {

    private final ISongService songService = new SongServiceIMPL();

    public List<Song> getSongs() {
        return songService.findAll();
    }

    public void saveSong(Song song) {
        songService.save(song);
    }

    public void deleteSong(int id) {
        songService.deleteById(id);
    }

    public Song findSongById(int id) {
        return songService.findById(id);
    }

    public void addToList(Song song, int[] ids) {

    }
}
