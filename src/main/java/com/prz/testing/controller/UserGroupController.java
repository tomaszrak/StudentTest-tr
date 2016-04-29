package com.prz.testing.controller;

import com.prz.testing.dto.Summary;
import com.prz.testing.domain.User;
import com.prz.testing.domain.UserGroup;
import com.prz.testing.exception.InternalServerError;
import com.prz.testing.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ROLO on 20.12.2015.
 */
@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserGroup>> getAll() {
        try {
            List<UserGroup> groups = userGroupService.getAllGroups();
            return new ResponseEntity<List<UserGroup>>(groups, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Void> saveGroup(@RequestBody UserGroup group) {
        try {
            userGroupService.saveGroup(group);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/groupToRemove", method = RequestMethod.POST)
    public ResponseEntity<Void> removeGroup(@RequestBody UserGroup group) {
        try {
            userGroupService.removeGroup(group);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/userGroupToUpdate", method = RequestMethod.POST)
    public ResponseEntity<Void> updateGroup(@RequestBody UserGroup group) {
        try {
            userGroupService.updateGroup(group);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (SQLException e){
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/summary", method = RequestMethod.POST)
    public ResponseEntity<List<Summary>> getSummaryForUsers(@RequestBody Long userGroupId) {
        try {
            List<Summary> list = userGroupService.getSummaryForGroup(userGroupId);
            return new ResponseEntity<List<Summary>>(list, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            throw new InternalServerError(e);
        }
    }
}
