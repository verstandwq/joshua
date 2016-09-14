package org.gyt.web.controller;

import org.gyt.web.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 一级页面路由器
 * Created by y27chen on 2016/7/12.
 */
@RestController
public class LoginPageController {

    @RequestMapping("/login")
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }
}
