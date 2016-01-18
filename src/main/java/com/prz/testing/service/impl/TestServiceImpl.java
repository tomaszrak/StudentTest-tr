package com.prz.testing.service.impl;

import com.prz.testing.controller.UserData;
import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.*;
import com.prz.testing.enumerate.QuestionType;
import com.prz.testing.repository.*;
import com.prz.testing.service.AnswerService;
import com.prz.testing.service.QuestionService;
import com.prz.testing.service.TestService;
import com.sun.javafx.binding.StringFormatter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;

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

    @Autowired
    private SummaryRepository summaryRepository;

    private Logger logger = Logger.getLogger(TestServiceImpl.class);

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

    public Summary solveTest(List<QuestionAnswer> answers) throws SQLException {
        for (QuestionAnswer answer : answers) {
            if (answer.getQuestion().getType().equals(QuestionType.NUMBER)
                    || answer.getQuestion().getType().equals(QuestionType.SHORT_ANSWER)) {
                answerService.saveAnswer(answer.getAnswer());
            }

            answer.setCreateDate(new Date());
            answerService.saveAnswer(answer);
        }

        return createSummary(answers);
    }

    public Summary createSummary(List<QuestionAnswer> answers) throws SQLException {
        Set<Long> ids = new HashSet<Long>();

        int correctAnswersNumber = 0;

        for (QuestionAnswer answer : answers) {
            Question question = answer.getQuestion();
            ids.add(question.getId());
        }


        List<CorrectAnswer> correctAnswers = getCorrectAnswersForQuestions(ids);

        for (QuestionAnswer answer : answers) {
            for (CorrectAnswer cAnswer : correctAnswers) {
                if (null != answer.getAnswer().getId()) {
                    logger.info(answer.getAnswer().getId() + " " + cAnswer.getAnswer().getId());
                    if (answer.getAnswer().getId() == cAnswer.getAnswer().getId()) {
                        ++correctAnswersNumber;
                        break;
                    }
                }
            }
        }
        User student = answers.get(0).getUser();

        Summary summary = new Summary();
        summary.setCreateDate(new Date());
        summary.setStudent(student);
        summary.setTest(answers.get(0).getTestId());
        summary.setDegree("" + 100 * correctAnswersNumber / ids.size());

        logger.info(String.format("Correct answers [%d] for userId = [%d]", correctAnswersNumber, student.getId()));

        summaryRepository.save(summary);

        return summary;
    }

    public List<CorrectAnswer> getCorrectAnswersForQuestions(Set<Long> ids) throws SQLException {
        return questionRepository.getCorrectAnswersForQuestions(ids);
    }


}
