package com.prz.testing.controller;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import com.prz.testing.service.AnswerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ROLO on 23.11.2015.
 */
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    private Logger logger = Logger.getLogger(AnswerController.class);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> addAnswer(@RequestBody Answer answer) {
        try {
            answerService.saveAnswer(answer);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception occured during invocation of addAnswer", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
