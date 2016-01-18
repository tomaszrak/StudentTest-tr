package com.prz.testing.repository.impl;

import com.prz.testing.domain.Summary;
import com.prz.testing.repository.SummaryRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by ROLO on 17.01.2016.
 */
@Repository
@Transactional
public class SummaryRepositoryImpl extends AbstractRepositoryImpl<Summary> implements SummaryRepository {

    SummaryRepositoryImpl() {
        super(Summary.class);
    }
}
