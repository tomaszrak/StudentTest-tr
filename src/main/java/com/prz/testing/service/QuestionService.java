package com.prz.testing.service;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Question;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 15.10.2015.
 */
public interface QuestionService {

    List<Question> getWithCriteriaPaginatedQuestions(Criteria criteria) throws SQLException;

    void addQuestint(Question question) throws SQLException;

    Integer countWithCriteriaQuestions(Criteria criteria) throws SQLException;

}
