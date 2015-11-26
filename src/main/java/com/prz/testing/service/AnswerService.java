package com.prz.testing.service;

import com.prz.testing.domain.Answer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
public interface AnswerService {

    public List<Answer> getAllAnswersForQuestion(Long questionId) throws SQLException;

    void saveAnswer(Answer answer) throws SQLException;
}
