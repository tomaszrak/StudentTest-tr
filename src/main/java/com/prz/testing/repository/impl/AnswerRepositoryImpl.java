package com.prz.testing.repository.impl;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import com.prz.testing.repository.AnswerRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
@Repository
@Transactional
public class AnswerRepositoryImpl extends AbstractRepositoryImpl<Answer> implements AnswerRepository {

    AnswerRepositoryImpl() {
        super(Answer.class);
    }

    public List<Answer> getAnswersByQuestion(Question question) throws SQLException {
        return null;
    }
}
