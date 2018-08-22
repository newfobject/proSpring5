package ch8;

import ch8.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {

}
