package ch9;

import ch9.config.DataJPAConfig;
import ch9.config.ServicesConfigProgrammatic;
import ch9.services.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String[] args) {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(ServicesConfigProgrammatic.class, DataJPAConfig.class);
        SingerService singerService = context.getBean(SingerService.class);
        System.out.println("Singer count: " + singerService.countAll());

        context.close();
    }
}
