package com.prz.testing.repository.impl;

import com.prz.testing.domain.User;
import com.prz.testing.domain.UserGroup;
import com.prz.testing.repository.UserGroupRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 20.12.2015.
 */
@Repository
@Transactional
public class UserGroupRepositoryImpl extends AbstractRepositoryImpl<UserGroup> implements UserGroupRepository {

    UserGroupRepositoryImpl() {
        super(UserGroup.class);
    }

    public List<UserGroup> getAllGroups() throws SQLException {
        List<UserGroup> groups = getCurrentSession().createCriteria(UserGroup.class)
                .createAlias("creator", "user", JoinType.LEFT_OUTER_JOIN)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return groups;
    }

    public UserGroup getUserGroupByUser(Long userId) throws SQLException {
        UserGroup userGroup = (UserGroup) getCurrentSession().createCriteria(UserGroup.class)
                .createAlias("users", "users").add(Restrictions.eq("users.id", userId)).list().get(0);

        return userGroup;
    }

    public List<User> getGroupUsers(Long id) throws SQLException{
        List<User> users = getCurrentSession().createCriteria(UserGroup.class).add(Restrictions.eq("id", id))
                .setProjection(Projections.property("users")).list();
        return users;
    }
}
