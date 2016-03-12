package com.prz.testing.repository.impl;

import com.prz.testing.domain.Test;
import com.prz.testing.repository.TestRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
@Repository
@Transactional
public class TestRepositoryImpl extends AbstractRepositoryImpl<Test> implements TestRepository{

    TestRepositoryImpl() {
        super(Test.class);
    }

    public List<Test> getTestsByUser(Long id) throws SQLException{
        List<Test> tests = getCurrentSession().createCriteria(Test.class).createAlias("user", "u")
                .add(Restrictions.eq("u.id", id)).list();
        return tests;

    }

    public void saveTest(Test test) throws SQLException{
        getCurrentSession().save(test);
    }
}
