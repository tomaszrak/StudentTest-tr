package com.prz.testing.dto;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Test;

import java.util.List;

/**
 * Created by ROLO on 02.01.2016.
 */
public class TestQA {

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
