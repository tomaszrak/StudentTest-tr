package com.prz.testing.service;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 12.09.2015.
 */
public interface UserService {

    List<User> getAllUsers() throws SQLException;

    User getUserById(Long id) throws SQLException;

    void saveOrUpdateUser(User user) throws SQLException;

    void blockUser(User user) throws SQLException;

    void activateUser(User user) throws SQLException;

    List<User> getAllUsersPaginated(Criteria criteria) throws SQLException;

    List<User> getUsersWithCriteriaPaginated(Criteria criteria);

    Integer countUsersWithCriteria(Criteria criteria);

    void updateUser(User user) throws SQLException;

    User getUserByIndex(Integer indexNumber) throws SQLException;

    void deleteUser(Long id) throws SQLException;

    void resetPassword(User user) throws SQLException;
}
