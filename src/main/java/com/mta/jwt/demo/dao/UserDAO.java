package com.mta.jwt.demo.dao;


import java.sql.SQLException;

public interface UserDAO {
    public UserEntity findUserAccount(String usename) throws SQLException;
}
