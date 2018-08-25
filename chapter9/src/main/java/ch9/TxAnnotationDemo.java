package ch9;

import ch9.config.DataJPAConfig;
import ch9.config.ServicesConfig;
import ch9.entities.Album;
import ch9.entities.Singer;
import ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

public class TxAnnotationDemo {
    public static void main(String[] args) {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(ServicesConfig.class, DataJPAConfig.class);
        SingerService singerService = context.getBean(SingerService.class);

        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);

        singerService.save(initializeSinger());

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singer.setLastName("His last name");
        singerService.save(singer);

        singer = singerService.findById(1L);
        System.out.println("Singer version: " + singer.getVersion());

        System.out.println("Singer saved!");

        context.close();
    }

    private static Singer initializeSinger() {
        Singer singer = new Singer();
        singer.setFirstName("FirstName");
        singer.setLastName("LastName");
        singer.setBirthDate(new Date(new GregorianCalendar(1991, 1, 1).getTime().getTime()));

        HashSet<Album> albums = new HashSet<>();
        Album album = new Album();
        album.setTitle("Album 1");
        album.setReleaseDate(new Date(new GregorianCalendar(2000, 1, 1).getTime().getTime()));
        album.setSinger(singer);
        albums.add(album);
        singer.setAlbums(albums);
        return singer;
    }
}
