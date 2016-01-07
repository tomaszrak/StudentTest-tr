package com.prz.testing.repository.impl;

import com.prz.testing.domain.Question;
import com.prz.testing.repository.QuestionRepository;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 06.10.2015.
 */
@Repository
@Transactional
public class QuestionRepositoryImpl extends AbstractRepositoryImpl<Question> implements QuestionRepository {

    QuestionRepositoryImpl() {
        super(Question.class);
    }

    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = getCurrentSession().createCriteria(Question.class)
                .createAlias("creator", "user", JoinType.LEFT_OUTER_JOIN).list();

        return questions;
    }

    public Question getQuestionByQAId(Long questionAnswerId) {
        Question question = (Question) getCurrentSession().createCriteria(Question.class)
                .add(Restrictions.eq("questionAnswerId", questionAnswerId)).uniqueResult();
        return question;
    }
}
