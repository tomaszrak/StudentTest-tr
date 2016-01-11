package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.CorrectAnswer;
import com.prz.testing.domain.Question;
import com.prz.testing.domain.User;
import com.prz.testing.enumerate.QuestionType;
import com.prz.testing.repository.QuestionRepository;
import com.prz.testing.repository.UserRepository;
import com.prz.testing.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 15.10.2015.
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserData userData;

    private Logger logger = Logger.getLogger(QuestionServiceImpl.class);

    public List<Question> getWithCriteriaPaginatedQuestions(Criteria criteria) throws SQLException {
        if(null == criteria.getOrderParam() || null == criteria.getOrderWay()){
            criteria.setOrderWay("ASC");
            criteria.setOrderParam("name");
        }
        return questionRepository.getWithCriteriaPaginated(criteria);
    }

    public Set<Question> getAll() throws SQLException{
        return questionRepository.getAllQuestions();
    }

    public Question getById(Long questionId) throws SQLException{
        return questionRepository.getById(questionId);
    }

    public void setCorrectAnswer(List<CorrectAnswer> answers) throws SQLException {
        Question question = answers.get(0).getQuestion();
        User user = userRepository.getById(userData.getId());
        if(question.getType().equals(QuestionType.ONE_ANSWER) || question.getType().equals(QuestionType.NUMBER)){
            CorrectAnswer answer = answers.get(0);
            answer.setCreateDate(new Date());
            answer.setCreator(user);
            questionRepository.setCorrectAnswer(answers.get(0));
        } else if(question.getType().equals(QuestionType.MULTIPLE)) {
            for (CorrectAnswer answer : answers) {
                answer.setCreator(user);
                answer.setCreateDate(new Date());
                questionRepository.setCorrectAnswer(answer);
            }
        }

    }

    public void addQuestion(Question question) throws SQLException {
        question.setCreator(userRepository.getByIndexNumber(userData.getIndexNumber()));
        question.setCreateDate(new Date());
        questionRepository.save(question);
    }

    public Integer countWithCriteriaQuestions(Criteria criteria) throws SQLException {
        return questionRepository.countWithCriteria(criteria);
    }
}
