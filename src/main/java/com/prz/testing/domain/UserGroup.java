package com.prz.testing.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Roman on 27.09.2015.
 */
@Entity(name = "USER_GROUP")
public class UserGroup {

    @Id
    @SequenceGenerator(name = "userGroupId", sequenceName = "USER_GROUP_ID_SEQ")
    @GeneratedValue(generator = "userGroupId", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID", referencedColumnName = "id")
    private User creator;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_GROUP_TEST",
        joinColumns = @JoinColumn(name = "USER_GROUP_ID"),
        inverseJoinColumns = @JoinColumn(name = "TEST_ID"))
    private Set<Test> tests;

    @Column(name = "CREATE_DATE", nullable = true)
    private Date createDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_GROUP_USERS",
            joinColumns = @JoinColumn(name = "USER_GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
