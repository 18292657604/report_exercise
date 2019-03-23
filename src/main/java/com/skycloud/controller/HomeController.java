package com.skycloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author lsy
 * @date 2019/3/22
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("index")
    @ResponseBody
    public String homePage(){
        return "This  is home page";
    }

    @RequestMapping("login")
    public String indexPage(){
        return "login";
    }

    @RequestMapping("logout")
    public ModelAndView logout(){
        ModelMap map = new ModelMap();
        map.put("id", 1);
        map.put("name", "hello world");
        return new ModelAndView("logout", map);
    }

}
