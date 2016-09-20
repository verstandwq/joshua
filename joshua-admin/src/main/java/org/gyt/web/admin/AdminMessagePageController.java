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
public class AdminMessagePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/message")
    public ModelAndView tablePage(
            @RequestParam(required = false) String type
    ) {
        ModelAndView modelAndView = new ModelAndView("admin-message");
        modelAndView.addObject("subtitle", "小标题");
        return modelAndView;
    }

    @RequestMapping("/message/{id}")
    public ModelAndView detailsPage(
            @PathVariable String id
    ) {
        ModelAndView modelAndView = new ModelAndView("admin-message-details");
        modelAndView.addObject("subtitle", id);
        return modelAndView;
    }
}
