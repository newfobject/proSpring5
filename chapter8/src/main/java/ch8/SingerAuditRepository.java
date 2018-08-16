package ch8;

import ch8.entities.SingerAudit;

import java.util.List;

public interface SingerAuditRepository {
    List<SingerAudit> findAll();

    SingerAudit findOne(Long id);

    SingerAudit save(SingerAudit singerAudit);
}
