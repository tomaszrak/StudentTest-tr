package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.domain.UserGroup;
import com.prz.testing.repository.UserGroupRepository;
import com.prz.testing.repository.UserRepository;
import com.prz.testing.service.UserGroupService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by ROLO on 20.12.2015.
 */
@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService{

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserData userData;

    private Logger logger =  Logger.getLogger(UserGroupServiceImpl.class);

    public List<UserGroup> getAllGroups() throws SQLException {
        return userGroupRepository.getAllGroups();
    }

    public void saveGroup(UserGroup group) throws SQLException {
        group.setCreator(userRepository.getByIndexNumber(userData.getIndexNumber()));
        group.setCreateDate(new Date());
        userGroupRepository.save(group);
    }

    public void updateGroup(UserGroup group) throws SQLException {
        userGroupRepository.update(group);
    }

    public void removeGroup(UserGroup group) throws SQLException {
        userGroupRepository.delete(group);
    }
}
