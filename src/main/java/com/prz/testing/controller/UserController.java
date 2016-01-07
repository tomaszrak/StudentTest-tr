package com.prz.testing.controller;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Role;
import com.prz.testing.domain.User;
import com.prz.testing.dto.PaginationData;
import com.prz.testing.enumerate.RoleName;
import com.prz.testing.enumerate.Status;
import com.prz.testing.service.RoleService;
import com.prz.testing.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

    private Logger logger = Logger.getLogger(UserController.class);

    @Override
    public PaginationData<User> fetch(Criteria criteria) throws Exception {
        List<User> data = userService.getUsersWithCriteriaPaginated(criteria);
        Integer totalItems = userService.countUsersWithCriteria(criteria);
        return new PaginationData<User>(totalItems, data);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAll() throws SQLException {
        logger.info("last name = " + userData.getLastName());
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Void> saveUser(@RequestBody User user) throws SQLException {
        logger.info("test");
//        User user = new User();
//        user.setId(new Long(1));
//        user.setPassword("test");
//        user.setCreateDate(new Date());
//        user.setEmail("test");
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setIndexNumber(new Long(787877));
//        user.setStatus(Status.ACTIVE);
        userService.saveUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/userToUpdate", method = RequestMethod.POST)
    public ResponseEntity<Void> updateUser(@RequestBody User user) throws SQLException {
        userService.updateUser(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/paginated", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getPaginatedUsers() {
        List<User> user = new ArrayList<User>();
        try {
            user = userService.getAllUsersPaginated(new Criteria(0, 10));
        } catch (SQLException e) {
            logger.error(e);
            return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }
}
