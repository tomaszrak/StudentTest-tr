package com.prz.testing.repository.impl;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Role;
import com.prz.testing.domain.User;
import com.prz.testing.enumerate.RoleName;
import com.prz.testing.repository.UserRepository;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 10.09.2015.
 */
@Repository
@Transactional
public class UserRepositoryImpl extends AbstractRepositoryImpl<User> implements UserRepository {

    UserRepositoryImpl() {
        super(User.class);
    }


    public List<User> getAllUsers() throws SQLException {
        List<User> users = getCurrentSession().createCriteria(User.class).list();
        return users;
    }

    public User getByCredentials(String lastName, String firstName) throws SQLException {
        User user = new User();
        user = (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("lastName", lastName))
                .add(Restrictions.eq("firstName", firstName)).uniqueResult();
        return user;
    }

    public List<User> getByRoleName(RoleName roleName) throws SQLException {
        List<User> users = getCurrentSession().createCriteria(User.class)
               .createAlias("role", "role").add(Restrictions.eq("role.roleName", roleName)).list();
        return users;
    }

    public User getByIndexNumber(Integer indexNumber) throws SQLException {
        User user = (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("indexNumber", indexNumber)).uniqueResult();

        return user;
    }

    public void updateUserPassword(Long userId, String password) throws SQLException {
        getCurrentSession().createQuery("UPDATE USERS set password = :password where id = :id")
                .setParameter("id", userId).setParameter("password", password).executeUpdate();
    }
}
