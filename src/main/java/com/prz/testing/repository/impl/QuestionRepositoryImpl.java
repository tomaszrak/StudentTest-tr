package com.prz.testing.repository.impl;

import com.prz.testing.domain.Question;
import com.prz.testing.repository.QuestionRepository;
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


        return null;
    }
}
