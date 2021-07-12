package com.mta.jwt.demo.mapper;

import com.mta.jwt.demo.model.RoleEntity;
import com.mta.jwt.demo.model.RoleEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleMapper implements RowMapper<RoleEntity> {

    @Override
    public RoleEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        RoleEntity u = new RoleEntity();
        u.setId(resultSet.getInt("ID"));
        u.setName(resultSet.getString("NAME"));
        return u;
    }
}
