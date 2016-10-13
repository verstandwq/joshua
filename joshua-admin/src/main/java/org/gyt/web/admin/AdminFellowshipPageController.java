package org.gyt.web.admin;

import org.gyt.web.api.service.FellowshipService;
import org.gyt.web.api.utils.ModelAndViewUtils;
import org.gyt.web.model.Fellowship;
import org.gyt.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminFellowshipPageController {

    @Autowired
    private FellowshipService fellowshipService;

    @Autowired
    private ModelAndViewUtils modelAndViewUtils;

    @RequestMapping("/fellowship")
    public ModelAndView tablePage() {
        ModelAndView modelAndView = modelAndViewUtils.newAdminModelAndView("adminPages/admin-fellowship");
        modelAndView.addObject("subtitle", "所有团契");
        modelAndView.addObject("items", fellowshipService.getAll());
        return modelAndView;
    }

    @RequestMapping("/myfellowship")
    public ModelAndView myTablePage() {
        ModelAndView modelAndView = modelAndViewUtils.newAdminModelAndView("adminPages/admin-fellowship");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelAndView.addObject("subtitle", "我的团契");

        Set<Fellowship> fellowshipSet = new HashSet<>();
        fellowshipSet.addAll(fellowshipService.getUserOwnerFellowship(user.getUsername()));
        fellowshipSet.addAll(fellowshipService.getUserAdminFellowship(user.getUsername()));
        modelAndView.addObject("items", fellowshipSet);
        return modelAndView;
    }

    @RequestMapping("/fellowship/{name}")
    public ModelAndView detailsPage(
            @PathVariable String name
    ) {
        ModelAndView modelAndView = modelAndViewUtils.newAdminModelAndView("adminPages/admin-fellowship-details");

        Fellowship fellowship = fellowshipService.get(name);

        if (null == fellowship) {
            modelAndView.setViewName("404");
            modelAndView.addObject("message", String.format("找不到团契：%s", name));
        } else {
            modelAndView.addObject("item", fellowship);
            modelAndView.addObject("subtitle", fellowship.getDisplayName());
        }

        return modelAndView;
    }
}
