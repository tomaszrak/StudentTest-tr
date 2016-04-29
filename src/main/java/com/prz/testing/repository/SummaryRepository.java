package com.prz.testing.repository;

import com.prz.testing.domain.Summary;
import com.prz.testing.domain.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ROLO on 17.01.2016.
 */
public interface SummaryRepository extends AbstractRepository<Summary>{

    List<Summary> getByUsers(List<User> students) throws SQLException;

    List<Summary> getByUser(Long userId) throws SQLException;
}
