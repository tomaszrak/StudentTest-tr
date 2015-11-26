package com.prz.testing.controller;

import com.prz.testing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String getMain(HttpServletResponse response) {
        return "login";
//        try {
//            UserData userData = (UserData) userService.getUserById(new Long("500"));
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        try {
//            response.sendRedirect("/app/index.html");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

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

}
