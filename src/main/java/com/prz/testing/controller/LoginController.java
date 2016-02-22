package com.prz.testing.controller;

import com.prz.testing.domain.User;
import com.prz.testing.service.UserService;
import com.prz.testing.util.LogUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Roman on 25.08.2015.
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @PostConstruct
    public void test(){
        logger.info("LOGIN CONTROLLER");
    }

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserData userData;


    @Autowired
    private LogUtil log;

    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public ResponseEntity<Void> studentTest(){
        logger.info("role student");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null != auth){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

//    @RequestMapping("/")
//    public void getMain(HttpServletResponse response) {
//       //return "login";
////        try {
////            UserData userData = (UserData) userService.getUserById(new Long("500"));
////            return new ResponseEntity<Void>(HttpStatus.OK);
////        } catch (SQLException e) {
////            e.printStackTrace();
////            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
//        try {
//            response.sendRedirect("/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    @RequestMapping("/")
//    public void getLoginPage(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/login.html");
////        return "login";
//    }

    @RequestMapping(value = "/messages/en", method = RequestMethod.GET)
    public ResponseEntity<Properties> getEnMessages() throws IOException {
        Properties properties = new Properties();
        properties.putAll(PropertiesLoaderUtils.loadProperties(new ClassPathResource("stApp_en.properties")));
        return new ResponseEntity<Properties>(properties, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages/pl", method = RequestMethod.GET)
    public ResponseEntity<Properties> getPlMessages() {
        return new ResponseEntity<Properties>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> getLoggedUser(){
        User user = null; //SecurityContextHolder.getContext().getAuthentication().getName()
        try {
            user = userService.getUserByIndex(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (SQLException e) {
            logger.error("Exception occured during invocation of afterLogin()", e);
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userData.setCreateDate(user.getCreateDate());
        userData.setRole(user.getRole());
        userData.setLastName(user.getLastName());
        userData.setFirstName(user.getFirstName());
        userData.setEmail(user.getEmail());
        userData.setId(user.getId());
        userData.setStatus(user.getStatus());
        userData.setIndexNumber(user.getIndexNumber());
        log.info("LAST NAME");
        logger.info("LAST NAME is " + userData.getLastName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
