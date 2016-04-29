package com.prz.testing.controller;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.User;
import com.prz.testing.dto.PaginationData;
import com.prz.testing.dto.Summary;
import com.prz.testing.exception.InternalServerError;
import com.prz.testing.service.SummaryService;
import com.prz.testing.service.UserService;
import com.prz.testing.service.impl.SummaryServiceImpl;
import com.prz.testing.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Roman on 12.09.2015.
 */
@RequestMapping("/users")
@RestController
public class UserController extends PaginationController<User> {

    @Autowired
    private UserService userService;

    @Autowired
    private UserData userData;

    @Autowired
    private LogUtil logger;

    @Autowired
    private SummaryServiceImpl summaryService;

    @Override
    public PaginationData<User> fetch(Criteria criteria) throws Exception {
        List<User> data = userService.getUsersWithCriteriaPaginated(criteria);
        Integer totalItems = userService.countUsersWithCriteria(criteria);
        return new PaginationData<User>(totalItems, data);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll() {
        try {
            return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        try {
            userService.saveOrUpdateUser(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/userToUpdate", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/users/paginated", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getPaginatedUsers() {
        try {
            List<User> user = userService.getAllUsersPaginated(new Criteria(0, 10));
            return new ResponseEntity<List<User>>(user, HttpStatus.OK);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            if (null == user) {
                logger.info(userData.getId().toString(),
                        String.format("Cannot remove user. User with id=%d not found", id));
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
            }

            userService.deleteUser(id);
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        } catch (SQLException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ResponseEntity<Void> resetPassword(@RequestBody User user) {
        try {
            userService.resetPassword(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/summary", method = RequestMethod.POST)
    public ResponseEntity<List<Summary>> getUserSummary(@RequestBody Long userId){
        try {
            List<Summary> summaries = summaryService.getSummaryForUser(userId);
            return new ResponseEntity<List<Summary>>(summaries, HttpStatus.OK);
        } catch (Exception e){
            throw new InternalServerError(e);
        }
    }
}
