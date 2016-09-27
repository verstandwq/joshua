package org.gyt.web.controller;

import org.gyt.web.api.service.UserService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户信息页面路由器
 * Created by y27chen on 2016/7/12.
 */
@RestController
public class UserInfoPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelAndViewUtils modelAndViewUtils;

    @RequestMapping("/info")
    public ModelAndView infoPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("info");
        return modelAndView;
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView passwordPage() {
        ModelAndView modelAndView = modelAndViewUtils.newModelAndView("password");
        return modelAndView;
    }
}
