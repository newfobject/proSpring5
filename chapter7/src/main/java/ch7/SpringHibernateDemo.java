package ch7;

import ch7.dao.SingerDao;
import ch7.entities.Album;
import ch7.entities.Instrument;
import ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {

    private static Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);

    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SingerDao singerDao = ctx.getBean(SingerDao.class);
        Singer singer = singerDao.findById(1L);
        logger.info(singer.toString());
        singerDao.delete(singer);
        listSingersWithAlbums(singerDao.findAllWithAlbum());

        ctx.close();
    }

    private static void listSingers(List<Singer> all) {
        logger.info(" --- Listing singers:");
        for (Singer singer : all) {
            logger.info(singer.toString());
        }
    }

    private static void listSingersWithAlbums(List<Singer> singers) {
        logger.info("---- Listing singers with instruments:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
            if (singer.getAlbums() != null) {
                for (Album album : singer.getAlbums()) {
                    logger.info("\t" + album.toString());
                }
            }
            if (singer.getInstruments() != null) {
                for (Instrument instrument : singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                }
            }
        }
    }
}
