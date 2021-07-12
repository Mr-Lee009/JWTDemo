package com.mta.jwt.demo.dao;


import com.mta.jwt.demo.model.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public UserEntity findUserAccount(String username) throws SQLException;

    public List<UserEntity> getAll() throws SQLException;
}
