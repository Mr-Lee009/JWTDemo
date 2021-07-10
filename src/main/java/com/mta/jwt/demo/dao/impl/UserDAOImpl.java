package com.mta.jwt.demo.dao.impl;

import com.mta.jwt.demo.dao.UserDAO;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserDAOImpl implements UserDAO {

    @Override
    public UserEntity findUserAccount(String usename) throws SQLException {
        return null;
    }
}
