package ch8.service;

import ch8.entities.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);

    List<Singer> findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);

    List<Singer> findAllByNativeQuery();

}
