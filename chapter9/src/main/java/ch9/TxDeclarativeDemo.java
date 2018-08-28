package ch9;

import ch9.entities.Album;
import ch9.entities.Singer;
import ch9.services.SingerService;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

public class TxDeclarativeDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/aop-transaction.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);

        singerService.save(initializeSinger());

        List<Singer> singers = singerService.findAll();
        singers.forEach(System.out::println);

        Singer singer = singerService.findById(1L);
        singer.setFirstName("John Clayton");
        singerService.save(singer);
        System.out.println("Singer save successfully: " + singer);

        System.out.println("Singer count: " + singerService.countAll());

        ctx.close();
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
