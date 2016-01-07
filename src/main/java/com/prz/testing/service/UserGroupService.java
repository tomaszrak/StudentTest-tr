package com.prz.testing.service;

import com.prz.testing.domain.UserGroup;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 20.12.2015.
 */
public interface UserGroupService {

    List<UserGroup> getAllGroups() throws SQLException;

    void saveGroup(UserGroup group) throws SQLException;

    void updateGroup(UserGroup group) throws SQLException;

    void removeGroup(UserGroup group) throws SQLException;
}
