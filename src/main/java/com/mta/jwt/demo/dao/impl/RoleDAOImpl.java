package com.mta.jwt.demo.dao.impl;

import com.mta.jwt.demo.dao.RoleDAO;
import com.mta.jwt.demo.mapper.RoleMapper;
import com.mta.jwt.demo.model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDAOImpl implements RoleDAO {

    @Autowired(required = true)
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<RoleEntity> findRule(long ID) throws SQLException {
        String sql = "select * from role where ID in (select ID_RULE from role_user where ID_USER = ?)";
        return this.jdbcTemplate.query(sql, new RoleMapper(), ID);
    }
}
