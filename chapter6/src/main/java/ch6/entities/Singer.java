package ch6.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Singer implements Serializable {
    private Long id;
    private String fistName;
    private String lastName;
    private Date birthDate;
    private List<Album> albums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean addAlbum(Album album) {
        if (albums == null) {
            albums = new ArrayList<>();
            albums.add(album);
            return true;
        } else {
            if (albums.contains(album)) {
                return false;
            }
        }
        albums.add(album);
        return true;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", albums=" + albums +
                '}';
    }
}
