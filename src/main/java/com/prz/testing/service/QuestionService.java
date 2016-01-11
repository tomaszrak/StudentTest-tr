package com.prz.testing.service;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.CorrectAnswer;
import com.prz.testing.domain.Question;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 15.10.2015.
 */
public interface QuestionService {

    List<Question> getWithCriteriaPaginatedQuestions(Criteria criteria) throws SQLException;

    void addQuestion(Question question) throws SQLException;

    Integer countWithCriteriaQuestions(Criteria criteria) throws SQLException;

    Set<Question> getAll() throws SQLException;

    Question getById(Long questionId) throws SQLException;

    void setCorrectAnswer(List<CorrectAnswer> answers) throws SQLException;

}
