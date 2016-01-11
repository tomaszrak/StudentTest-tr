package com.prz.testing.dto;

import com.prz.testing.domain.CorrectAnswer;
import com.prz.testing.domain.Question;

import java.util.List;

/**
 * Created by ROLO on 11.01.2016.
 */
public class QuestionCAnswer {

    private Question question;

    private List<CorrectAnswer> correctAnswer;

    public List<CorrectAnswer> getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(List<CorrectAnswer> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }



    public QuestionCAnswer(Question question, List<CorrectAnswer> answers){
        this.question = question;
        this.correctAnswer = answers;
    }

    QuestionCAnswer(){

    }

}
