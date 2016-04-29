package com.prz.testing.service.impl;

import com.prz.testing.domain.User;
import com.prz.testing.dto.Summary;
import com.prz.testing.exception.ObjectProcessException;
import com.prz.testing.repository.SummaryRepository;
import com.prz.testing.service.SummaryService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROLO on 26.04.2016.
 */
@Service
@Transactional
public class SummaryServiceImpl{

    @Autowired
    private SummaryRepository summaryRepository;

    public List<Summary> getSummaryForUser(Long userId) throws SQLException, ObjectProcessException {

        List<com.prz.testing.domain.Summary> summaries = summaryRepository.getByUser(userId);
        List<Summary> result = new ArrayList<Summary>();
        for(com.prz.testing.domain.Summary summary : summaries){
            Summary smr = new Summary();
            try {
                BeanUtils.copyProperties(smr, summary);
            } catch (IllegalAccessException e) {
                throw new ObjectProcessException(e);
            } catch (InvocationTargetException e) {
                throw new ObjectProcessException(e);
            }
            result.add(smr);
        }
        return result;
    }
}
