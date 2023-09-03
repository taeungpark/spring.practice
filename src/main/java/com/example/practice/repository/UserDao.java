package com.example.practice.repository;

import com.example.practice.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {
//    private final JdbcTemplate jdbcTemplate; // final need to be initialized at constructor
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsertOperations insertAction; // interface that help insert easy

    // Spring boot will inject, when input the parameter on constructor.
    public UserDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("user");// table name to insert
    }

    public boolean addUser(User user){
//        String sql = "INSERT INTO user(user_id, name) VALUES(?, ?)";
//        int result = jdbcTemplate.update(sql, user.getUserid(), user.getName());
//        return result == 1;

        // user have the property of roleId, name
        // SimpleJdbcInsert will create sql (INSERT INTO user(user_id, name) VALUES(:userId, :name)
        // property name in user class and column name should be fit
        SqlParameterSource params = new BeanPropertySqlParameterSource(user); // user object field name should be same with table name.
        int result = insertAction.execute(params);
        return result == 1;
    }

    public boolean deleteUser(int userId){
        String sql = "DELETE FROM user WHERE user_id = :userId";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        int result = jdbcTemplate.update(sql, params);
        return result == 1;
    }

    public User getUser (int userId){
        String sql = "SELECT * FROM user WHERE user_id = :userId";
        SqlParameterSource params = new MapSqlParameterSource("userId", userId);
        RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.queryForObject(sql, params, userRowMapper);
    }

    public List<User> getUsers(){
        String sql = "SELECT * FROM user";
        RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);
        return jdbcTemplate.query(sql, userRowMapper);
        }
}
