package com.prz.testing.service;

import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.Test;
import com.prz.testing.dto.TestQA;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
public interface TestService {
    List<Test> getTestsByUser(Long id) throws SQLException;

    void saveTest(TestCriteria criteria) throws SQLException;

    TestQA getTest(Long id) throws SQLException;
}
