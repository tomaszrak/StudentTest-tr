package com.prz.testing.controller;

import com.prz.testing.domain.Role;
import com.prz.testing.domain.User;
import com.prz.testing.exception.InternalServerError;
import com.prz.testing.service.UserService;
import com.prz.testing.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Roman on 25.08.2015.
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LogUtil logger;

    @Autowired
    private UserService userService;

    @Autowired
    private UserData userData;

    @Autowired
    private LogUtil log;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(null != auth){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<Properties> getEnMessages(@RequestParam String lang) {
        try {
            Properties properties = new Properties();
            if(lang.equals("en")){
                properties.putAll(PropertiesLoaderUtils.loadProperties(new ClassPathResource("stApp_en.properties")));
            } else{
                properties.putAll(PropertiesLoaderUtils.loadProperties(new ClassPathResource("stApp_pl.properties")));
            }
            return new ResponseEntity<Properties>(properties, HttpStatus.OK);
        } catch (IOException e) {
            throw new InternalServerError(e);
        }
    }

    @RequestMapping(value = "/messages/pl", method = RequestMethod.GET)
    public ResponseEntity<Properties> getPlMessages() {
        return new ResponseEntity<Properties>(HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> getLoggedUser(){
        try {
            User user = userService.getUserByIndex(SecurityContextHolder.getContext().getAuthentication().getName());
            userData.setCreateDate(user.getCreateDate());
            userData.setRole(user.getRole());
            userData.setLastName(user.getLastName());
            userData.setFirstName(user.getFirstName());
            userData.setEmail(user.getEmail());
            userData.setId(user.getId());
            userData.setStatus(user.getStatus());
            userData.setIndexNumber(user.getIndexNumber());
            userData.setRoleName(user.getRole().getName().toString());
            log.info("LAST NAME");
            logger.info("LAST NAME is " + userData.getLastName());

            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e);
        }
    }

}
