package org.gyt.web.admin;

import org.gyt.web.api.service.FellowshipService;
import org.gyt.web.model.Fellowship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台页面路由器
 * Created by Administrator on 2016/9/16.
 */
@RestController
@RequestMapping("/admin")
public class AdminFellowshipPageController {

    @Autowired
    private FellowshipService fellowshipService;

    @RequestMapping("/fellowship")
    public ModelAndView tablePage() {
        ModelAndView modelAndView = new ModelAndView("admin-fellowship");
        modelAndView.addObject("subtitle", "所有团契");
        modelAndView.addObject("items", fellowshipService.getAll());
        return modelAndView;
    }

    @RequestMapping("/fellowship/{name}")
    public ModelAndView detailsPage(
            @PathVariable String name
    ) {
        ModelAndView modelAndView = new ModelAndView("admin-fellowship-details");

        Fellowship fellowship = fellowshipService.get(name);

        if (null == fellowship) {
            modelAndView.setViewName("404");
            modelAndView.addObject("message", String.format("找不到团契：%s", name));
        } else {
            modelAndView.addObject("item", fellowship);
        }

        return modelAndView;
    }
}
