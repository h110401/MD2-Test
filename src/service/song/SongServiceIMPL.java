package service.song;

import model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongServiceIMPL implements ISongService {

    private static final List<Song> songs = new ArrayList<>();

    static {
        songs.add(new Song(1, "One"));
        songs.add(new Song(2, "Two"));
        songs.add(new Song(3, "Three"));
        songs.add(new Song(4, "Four"));
        for (Song song : songs) {
            song.setListen((int) (Math.random() * 1000 + 1));
            song.setLike((int) (Math.random() * song.getListen() + 1));
        }
    }


    @Override
    public List<Song> findAll() {
        return songs;
    }

    @Override
    public void save(Song song) {
        if (this.findById(song.getId()) == null) {
            songs.add(song);
        } else {
            this.findById(song.getId()).setName(song.getName());
        }
    }

    @Override
    public void deleteById(int id) {
        songs.remove(this.findById(id));
    }

    @Override
    public Song findById(int id) {
        return songs.stream().filter(s -> s.getId() == id).findAny().orElse(null);
    }

    @Override
    public void sort() {

    }
}
