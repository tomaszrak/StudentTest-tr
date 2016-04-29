package com.prz.testing.repository.impl;

import com.prz.testing.domain.Summary;
import com.prz.testing.domain.User;
import com.prz.testing.repository.SummaryRepository;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 17.01.2016.
 */
@Repository
@Transactional
public class SummaryRepositoryImpl extends AbstractRepositoryImpl<Summary> implements SummaryRepository {

    SummaryRepositoryImpl() {
        super(Summary.class);
    }

    public List<Summary> getByUsers(List<User> students) throws SQLException {
        List<Summary> list = getCurrentSession()
                .createCriteria(Summary.class).add(Restrictions.in("student", students)).list();
        return  list;
    }

    public List<Summary> getByUser(Long userId) throws SQLException{
        List<Summary> list = getCurrentSession().createCriteria(Summary.class)
                .add(Restrictions.eq("student.id", userId)).list();

        return list;
    }
}
