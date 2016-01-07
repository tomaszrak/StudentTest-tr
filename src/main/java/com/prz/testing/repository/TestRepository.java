package com.prz.testing.repository;

import com.prz.testing.domain.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 07.12.2015.
 */
public interface TestRepository extends AbstractRepository<Test>{


    List<Test> getTestsByUser(Long id) throws SQLException;

    void saveTest(Test test) throws SQLException;
}
