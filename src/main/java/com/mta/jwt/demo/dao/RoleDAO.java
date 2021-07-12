package com.mta.jwt.demo.dao;

import com.mta.jwt.demo.model.RoleEntity;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    public List<RoleEntity> findRule(long ID) throws SQLException;
}
