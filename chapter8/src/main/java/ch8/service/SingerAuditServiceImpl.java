package ch8.service;

import ch8.SingerAuditRepository;
import ch8.entities.SingerAudit;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    public SingerAudit findById(Long id) {
        return singerAuditRepository.findOne(id);
    }

    @Override
    public SingerAudit save(SingerAudit singerAudit) {
        return singerAuditRepository.save(singerAudit);
    }
}
