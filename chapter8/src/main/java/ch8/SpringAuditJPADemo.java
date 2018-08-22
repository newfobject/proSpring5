package ch8;

import ch8.config.AuditConfig;
import ch8.entities.SingerAudit;
import ch8.service.SingerAuditService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringAuditJPADemo {
    public static void main(String[] args) {
        GenericApplicationContext ctx =
                new AnnotationConfigApplicationContext(AuditConfig.class);
        SingerAuditService singerAuditService = ctx.getBean(SingerAuditService.class);

        List<SingerAudit> singers = singerAuditService.findAll();
        listSingers(singers);
        System.out.println("Add new singer");

        SingerAudit singer = new SingerAudit();
        singer.setFirstName("BB");
        singer.setLastName("King");
        singer.setBirthDate(new Date(
                new GregorianCalendar(1940, 8, 16).getTime().getTime()));
        singerAuditService.save(singer);

        singers = singerAuditService.findAll();
        listSingers(singers);

        singer = singerAuditService.findById(1L);
        System.out.println("");
        System.out.println("Singer with id 1: " + singer);
        System.out.println("");

        System.out.println("Update singer");
        singer.setFirstName("John Clayton");
        singerAuditService.save(singer);
        singers = singerAuditService.findAll();
        listSingers(singers);

        ctx.close();
    }

    private static void listSingers(List<SingerAudit> singers) {
        System.out.println("start listing");
        for (SingerAudit singer : singers) {
            System.out.println(singer.toString());
        }
    }
}
