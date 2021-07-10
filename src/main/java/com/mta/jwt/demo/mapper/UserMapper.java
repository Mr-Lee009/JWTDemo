package com.mta.jwt.demo.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        UserEntity u = new UserEntity();
        u.setId(resultSet.getInt("ID"));
        u.setUsername(resultSet.getString("USERNAME"));
        u.setPassword(resultSet.getString("PASSWORD"));
        return u;
    }
}
