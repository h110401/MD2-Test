package service;

import java.util.List;

public interface IGeneric<T> {
    List<T> findAll();

    void save(T e);

    void deleteById(int id);

    T findById(int id);

    void sort();
}
