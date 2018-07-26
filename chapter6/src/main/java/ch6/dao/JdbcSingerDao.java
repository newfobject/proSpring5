package ch6.dao;

import ch6.InsertSinger;
import ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao2, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(JdbcSingerDao.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private InsertSinger insertSinger;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertSinger = new InsertSinger(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        if (jdbcTemplate == null) {
            throw new BeanCreationException(
                    "Must set jdbcTemplate on SingerDao");
        }
    }


    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject(
                "select first_name || ' ' || last_name from singer where id = ?",
                new Object[]{id}, String.class);
    }

    @Override
    public void insert(Singer singer) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("first_name", singer.getFistName());
        paramMap.put("last_name", singer.getLastName());
        paramMap.put("birth_Date", singer.getBirthDate());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertSinger.updateByNamedParam(paramMap, keyHolder);
        singer.setId(keyHolder.getKey().longValue());
        logger.info("New singer inserted with id: " + singer.getId());
    }
}
