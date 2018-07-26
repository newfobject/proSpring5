package ch6;

import ch6.dao.SingerDao2;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JdbcCfgTest {

    @Test
    public void testH2DB() {
        GenericXmlApplicationContext ctx =
                new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-h2-cfg.xml");
        ctx.refresh();
        testDao(ctx.getBean(SingerDao2.class));
        ctx.close();
    }

    private void testDao(SingerDao2 bean) {
        assertNotNull(bean);
        String singerName = bean.findNameById(1L);
        assertEquals("John Mayer", singerName);
    }

}
