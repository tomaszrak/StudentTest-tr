package com.prz.testing.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roman on 28.09.2015.
 */
@Entity(name = "SUMMARY")
public class Summary {

    @Id
    @GeneratedValue(generator = "summaryId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "summaryId", sequenceName = "SUMMARY_ID_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User student;

    @Column(name = "DEGREE")
    private String degree;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ID", name = "TEST_ID")
    private Test test;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
