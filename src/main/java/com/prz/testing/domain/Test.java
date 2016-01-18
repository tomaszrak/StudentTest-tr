package com.prz.testing.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 27.09.2015.
 */
@Entity(name = "test")
public class Test {

    @Id
    @SequenceGenerator(name = "testId", sequenceName = "TEST_ID_SEQ")
    @GeneratedValue(generator = "testId", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "subject_id", referencedColumnName = "id")
//    private Subject subject;
    @Column(name = "subject")
    private String subject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "TEST_QUESTION",
            joinColumns = @JoinColumn(name = "TEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_ID"))
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
