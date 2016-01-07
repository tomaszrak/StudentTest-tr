package com.prz.testing.criteria;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ROLO on 14.12.2015.
 */
@Component
public class AnswerCriteria {

    private Question question;

    private List<Answer> answers;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
