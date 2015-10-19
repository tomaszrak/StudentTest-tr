package com.prz.testing.service;

import com.prz.testing.domain.Role;
import com.prz.testing.enumerate.RoleName;
import org.hibernate.Criteria;

import java.sql.SQLException;

/**
 * Created by Roman on 16.09.2015.
 */
public interface RoleService {
    
    void saveRole(Role role) throws SQLException;

    Role getRole(RoleName roleName) throws SQLException;
}
