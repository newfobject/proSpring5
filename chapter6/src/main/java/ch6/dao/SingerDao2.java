package ch6.dao;

import ch6.entities.Singer;

public interface SingerDao2 {
    String findNameById(Long id);

    void insert(Singer singer);
}
