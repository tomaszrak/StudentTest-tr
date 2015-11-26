package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.domain.Answer;
import com.prz.testing.repository.AnswerRepository;
import com.prz.testing.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private UserData userData;

    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswersForQuestion(Long questionId) throws SQLException {
        return null;
    }

    public void saveAnswer(Answer answer) throws SQLException {
        answer.setCreateDate(new Date());
        answer.setCreator(userData);
        answerRepository.save(answer);
    }
}
