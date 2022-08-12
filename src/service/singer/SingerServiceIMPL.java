package service.singer;

import model.Singer;
import model.Song;
import service.song.ISongService;
import service.song.SongServiceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SingerServiceIMPL implements ISingerService {

    private static final List<Singer> singers = new ArrayList<>();

    static {
        singers.add(new Singer(1, "John", 30));
        singers.add(new Singer(2, "Joe", 28));
        singers.add(new Singer(3, "Jay", 25));
        singers.add(new Singer(4, "Joe", 20));
        ISongService service = new SongServiceIMPL();
        service.findAll().get(0).getSingerList().add(singers.get(0));
        singers.get(0).getSongList().add(service.findAll().get(0));
        service.findAll().get(1).getSingerList().add(singers.get(1));
        singers.get(1).getSongList().add(service.findAll().get(1));
        service.findAll().get(2).getSingerList().add(singers.get(2));
        singers.get(2).getSongList().add(service.findAll().get(2));
        service.findAll().get(3).getSingerList().add(singers.get(3));
        singers.get(3).getSongList().add(service.findAll().get(3));

    }


    @Override
    public List<Singer> findAll() {
        return singers;
    }

    @Override
    public void save(Singer singer) {
        if (this.findById(singer.getId()) == null) {
            singers.add(singer);
        } else {
            this.findById(singer.getId()).setName(singer.getName());
            this.findById(singer.getId()).setAge(singer.getAge());
        }
    }

    @Override
    public void deleteById(int id) {
        singers.remove(this.findById(id));
    }

    @Override
    public Singer findById(int id) {
        return singers.stream().filter(s -> s.getId() == id).findAny().orElse(null);
    }

    public void sort() {
        Collections.sort(singers);
    }

}
