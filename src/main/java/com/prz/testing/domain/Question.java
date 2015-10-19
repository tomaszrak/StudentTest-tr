package com.prz.testing.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 28.09.2015.
 */
@Entity(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(generator = "questionId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "questionId", sequenceName = "QUESTION_ID_SEQ")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User creator;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "NAME")
    private String name;

    @Column(name = "time")
    private Double timeForAnswer;

    public Double getTimeForAnswer() {
        return timeForAnswer;
    }

    public void setTimeForAnswer(Double timeForAnswer) {
        this.timeForAnswer = timeForAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
