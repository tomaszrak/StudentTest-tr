package com.prz.testing.repository;

import com.prz.testing.domain.Question;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 06.10.2015.
 */
public interface QuestionRepository extends AbstractRepository<Question>{

    List<Question> getAllQuestions() throws SQLException;

    Question getQuestionByQAId(Long questionAnswerId);
}
