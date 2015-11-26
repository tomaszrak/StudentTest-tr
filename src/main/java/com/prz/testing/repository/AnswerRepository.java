package com.prz.testing.repository;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 23.11.2015.
 */
public interface AnswerRepository extends AbstractRepository<Answer>{

   List<Answer> getAnswersByQuestion(Question question) throws SQLException;
}
