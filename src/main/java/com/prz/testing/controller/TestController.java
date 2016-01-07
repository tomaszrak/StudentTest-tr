package com.prz.testing.controller;

import com.prz.testing.criteria.TestCriteria;
import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Test;
import com.prz.testing.dto.TestQA;
import com.prz.testing.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
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

    private Logger logger = Logger.getLogger(TestController.class);

    @RequestMapping(value="/tests", method = RequestMethod.GET)
    public ResponseEntity<List<Test>> getAllTests(){

        try {
            List<Test> tests = testService.getTestsByUser(userData.getId());
            return new ResponseEntity<List<Test>>(tests, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Exception occured during invocation of getAllTests()", e);
            return new ResponseEntity<List<Test>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> saveTest(@RequestBody TestCriteria criteria){
        try {
            testService.saveTest(criteria);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            logger.error("Exception occurred during invocation of saveTest()", e);
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/toSolve", method = RequestMethod.POST)
    public ResponseEntity<TestQA> getTest(@RequestBody Long userId){
        try{
            TestQA test = testService.getTest(userId);
            return new ResponseEntity<TestQA>(test, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Exception occurred during invocation of getTest()", e);
            return new ResponseEntity<TestQA>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}