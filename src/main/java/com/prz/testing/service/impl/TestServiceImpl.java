package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import com.prz.testing.domain.Test;
import com.prz.testing.domain.UserGroup;
import com.prz.testing.dto.TestQA;
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
import java.util.List;
import java.util.Set;

/**
 * Created by ROLO on 07.12.2015.
 */
@Service
@Transactional
public class TestServiceImpl implements TestService{

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionService questionService;

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

    public TestQA getTest(Long userId) throws SQLException{
        UserGroup userGroup = userGroupRepository.getUserGroupByUser(userId);

        TestQA testQA = new TestQA();
        testQA.setTest(userGroup.getTests().iterator().next());
        Set<Question> questions = testQA.getTest().getQuestions();

        List<Answer> answers = new ArrayList<Answer>();

        for(Question question : questions){
            answers.addAll(answerService.getAllAnswersForQuestion(question.getId()));
        }

        testQA.setAnswers(answers);
        return testQA;
    }


}
