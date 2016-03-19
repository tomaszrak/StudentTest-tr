package com.prz.testing.repository.impl;

import com.prz.testing.domain.Test;
import com.prz.testing.repository.TestRepository;
import com.prz.testing.util.LogUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
@Repository
@Transactional
public class TestRepositoryImpl extends AbstractRepositoryImpl<Test> implements TestRepository {

    TestRepositoryImpl() {
        super(Test.class);
    }

    @Autowired
    private LogUtil log;

    public List<Test> getTestsByUser(Long id) throws SQLException {

        List<Test> tests = getCurrentSession().createCriteria(Test.class)
                .add(Restrictions.eq("user.id", id))
                .list();
        return tests;

    }

    public void saveTest(Test test) throws SQLException {
        getCurrentSession().save(test);
    }
}
