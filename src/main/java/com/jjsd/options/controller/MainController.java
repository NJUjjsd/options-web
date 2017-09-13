package com.jjsd.options.controller;

import com.jjsd.options.entity.user.User;
import com.jjsd.options.service.UserService;
import com.jjsd.options.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by john on 2017/8/3.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView greeting() {
        System.out.println("call mainController");
        return new ModelAndView("index");
    }


}
