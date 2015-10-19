package com.prz.testing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by Roman on 25.08.2015.
 */
@RestController
@RequestMapping("/test")
public class LoginController {

    @PostConstruct
    public void test(){
        System.out.println("login controller");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(){
        return "start";
    }
}
