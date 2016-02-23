package com.prz.testing.controller;

import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.QuestionAnswer;
import com.prz.testing.domain.Summary;
import com.prz.testing.domain.Test;
import com.prz.testing.exception.InternalServerError;
import com.prz.testing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ROLO on 08.12.2015.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private UserData userData;

    @RequestMapping(value = "/tests", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> getAllTests() {

        try {
            List<Test> tests = testService.getTestsByUser(userData.getId());
            return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> saveTest(@RequestBody TestCriteria criteria) {
        try {
            testService.saveTest(criteria);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/toSolve", method = RequestMethod.POST)
    public ResponseEntity<Test> getTest(@RequestBody Long userId) {
        try {
            Test test = testService.getTest(userId);
            return new ResponseEntity<Test>(test, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/resolved", method = RequestMethod.POST)
    public ResponseEntity<Summary> solveTest(@RequestBody List<QuestionAnswer> answers) {
        try {
            Summary summary = testService.solveTest(answers);
            return new ResponseEntity<Summary>(summary, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }
}
