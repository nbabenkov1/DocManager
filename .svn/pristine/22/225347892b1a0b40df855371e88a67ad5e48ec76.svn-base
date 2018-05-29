package DAO;

import models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by N.Babenkov on 10.05.2018.
 **/
@Component
public class ActreaderDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ActreaderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Document> selectAllDocuments(){
        return jdbcTemplate.query("SELECT M_NUM, RES, DOG FROM ACT_LIST", new BeanPropertyRowMapper<>(Document.class));
    }
}
