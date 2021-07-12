package com.mta.jwt.demo.dao.impl;

import com.mta.jwt.demo.dao.UserDAO;
import com.mta.jwt.demo.mapper.UserMapper;
import com.mta.jwt.demo.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @Autowired(required = true)
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public UserEntity findUserAccount(String username) throws SQLException {
        String sql = "select * from user where USERNAME = ?";
        return this.jdbcTemplate.queryForObject(sql, new UserMapper(), username);
    }

    @Override
    @Transactional
    public List<UserEntity> getAll() throws SQLException {
        String sql = "select * from user";
        List<UserEntity> lst = this.jdbcTemplate.query(sql, new UserMapper());
        return lst;
    }
}
