package com.prz.testing.service.impl;

import com.prz.testing.domain.Role;
import com.prz.testing.enumerate.RoleName;
import com.prz.testing.repository.RoleRepository;
import com.prz.testing.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by Roman on 16.09.2015.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void saveRole(Role role) throws SQLException{
        roleRepository.save(role);
    }

    public Role getRole(RoleName roleName) throws SQLException{
        return roleRepository.getByName(RoleName.STUDENT);
    }
}
