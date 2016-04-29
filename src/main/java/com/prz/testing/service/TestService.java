package com.prz.testing.service;

import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.QuestionAnswer;
import com.prz.testing.dto.Summary;
import com.prz.testing.domain.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
public interface TestService {
    List<Test> getTestsByUser(Long id) throws SQLException;

    void saveTest(TestCriteria criteria) throws SQLException;

    Test getTest(Long id) throws SQLException;

    Summary solveTest(List<QuestionAnswer> answers) throws SQLException;
}
