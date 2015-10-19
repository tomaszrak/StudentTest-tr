package com.prz.testing.controller;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Question;
import com.prz.testing.dto.PaginationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Roman on 15.10.2015.
 */
@RestController
@RequestMapping("/question")
public class QuestionController extends PaginationController<Question>{

    @Autowired
    private QuestionSer
    @Override
    public PaginationData<Question> fetch(Criteria criteria) throws Exception {
        List<Question> data =
        return null;
    }

    public ResponseEntity addQuestion(){

        return new ResponseEntity(HttpStatus.OK);
    }
}
