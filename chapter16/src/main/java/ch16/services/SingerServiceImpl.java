package ch16.services;

import ch16.entities.Singer;
import ch16.repo.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("singerService")
public class SingerServiceImpl implements SingerService {
    private SingerRepository singerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        List<Singer> singers = new ArrayList<>();
        singerRepository.findAll().forEach(singers::add);
        return singers;
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        return singerRepository.findById(id).get();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Singer> findAllByPage(Pageable pageable) {
        return singerRepository.findAll(pageable);
    }

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }
}
