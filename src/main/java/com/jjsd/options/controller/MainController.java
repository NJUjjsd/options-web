package com.jjsd.options.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by john on 2017/8/3.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView greeting() {
        return new ModelAndView("index");
    }
}
