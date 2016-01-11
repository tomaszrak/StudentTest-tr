package com.prz.testing.criteria;

import com.prz.testing.domain.Answer;
import com.prz.testing.domain.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by ROLO on 14.12.2015.
 */
@Component
public class AnswerCriteria {

    private Question question;

    private Set<Answer> answers;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
