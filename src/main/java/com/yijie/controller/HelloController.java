package com.yijie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by barbara on 2016/11/30.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello.json", method = RequestMethod.GET)
    @ResponseBody
    public Object hello() {
        return "HelloSpringMVC";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}


