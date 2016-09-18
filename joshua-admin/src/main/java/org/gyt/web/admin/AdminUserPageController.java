package org.gyt.web.admin;

import org.apache.commons.lang3.StringUtils;
import org.gyt.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminUserPageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public ModelAndView home(
            @RequestParam(required = false) String type
    ) {
        ModelAndView modelAndView = new ModelAndView("admin-user");

        if (StringUtils.isEmpty(type)) {
            modelAndView.addObject("users", userService.getAll());
        } else if (type.equalsIgnoreCase("system")) {
            modelAndView.addObject("users", userService.getAll().stream().filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("SYSTEM_ADMIN"))).collect(Collectors.toList()));
        } else if (type.equalsIgnoreCase("owner")) {
            modelAndView.addObject("users", userService.getAll().stream().filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("FELLOWSHIP_OWNER"))).collect(Collectors.toList()));
        } else if (type.equalsIgnoreCase("admin")) {
            modelAndView.addObject("users", userService.getAll().stream().filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("FELLOWSHIP_ADMIN"))).collect(Collectors.toList()));
        }

        return modelAndView;
    }
}
