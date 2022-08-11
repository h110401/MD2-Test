package model;

import java.util.Objects;

public class Singer implements Comparable<Singer> {
    private int id;
    private String name;
    private int age;

    public Singer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Singer o) {
        if (!this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        } else {
            return this.age - o.age;
        }
    }
}


