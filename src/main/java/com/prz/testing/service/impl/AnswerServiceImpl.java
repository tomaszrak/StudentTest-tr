package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.criteria.AnswerCriteria;
import com.prz.testing.domain.*;
import com.prz.testing.dto.QuestionCAnswer;
import com.prz.testing.repository.AnswerRepository;
import com.prz.testing.repository.QuestionRepository;
import com.prz.testing.repository.UserRepository;
import com.prz.testing.service.AnswerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by ROLO on 23.11.2015.
 */
@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private UserData userData;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    private Logger logger = Logger.getLogger(AnswerServiceImpl.class);

    public List<Answer> getAllAnswersForQuestion(Long questionId) throws SQLException {
        return answerRepository.getAnswersByQuestion(questionId);
    }

    public void saveAnswer(Answer answer) throws SQLException {
        answer.setCreateDate(new Date());
        answer.setCreator(userRepository.getByIndexNumber(userData.getIndexNumber()));
        answerRepository.save(answer);
    }

    public void saveAnswerWithQuestion(Answer answer) throws SQLException {
        answer.setCreateDate(new Date());
        answer.setCreator(userRepository.getByIndexNumber(userData.getIndexNumber()));
    }

    public QuestionCAnswer saveAnswersQuestion(AnswerCriteria criteria) throws SQLException {

        User creator = userRepository.getByIndexNumber(userData.getIndexNumber());

        Set<Answer> answers = criteria.getAnswers();
        for (Answer answer : answers) {
            answer.setCreateDate(new Date());
            answer.setCreator(creator);
//            answer.setQuestion(question);
            answerRepository.save(answer);
        }
        Question question = criteria.getQuestion();
        question.setCreateDate(new Date());
        question.setCreator(creator);
        question.setAnswers(answers);
        questionRepository.save(question);

        List<CorrectAnswer> cAnswers = questionRepository.getCAnswerByQuestion(question.getId());

        return new QuestionCAnswer(question, cAnswers);
    }

    public void saveAnswer(QuestionAnswer answer) throws SQLException {
        answerRepository.saveAnswer(answer);
    }
}
