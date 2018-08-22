package ch8;

import ch8.config.EnversConfig;
import ch8.entities.SingerAudit;
import ch8.service.SingerAuditService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SpringEnversJPADemo {
    public static void main(String[] args) {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(EnversConfig.class);

        SingerAuditService auditService = context.getBean(SingerAuditService.class);

        System.out.println("Add new Singer");
        SingerAudit singerAudit = new SingerAudit();
        singerAudit.setFirstName("BB");
        singerAudit.setLastName("King");
        singerAudit.setBirthDate(new Date(new GregorianCalendar(1940, 8, 16).getTime().getTime())
        );
        auditService.save(singerAudit);
        listSingers(auditService.findAll());

        System.out.println("Update singer");
        singerAudit.setFirstName("Riley B.");
        auditService.save(singerAudit);
        listSingers(auditService.findAll());
        SingerAudit oldSInger = auditService.findAuditByRevision(1L, 1);
        System.out.println("");
        System.out.println("Old singer with id 1 and rev 1: " + oldSInger);
        System.out.println("");
        oldSInger = auditService.findAuditByRevision(1L, 2);
        System.out.println("");
        System.out.println("Old singer with id 1 and rev 2; " + oldSInger);
        System.out.println();

        context.close();
    }

    private static void listSingers(List<SingerAudit> singers) {
        System.out.println("start listing");
        for (SingerAudit singer : singers) {
            System.out.println(singer.toString());
        }
    }
}
