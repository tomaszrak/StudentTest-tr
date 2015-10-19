package com.prz.testing.service.impl;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Question;
import com.prz.testing.domain.User;
import com.prz.testing.repository.QuestionRepository;
import com.prz.testing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 15.10.2015.
 */
@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getWithCriteriaPaginatedQuestions(Criteria criteria) throws SQLException {
        return questionRepository.getWithCriteriaPaginated(criteria);
    }

    public void addQuestint(Question question) throws SQLException {
        question.setCreator(new User());
        questionRepository.save(question);
    }

    public Integer countWithCriteriaQuestions(Criteria criteria) throws SQLException {
        return questionRepository.countWithCriteria(criteria);
    }
}
