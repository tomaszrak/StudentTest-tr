package com.prz.testing.controller;

import com.prz.testing.criteria.AnswerCriteria;
import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import com.prz.testing.dto.QuestionCAnswer;
import com.prz.testing.exception.InternalServerError;
import com.prz.testing.service.AnswerService;
import com.prz.testing.util.LogUtil;
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

/**
 * Created by ROLO on 23.11.2015.
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private LogUtil logger;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> addAnswer(@RequestBody Answer answer) {
        try {
            answerService.saveAnswer(answer);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public ResponseEntity<List<Answer>> getAllByQuestion(@RequestBody Long questionId){
        try {
            List<Answer> answers = answerService.getAllAnswersForQuestion(questionId);
            return new ResponseEntity<List<Answer>>(answers, HttpStatus.OK);
        } catch (SQLException e){
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/questionAnswer", method = RequestMethod.POST)
    public ResponseEntity<QuestionCAnswer> saveAnswerWithQuestion(@RequestBody AnswerCriteria criteria){
        try {
            QuestionCAnswer question = answerService.saveAnswersQuestion(criteria);
            return  new ResponseEntity<QuestionCAnswer>(question, HttpStatus.OK);
        } catch (Exception e){
            throw new InternalServerError(e);
        }
    }
}
