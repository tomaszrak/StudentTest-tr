package com.prz.testing.repository;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.User;
import com.prz.testing.enumerate.RoleName;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 10.09.2015.
 */

public interface UserRepository extends AbstractRepository<User>{

    void test();

    List<User> getAllUsers() throws SQLException;

    User getByCredentials(String lastName, String firstName) throws SQLException;

    List<User> getByRoleName(RoleName roleNames) throws SQLException;
}
