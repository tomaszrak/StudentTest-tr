package com.prz.testing.repository;

import com.prz.testing.domain.Role;
import com.prz.testing.enumerate.RoleName;
import org.hibernate.Criteria;

import java.sql.SQLException;

/**
 * Created by Roman on 16.09.2015.
 */
public interface RoleRepository extends AbstractRepository<Role>{
    Role getByName(RoleName name);


    void saveRole(Role role) throws SQLException;
}
