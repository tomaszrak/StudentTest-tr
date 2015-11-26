package com.prz.testing.service.impl;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Question;
import com.prz.testing.domain.User;
import com.prz.testing.repository.QuestionRepository;
import com.prz.testing.repository.UserRepository;
import com.prz.testing.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

    public List<Question> getWithCriteriaPaginatedQuestions(Criteria criteria) throws SQLException {
        if(null == criteria.getOrderParam() || null == criteria.getOrderWay()){
            criteria.setOrderWay("ASC");
            criteria.setOrderParam("name");
        }
        return questionRepository.getWithCriteriaPaginated(criteria);
    }

    public List<Question> getAll() throws SQLException{
        return questionRepository.getAllQuestions();
    }

    public void addQuestint(Question question) throws SQLException {
        question.setCreator(userRepository.getByIndexNumber(new Long("123456")));
        question.setCreateDate(new Date());
        questionRepository.save(question);
    }

    public Integer countWithCriteriaQuestions(Criteria criteria) throws SQLException {
        return questionRepository.countWithCriteria(criteria);
    }
}
