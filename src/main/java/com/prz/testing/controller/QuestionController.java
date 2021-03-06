package com.prz.testing.controller;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.CorrectAnswer;
import com.prz.testing.domain.Question;
import com.prz.testing.dto.PaginationData;
import com.prz.testing.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 15.10.2015.
 */
@RestController
@RequestMapping("/question")
public class QuestionController extends PaginationController<Question> {

    private Logger logger = Logger.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @Override
    public PaginationData<Question> fetch(Criteria criteria) throws Exception {
        List<Question> data = questionService.getWithCriteriaPaginatedQuestions(criteria);
        Integer totalItems = questionService.countWithCriteriaQuestions(criteria);
        return new PaginationData<Question>(totalItems, data);

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<Void> addQuestion(@RequestBody Question question) throws SQLException {
        try {
            questionService.addQuestion(question);
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e){
            logger.error("Exception occurred during invocation of addQuestion()", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ResponseEntity<Set<Question>> getAll() throws SQLException {
        try {
            Set<Question> questions = questionService.getAll();
            return new ResponseEntity<Set<Question>>(questions, HttpStatus.OK);
        }catch(Exception e){
            logger.error("Exception in  getAll()", e);
            return new ResponseEntity<Set<Question>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/byId", method = RequestMethod.POST)
    public ResponseEntity<Question> getById(@RequestBody Long questionId){
        try{
            Question question = questionService.getById(questionId);
            return new ResponseEntity<Question>(question, HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception occurred during invocation of getById()", e);
            return new ResponseEntity<Question>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/correctAnswer", method = RequestMethod.POST)
    public ResponseEntity<Void> setCorrectAnswer(@RequestBody List<CorrectAnswer> answers){
        try{
            questionService.setCorrectAnswer(answers);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch(Exception e){
            logger.error("Exception in setCorrectAnswer()", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
