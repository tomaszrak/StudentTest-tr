package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.*;
import com.prz.testing.repository.QuestionRepository;
import com.prz.testing.repository.TestRepository;
import com.prz.testing.repository.UserGroupRepository;
import com.prz.testing.repository.UserRepository;
import com.prz.testing.service.AnswerService;
import com.prz.testing.service.QuestionService;
import com.prz.testing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserData userData;

    @Autowired
    private UserGroupRepository userGroupRepository;


    public List<Test> getTestsByUser(Long id) throws SQLException {
        return testRepository.getTestsByUser(id);
    }

    public void saveTest(TestCriteria criteria) throws SQLException {
        Test test = criteria.getTest();
        test.setUser(userRepository.getById(userData.getId()));
        testRepository.saveTest(criteria.getTest());
    }

    public Test getTest(Long userId) throws SQLException {
        UserGroup userGroup = userGroupRepository.getUserGroupByUser(userId);

        Test test = userGroup.getTests().iterator().next();

        return test;
    }

    public void solveTest(List<QuestionAnswer> answers) throws SQLException {
        for (QuestionAnswer answer : answers) {
            answer.setCreateDate(new Date());
            answerService.saveAnswer(answer);
        }

        createSummary(answers);
    }

    public void createSummary(List<QuestionAnswer> answers) throws SQLException {
        List<Long> ids = new ArrayList<Long>();
        List<Question> questions = new ArrayList<Question>();

        int correctAnswersNumber = 0;

        for (QuestionAnswer answer : answers) {
            Question question = answer.getQuestion();
            ids.add(question.getId());
            questions.add(question);
        }


        List<CorrectAnswer> correctAnswers = getCorrectAnswersForQuestions(ids);

        for (QuestionAnswer answer : answers) {
            for(CorrectAnswer cAnswer : correctAnswers){
                if (answer.getAnswer() == cAnswer.getAnswer()){
                    ++correctAnswersNumber;
                    break;
                } else{
                    continue;
                }
            }
        }
    }

    public List<CorrectAnswer> getCorrectAnswersForQuestions(List<Long> ids) throws SQLException {
        return questionRepository.getCorrectAnswersForQuestions(ids);
    }


}
