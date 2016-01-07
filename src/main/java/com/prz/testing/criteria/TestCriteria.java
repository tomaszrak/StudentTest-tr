package com.prz.testing.criteria;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Test;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ROLO on 13.12.2015.
 */
@Component
public class TestCriteria {

    private Test test;

    private List<Answer> answers;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
