package ch9.services;

import ch9.entities.Singer;
import ch9.repos.SingerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerService")
@Repository
public class SingerServiceImpl implements SingerService {
    private SingerRepository singerRepository;
    private TransactionTemplate transactionTemplate;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Autowired(required = false)
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
//    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
//    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
//    @Transactional(propagation = Propagation.NEVER)
    public long countAll() {
        if (transactionTemplate == null) {
            return singerRepository.countAllSingers();
        }

        return transactionTemplate.execute(
                status -> em.createNamedQuery(Singer.COUNT_ALL, Long.class).getSingleResult());
    }
}
