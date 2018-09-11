package ch13;

import ch13.entities.Singer;
import ch13.services.SingerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SingerControllerTest {
    private final List<Singer> singers = new ArrayList<>();

    @Before
    public void initSingers() {
        Singer singer = new Singer();
        singer.setId(1L);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(new GregorianCalendar(1940, 1, 1).getTime());
        singers.add(singer);
    }

    @Test
    public void testList() {
        SingerService singerService = mock(SingerService.class);
        when(singerService.findAll()).thenReturn(singers);

        SingerController singerController = new SingerController();

        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("singers", singerController.listData());

        Singers modelSingers = (Singers) uiModel.get("singers");
        assertEquals(1, modelSingers.getSingers().size());
    }

    @Test
    public void testCreate() {
        Singer newSinger = new Singer();
        newSinger.setId(999L);
        newSinger.setVersion(0);
        newSinger.setFirstName("BB");
        newSinger.setLastName("King");
        newSinger.setBirthDate(new GregorianCalendar(1940, 1, 1).getTime());

        SingerService singerService = mock(SingerService.class);
        when(singerService.save(newSinger)).thenAnswer((Answer<Singer>) invocation -> {
            singers.add(newSinger);
            return newSinger;
        });

        SingerController singerController = new SingerController();
        ReflectionTestUtils.setField(singerController, "singerService", singerService);

        Singer singer = singerController.create(newSinger);
        assertEquals(Long.valueOf(999L), singer.getId());
        assertEquals(0, singer.getVersion());
        assertEquals("BB", singer.getFirstName());
        assertEquals("King", singer.getLastName());

        assertEquals(2, singers.size());
    }
}
