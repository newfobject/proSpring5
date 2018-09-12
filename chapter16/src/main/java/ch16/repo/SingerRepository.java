package ch16.repo;

import ch16.entities.Singer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SingerRepository extends PagingAndSortingRepository<Singer, Long> {
}
