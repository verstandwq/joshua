package org.gyt.web.admin;

import org.gyt.web.api.service.RoleService;
import org.gyt.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminNotificationPageController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/notification")
    public ModelAndView tablePage(
            @RequestParam(required = false) String type
    ) {
        ModelAndView modelAndView = new ModelAndView("admin-notification");
        modelAndView.addObject("subtitle", type);
        return modelAndView;
    }

    @RequestMapping("/notification/{id}")
    public ModelAndView detailsPage(
            @PathVariable String id
    ) {
        ModelAndView modelAndView = new ModelAndView("admin-notification-details");
        modelAndView.addObject("subtitle", id);
        return modelAndView;
    }
}
