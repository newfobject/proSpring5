package ch8.service;

import ch8.config.JpaConfig;
import ch8.entities.Singer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SingerServiceImplTest {

    private static Logger logger =
            LoggerFactory.getLogger(SingerServiceImplTest.class);
    private GenericApplicationContext ctx;
    private SingerService service;

    @Before
    public void setUp() {
        ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
        service = ctx.getBean(SingerService.class);
        assertNotNull(service);
    }

    @After
    public void tearDown() {
        ctx.close();
    }

    @Test
    public void findAll() {
        List<Singer> singers = service.findAll();
        assertEquals(3, singers.size());
        listSingers(singers);
    }

    private void listSingers(List<Singer> singers) {
        logger.info("--- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    @Test
    public void findAllWithAlbum() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAllByNativeQuery() {
    }
}