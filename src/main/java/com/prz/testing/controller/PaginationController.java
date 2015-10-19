package com.prz.testing.controller;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.dto.PaginationData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 15.10.2015.
 */
@RestController
public abstract class PaginationController<T> {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<PaginationData<T>> paginateData(@RequestBody Criteria criteria){
        PaginationData<T> data;
        try {
            data = fetch(criteria);
        }catch (Exception e){
            return new ResponseEntity<PaginationData<T>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<PaginationData<T>>(data, HttpStatus.OK);
    }

    public abstract PaginationData<T> fetch(Criteria criteria) throws Exception;

}
