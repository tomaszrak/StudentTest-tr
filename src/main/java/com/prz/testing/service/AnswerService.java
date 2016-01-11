package com.prz.testing.service;

import com.prz.testing.criteria.AnswerCriteria;
import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import com.prz.testing.domain.QuestionAnswer;
import com.prz.testing.dto.QuestionCAnswer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
public interface AnswerService {

    public List<Answer> getAllAnswersForQuestion(Long questionId) throws SQLException;

    void saveAnswer(Answer answer) throws SQLException;

    void saveAnswerWithQuestion(Answer answer) throws SQLException;

    QuestionCAnswer saveAnswersQuestion(AnswerCriteria criteria) throws SQLException;

    void saveAnswer(QuestionAnswer answer) throws SQLException;
}
