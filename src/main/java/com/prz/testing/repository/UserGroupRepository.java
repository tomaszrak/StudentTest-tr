package com.prz.testing.repository;

import com.prz.testing.domain.UserGroup;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 20.12.2015.
 */
public interface UserGroupRepository extends AbstractRepository<UserGroup>{

    List<UserGroup> getAllGroups() throws SQLException;

    UserGroup getUserGroupByUser(Long userId) throws SQLException;
}
