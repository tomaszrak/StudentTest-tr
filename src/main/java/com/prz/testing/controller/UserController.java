package com.prz.testing.controller;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.User;
import com.prz.testing.dto.PaginationData;
import com.prz.testing.exception.InternalServerError;
import com.prz.testing.service.RoleService;
import com.prz.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private RoleService roleService;

    @Autowired
    private UserData userData;

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
            userService.saveUser(user);
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
}
